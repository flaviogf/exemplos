require "functions_framework"

FunctionsFramework.http("hello") do |request|
  request.logger.info "I received #{request.request_method} from #{request.url}"
  "ok\n"
end
