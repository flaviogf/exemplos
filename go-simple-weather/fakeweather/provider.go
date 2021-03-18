package fakeweather

import "github.com/flaviogf/gosimpleweather/weather"

type Provider struct{}

func NewProvider() *Provider {
	return &Provider{}
}

func (p *Provider) GetWeatherByCity(city string) (weather.Weather, error) {
	return weather.Weather{Temperature: 20}, nil
}
