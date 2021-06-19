require "functions_framework"

FunctionsFramework.http("hello") do |request|
  "Received #{request.request_method} from #{request.url}\n"
end
