# frozen_string_literal: true

require 'ruby2d'

class Paddle
  attr_accessor :direction

  def initialize(position:)
    @position = position

    @width = 8
    @height = 45

    @x = if position == :right
           Window.width - @width
         else
           0
         end

    @y = Window.height / 2 - @height

    @velocity = 5
  end

  def draw
    Rectangle.new(x: @x, y: @y, width: @width, height: @height)
  end

  def move
    case direction
    when :down
      @y += @velocity
    when :up
      @y -= @velocity
    end
  end
end

player1 = Paddle.new(position: :left)
player2 = Paddle.new(position: :right)

on :key_held do |event|
  case event.key
  when 'j'
    player1.direction = :down
  when 'k'
    player1.direction = :up
  end
end

on :key_up do |event|
  case event.key
  when 'j', 'k'
    player1.direction = nil
  end
end

update do
  clear

  player1.draw
  player2.draw

  player1.move
end

show
