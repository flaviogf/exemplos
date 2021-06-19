# Docker

```sh
docker build -t flaviogf/functions-framework-sample001 .

docker run --rm -p 8080:8080 flaviogf/functions-framework-sample001 --target=hello
```

# Google Cloud Function

```sh
export YOUR_FUNCTION_NAME=hello

export YOUR_FUNCTION_TARGET=hello

export YOUR_PROJECT_ID=proj-xpto

gcloud functions deploy $YOUR_FUNCTION_NAME \
    --project=$YOUR_PROJECT_ID \
    --runtime=ruby27 \
    --trigger-http \
    --entry-point=$YOUR_FUNCTION_TARGET
```
