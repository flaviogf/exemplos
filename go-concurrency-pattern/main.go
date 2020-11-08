package main

import (
	"fmt"
	"time"
)

func main() {
	ch1 := boring("someone")

	for i := 0; i < 5; i++ {
		fmt.Println(<-ch1)
	}

	joe := boring("Joe")

	anna := boring("Anna")

	for i := 0; i < 5; i++ {
		fmt.Println(<-joe)
		fmt.Println(<-anna)
	}

	ch2 := fanIn(boring("Joe"), boring("Anna"))

	for i := 0; i < 5; i++ {
		fmt.Println(<-ch2)
	}

	ch3 := fanInV2(boring("Joe"), boring("Anna"))

	for i := 0; i < 5; i++ {
		fmt.Println(<-ch3)
	}

	listenWithTimeout(boring("Frank"), 5)

	chain()
}

// generator
func boring(text string) chan string {
	ch := make(chan string)

	go func() {
		for i := 0; ; i++ {
			ch <- fmt.Sprintf("%s: Boring %d!", text, i)

			time.Sleep(time.Second)
		}
	}()

	return ch
}

// multiplexing
func fanIn(ch1, ch2 chan string) chan string {
	ch := make(chan string)

	go func() {
		for {
			ch <- <-ch1
		}
	}()

	go func() {
		for {
			ch <- <-ch2
		}
	}()

	return ch
}

func fanInV2(ch1, ch2 chan string) chan string {
	ch := make(chan string)

	go func() {
		for {
			select {
			case s := <-ch1:
				ch <- s
			case s := <-ch2:
				ch <- s
			}
		}
	}()

	return ch
}

func listenWithTimeout(ch chan string, seconds time.Duration) {
	timeout := time.After(seconds * time.Second)

	for {
		select {
		case s := <-ch:
			fmt.Println(s)
		case <-timeout:
			fmt.Println("too slow")
			return
		}
	}
}

func chain() {
	const n = 100000

	leftmost := make(chan int)

	right := leftmost

	left := leftmost

	for i := 0; i < n; i++ {
		right = make(chan int)

		go f(left, right)

		left = right
	}

	go func(ch chan int) {
		ch <- 1
	}(right)

	fmt.Println(<-leftmost)
}

func f(left, right chan int) {
	left <- 1 + <-right
}
