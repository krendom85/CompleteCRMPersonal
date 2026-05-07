package models

import "time"

type RucStatus string
type RucStep string

const (
	StatusActive    RucStatus = "active"
	StatusInactive  RucStatus = "inactive"
	StatusSuspended RucStatus = "suspended"
)

const (
	StepInitial    RucStep = "initial"
	StepWaiting    RucStep = "waiting"
	StepValidation RucStep = "validation"
	StepCompleted  RucStep = "completed"
	StepError      RucStep = "error"
)

type RucEntity struct {
	ID        uint      `gorm:"primaryKey;autoIncrement"`
	Ruc       string    `gorm:"type:varchar(20);not null;unique"`
	Name      string    `gorm:"type:varchar(255);not null"`
	Address   string    `gorm:"type:varchar(255)"`
	Phone     string    `gorm:"type:varchar(20)"`
	Email     string    `gorm:"type:varchar(100)"`
	Status    RucStatus `gorm:"type:varchar(20)"`
	Step      RucStep   `gorm:"type:varchar(20)"`
	CreatedAt time.Time `gorm:"autoCreateTime"`
	UpdatedAt time.Time `gorm:"autoUpdateTime"`
}

func (RucEntity) TableName() string {
	return "rucs"
}
