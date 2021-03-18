package main

import (
	"fmt"
	"log"

	"github.com/flaviogf/gosimpleweather/fakeweather"
	"github.com/flaviogf/gosimpleweather/forecast"
)

func main() {
	provider := fakeweather.NewProvider()

	service := forecast.NewService(provider)

	outfit, err := service.WhatToWear("Franca")

	if err != nil {
		log.Fatal(err)
	}

	fmt.Println(outfit)
}
