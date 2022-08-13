# frozen_string_literal: true

require 'ruby2d'

t = Time.now

update do
  close if Time.now - t > 5
end

show
