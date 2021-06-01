package main

import (
	"context"
	"log"
	"time"

	"github.com/flaviogf/grpc_quickstart/pb"
	"google.golang.org/grpc"
)

func main() {
	conn, err := grpc.Dial("localhost:9000", grpc.WithInsecure(), grpc.WithBlock())

	if err != nil {
		log.Fatal(err)
	}

	c := pb.NewGreeterClient(conn)

	ctx, cancel := context.WithTimeout(context.Background(), time.Second)

	defer cancel()

	resp, err := c.SayHello(ctx, &pb.HelloRequest{Name: "World"})

	if err != nil {
		log.Fatal(err)
	}

	log.Println(resp.GetMessage())
}
