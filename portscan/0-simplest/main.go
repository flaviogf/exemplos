package main

import (
	"fmt"
	"log"
	"net"
)

func main() {
	port := 5432

	conn, err := net.Dial("tcp", fmt.Sprintf("localhost:%d", port))

	if err != nil {
		log.Fatalf("%d (CLOSED)\n", port)
	}

	defer conn.Close()

	log.Printf("%d (OPEN)\n", port)
}
