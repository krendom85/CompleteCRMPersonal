package config

import (
	"log"
	"os"

	"github.com/joho/godotenv"
)

type Config struct {
	DBUrl     string
	MQUrl     string
	QueueName string
}

func LoadConfig() *Config {
	err := godotenv.Load()
	if err != nil {
		log.Println("Advertencia: No se pudo cargar el archivo .env o no existe. Se usarán variables de entorno del sistema.")
	}
	return &Config{
		DBUrl:     os.Getenv("DB_URL"),
		MQUrl:     os.Getenv("MQ_URL"),
		QueueName: os.Getenv("QUEUE_NAME"),
	}
}
