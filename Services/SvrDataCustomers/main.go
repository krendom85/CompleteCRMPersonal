package main

import (
	"encoding/json"
	"fmt"
	"time"

	"github.com/krendom85/SvrDataCustomers/internal/config"
	"github.com/krendom85/SvrDataCustomers/internal/db"
	"github.com/krendom85/SvrDataCustomers/internal/messaging"
	"github.com/krendom85/SvrDataCustomers/internal/models"

	"github.com/streadway/amqp"
)

func main() {
	cfg := config.LoadConfig()

	var err = db.Init(cfg.DBUrl, 10, 5, 30*time.Minute)
	if err != nil {
		panic(err)
	}

	mq, err := messaging.New(cfg.MQUrl, cfg.QueueName)
	if err != nil {
		panic(err)
	}
	defer mq.Close()

	maxWorkers := 10
	sem := make(chan struct{}, maxWorkers)

	mq.Consume(func(msg amqp.Delivery) {
		sem <- struct{}{}
		go func(m amqp.Delivery) {
			defer func() { <-sem }()
			fmt.Println("Procesando mensaje:", string(m.Body))

			var data models.Message
			err := json.Unmarshal(m.Body, &data)
			if err != nil {
				fmt.Println("Error al parsear mensaje:", err)
				return
			}
			fmt.Println("Tipo de mensaje recibido:", data.Type)
			fmt.Println("Datos del mensaje recibido:", data.Data)
		}(msg)
	})

	select {}
}
