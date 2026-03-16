package main

import (
	"fmt"
	"time"

	"github.com/krendom85/SvrDataCustomers/internal/config"
	"github.com/krendom85/SvrDataCustomers/internal/db"
	"github.com/krendom85/SvrDataCustomers/internal/messaging"
	"github.com/streadway/amqp"
)

func main() {
	cfg := config.LoadConfig()

	var err = db.Init(cfg.DBUrl, 10, 5, 30*time.Minute)
	if err != nil {
		panic(err)
	}
	defer db.GetDB().Close()

	mq, err := messaging.New(cfg.MQUrl, cfg.QueueName)
	if err != nil {
		panic(err)
	}
	defer mq.Close()

	mq.Consume(func(msg amqp.Delivery) {
		fmt.Println("Mensaje recibido:", string(msg.Body))
	})
}
