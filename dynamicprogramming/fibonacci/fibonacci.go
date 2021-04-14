package main

import (
	"fmt"
	"time"
)

func main() {
	n := 30

	start := time.Now()

	fmt.Println(fib(n))

	fmt.Println(time.Since(start).Nanoseconds()) // 5841500

	start = time.Now()

	fmt.Println(fibMemo(n, make([]int, n+1)))

	fmt.Println(time.Since(start).Nanoseconds()) // 75900

	start = time.Now()

	fmt.Println(fibMemoWithoutRecursion(n))

	fmt.Println(time.Since(start).Nanoseconds()) // 0
}

func fib(n int) int {
	if n == 1 || n == 2 {
		return 1
	}

	return fib(n-1) + fib(n-2)
}

func fibMemo(n int, memo []int) int {
	if n == 1 || n == 2 {
		return 1
	}

	if memo[n] != 0 {
		return memo[n]
	}

	memo[n] = fibMemo(n-1, memo) + fibMemo(n-2, memo)

	return memo[n]
}

func fibMemoWithoutRecursion(n int) int {
	if n == 1 || n == 2 {
		return 1
	}

	memo := make([]int, n+1)

	memo[1] = 1

	memo[2] = 1

	for i := 3; i < n+1; i++ {
		memo[i] = memo[i-1] + memo[i-2]
	}

	return memo[n]
}
