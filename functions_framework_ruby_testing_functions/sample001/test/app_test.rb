require "minitest/autorun"
require "functions_framework/testing"

class AppTest < MiniTest::Test
  include FunctionsFramework::Testing

  def test_hello_should_return_ok
    load_temporary "app.rb" do
      request = make_post_request "http://localhost:8080",
                                  "{\"name\":\"Ruby\"}",
                                  ["Content-Type: application/json"]

      response = call_http "hello", request

      assert_equal 200, response.status
      assert_equal "Hello, Ruby!", response.body.join
    end
  end
end
