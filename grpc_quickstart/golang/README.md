```sh
protoc \
-I=./pb \
--go_out=./pb \
--go_opt=module=github.com/flaviogf/grpc_quickstart/pb \
--go-grpc_out=./pb \
--go-grpc_opt=module=github.com/flaviogf/grpc_quickstart/pb \
helloworld.proto
```