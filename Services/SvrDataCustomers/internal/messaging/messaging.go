package messaging

import (
	"github.com/streadway/amqp"
)

type MQ struct {
	conn    *amqp.Connection
	channel *amqp.Channel
	Queue   amqp.Queue
}

func New(url, queueName string) (*MQ, error) {
	conn, err := amqp.Dial(url)
	if err != nil {
		return nil, err
	}

	ch, err := conn.Channel()
	if err != nil {
		conn.Close()
		return nil, err
	}

	q, err := ch.QueueDeclare(
		queueName,
		true,  // durable
		false, // delete when unused
		false, // exclusive
		false, // no-wait
		nil,   // arguments
	)
	if err != nil {
		ch.Close()
		conn.Close()
		return nil, err
	}

	return &MQ{conn: conn, channel: ch, Queue: q}, nil
}

func (mq *MQ) Close() {
	mq.channel.Close()
	mq.conn.Close()
}

func (mq *MQ) Consume(handler func(msg amqp.Delivery)) error {
	msgs, err := mq.channel.Consume(
		mq.Queue.Name,
		"",
		true,  // autoAck
		false, // exclusive
		false, // noLocal
		false, // noWait
		nil,   // args
	)
	if err != nil {
		return err
	}
	go func() {
		for msg := range msgs {
			handler(msg)
		}
	}()
	return nil
}
