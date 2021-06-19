require "functions_framework"

FunctionsFramework.on_startup do |function|
  require_relative "lib/hello"
end

FunctionsFramework.http("hello") do |request|
  Hello.new(request).build_response
end
