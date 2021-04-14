package main

import (
	"fmt"
	"log"

	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
)

type Task struct {
	gorm.Model
	Title string
	Done  bool
}

func main() {
	db, err := gorm.Open(sqlite.Open("database.sqlite3"), &gorm.Config{})

	if err != nil {
		log.Fatal(err)
	}

	db.AutoMigrate(&Task{})

	db.Create(&Task{Title: "Study English", Done: false})

	var task Task

	db.First(&task, 1)

	fmt.Printf("Task: %v, Done? %v\n", task.Title, task.Done)
}
