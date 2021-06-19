require "functions_framework"

FunctionsFramework.http("hello") do |request|
  begin
    raise "whoops!"
  rescue RuntimeError => e
    [500, {}, ["Uh, oh, got an error message: #{e.message}.\n"]]
  end
end
