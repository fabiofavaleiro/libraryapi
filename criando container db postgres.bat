@echo Abaixo comandos para gerar imagem docker:

docker run  --name librarydb-prod -p 5433:5432 -e POSTGRES_PASSWORD=postgresprod -e POSTGRES_USER=postgresprod -e POSTGRES_DB=library --network library-network postgres:16.6

@echo Agora o comando para criação do container:

docker run --name library-production -e DATASOURCE-URL=jdbc:postgresql://librarydb-prod:5432/library -e DATASOURCE_USERNAME=postgresprod -e DATASOURCE_PASSWORD=postgresprod --network library-network -d -p 8080:8080 -p 9090:9090 fabiofavaleiro/libraryapi

pause