# frozen_string_literal: true

require 'ruby2d'

class Paddle
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
  end

  def draw
    Rectangle.new(x: @x, y: @y, width: @width, height: @height)
  end
end

player1 = Paddle.new(position: :left)
player2 = Paddle.new(position: :right)

update do
  player1.draw
  player2.draw
end

show
