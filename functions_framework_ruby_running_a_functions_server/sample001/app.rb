require "functions_framework"
require "json"

FunctionsFramework.http("hello") do |request|
  name = JSON.parse(request.body.read.to_s)["name"] rescue nil

  name ||= "World"

  "Hello, #{name}!\n"
end
