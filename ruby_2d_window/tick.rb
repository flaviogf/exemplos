# frozen_string_literal: true

require 'ruby2d'

tick = 0

update do
  set background: 'random' if (tick % 60).zero?

  tick += 1
end

show
