require 'functions_framework'

FunctionsFramework.http('hello') do |request|
  "Hello World!\n"
end
