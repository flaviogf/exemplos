package forecast

import (
	"github.com/flaviogf/gosimpleweather/weather"
)

type WeatherProvider interface {
	GetWeatherByCity(city string) (weather.Weather, error)
}

type service struct {
	weatherProvider WeatherProvider
}

func NewService(weatherProvider WeatherProvider) *service {
	return &service{weatherProvider}
}

func (s service) WhatToWear(city string) (string, error) {
	weather, err := s.weatherProvider.GetWeatherByCity(city)

	if err != nil {
		return "", err
	}

	if weather.Temperature < 21 {
		return "long sleeves", nil
	}

	return "short sleeves", nil
}
