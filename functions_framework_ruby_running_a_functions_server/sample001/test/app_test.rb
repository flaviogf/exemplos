require "minitest/autorun"
require "functions_framework/testing"

class AppTest < MiniTest::Test
  include FunctionsFramework::Testing

  def test_should_return_ok
    load_temporary "app.rb" do
      request = make_post_request "http://localhost",
                                  '{"name": "Ruby"}',
                                  ["Content-Type: application/json"]

      response = call_http "hello", request

      assert_equal 200, response.status
    end
  end

  def test_should_return_message_with_name
    load_temporary "app.rb" do
      request = make_post_request "http://localhost",
                                  '{"name": "Ruby"}',
                                  ["Content-Type: application/json"]

      response = call_http "hello", request

      assert_equal "Hello, Ruby!\n", response.body.join
    end
  end
end
