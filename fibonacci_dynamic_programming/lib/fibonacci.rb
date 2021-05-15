def fibonacci(n)
  fibonacci_memo n, {0 => 0, 1 => 1}
end

def fibonacci_memo(n, memo)
  if !memo.has_key? n
    memo[n] = fibonacci_memo(n - 1, memo) + fibonacci_memo(n - 2, memo)
  end

  memo[n]
end

