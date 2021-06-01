package main

import (
	"context"
	"fmt"
	"log"
	"net"

	"github.com/flaviogf/grpc_quickstart/pb"
	"google.golang.org/grpc"
)

func main() {
	lis, err := net.Listen("tcp", ":9000")

	if err != nil {
		log.Fatal(err)
	}

	s := grpc.NewServer()

	pb.RegisterGreeterServer(s, new(service))

	fmt.Printf("Listening to %s\n", lis.Addr())

	s.Serve(lis)
}

type service struct {
	pb.UnimplementedGreeterServer
}

func (s *service) SayHello(ctx context.Context, request *pb.HelloRequest) (*pb.HelloResponse, error) {
	return &pb.HelloResponse{Message: fmt.Sprintf("Hello %s", request.Name)}, nil
}
