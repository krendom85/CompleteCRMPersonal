package service

import (
	"time"

	"github.com/krendom85/SvrDataCustomers/internal/db/models"
	"gorm.io/gorm"
)

func UpdateStep(db *gorm.DB, id uint, step models.RucStep) error {
	var entity models.RucEntity

	if err := db.First(&entity, id).Error; err != nil {
		return err
	}

	entity.Step = step
	entity.UpdatedAt = time.Now()
	return db.Save(&entity).Error
}

func UpdateStatus(db *gorm.DB, id uint, status models.RucStatus) error {
	var entity models.RucEntity

	if err := db.First(&entity, id).Error; err != nil {
		return err
	}

	entity.Status = status
	entity.UpdatedAt = time.Now()
	return db.Save(&entity).Error
}

func Create(db *gorm.DB, ruc string) error {

	if ruc == "" || len(ruc) > 20 {
		return gorm.ErrInvalidData
	}

	entity := &models.RucEntity{
		Ruc:       ruc,
		Status:    models.StatusActive,
		Step:      models.StepInitial,
		CreatedAt: time.Now(),
		UpdatedAt: time.Now(),
	}
	return db.Create(entity).Error
}

func Update(db *gorm.DB, id uint, name, address, phone, email string) error {
	var entity models.RucEntity
	if err := db.First(&entity, id).Error; err != nil {
		return err
	}

	entity.Name = name
	entity.Address = address
	entity.Phone = phone
	entity.Email = email
	entity.UpdatedAt = time.Now()
	return db.Save(&entity).Error
}
