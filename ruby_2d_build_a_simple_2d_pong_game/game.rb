# frozen_string_literal: true

require 'ruby2d'

class Paddle
  attr_reader :position, :width, :height

  def initialize(position: :left, width: 8, height: 20)
    @position = position
    @width = width
    @height = height
  end

  def draw
    Rectangle.new(x: x, y: y, width: width, height: height)
  end

  private

  def x
    return Window.width - width if position == :right

    0
  end

  def y
    @y = Window.height / 2 - height
  end
end

player1 = Paddle.new
player2 = Paddle.new(position: :right)

update do
  player1.draw
  player2.draw
end

set width: 640, height: 480

show
