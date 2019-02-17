FROM microsoft/dotnet AS build
ADD . .
RUN dotnet restore
RUN dotnet publish -c Release -o out
COPY --from=build ./CatalogoFilmes.Api/out ./
EXPOSE 8080
ENTRYPOINT [ "dotnet", "CatalogoFilmes.Api.dll" ]