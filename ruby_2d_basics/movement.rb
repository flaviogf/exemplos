# frozen_string_literal: true

require 'ruby2d'

set background: 'white'

@square = Square.new(x: 10, y: 20, size: 25, color: 'blue')
@x_speed = 0
@y_speed = 0

on :key_down do |event|
  keys = {
    'k' => method(:up),
    'j' => method(:down),
    'h' => method(:left),
    'l' => method(:right)
  }

  keys.fetch(event.key, method(:nothing)).call
end

def up
  @x_speed = 0
  @y_speed = -2
end

def down
  @x_speed = 0
  @y_speed = 2
end

def left
  @x_speed = -2
  @y_speed = 0
end

def right
  @x_speed = 2
  @y_speed = 0
end

def nothing; end

update do
  @square.x += @x_speed
  @square.y += @y_speed
end

show
