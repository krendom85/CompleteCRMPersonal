package db

import (
	"context"
	"database/sql"
	"log"
	"time"

	_ "github.com/lib/pq"
)

var DB *sql.DB

func Init(dbUrl string, maxOpen, maxIdle int, connMaxLifetime time.Duration) error {
	var err error
	DB, err = sql.Open("postgres", dbUrl)
	if err != nil {
		log.Fatalf("Error abriendo la conexión a la base de datos: %v", err)
	}
	DB.SetMaxOpenConns(maxOpen)
	DB.SetMaxIdleConns(maxIdle)
	DB.SetConnMaxLifetime(connMaxLifetime)

	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()
	if err := DB.PingContext(ctx); err != nil {
		log.Fatalf("No se pudo conectar a la base de datos: %v", err)
	}
	return nil
}

func GetDB() *sql.DB {
	return DB
}
