require "functions_framework"
require "sinatra/base"

class App < Sinatra::Base
  get "/hello/:name" do
    "Hello #{params[:name]}!\n"
  end
end

FunctionsFramework.http("hello") do |request|
  App.call request.env
end
