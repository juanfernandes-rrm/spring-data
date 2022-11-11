﻿# Spring Data
 
 ## Ambiente
 
- JDK8+
- IDE
- Banco de dados
- Client Banco

## Criando aplicação

Será utilizado o Spring Initializr ([https://start.spring.io/](https://start.spring.io/)) para criar a aplicação com a seguinte configuração:

<img width="600" src="https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fc3c5cb30-367b-4c67-a27f-00bf98d6bdc5%2FUntitled.png?table=block&id=c4870454-ab7f-4d58-9578-2a8fa4528aa3&spaceId=c201bf83-8b0f-4f26-aff6-11cb5d30850e&width=2000&userId=4b9f37e7-280d-4f3d-bb98-bcfe01bcc215&cache=v2"/>

Após gerar a aplicação, basta importá-la em alguma IDE.

## Configurando o Banco de Dados

Através do ***mvn repository (***[https://mvnrepository.com/](https://mvnrepository.com/)***)***, busque a dependência do driver do Banco de dados escolhido para o projeto, e adicione no arquivo ***pom.xml***. Neste caso, foi utilizado o driver do ***Mysql***.

Com o driver do Banco de dados adicionado ao projeto, é necessário informar à aplicação as credenciais de acesso, isso é feito no arquivo application.properties.

## Funcionamento do Spring Data

O Spring Data acopla todos os elementos listados abaixo. Dessa forma, não é preciso implementar vários objetos, como no caso do ***EntityManager*** da ***JPA***, o Spring Data ficará responsável por isso.

<img width="600" src="https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F843242c3-c976-4269-8425-bb9ccc3e8175%2FUntitled.png?table=block&id=459c1792-6db1-4d3a-aad2-306fdaf30716&spaceId=c201bf83-8b0f-4f26-aff6-11cb5d30850e&width=2000&userId=4b9f37e7-280d-4f3d-bb98-bcfe01bcc215&cache=v2"/>


## Repository

Com o banco gerado, é possível popular as tabelas. Isso é feito através da interface ***Repository.*** O CrudRepository facilita a persistência de objetos, pois diferente da JPA, não é preciso implementar os métodos do CRUD, eles são gerados pelo Spring Data.

<img width="600" src="https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F6e0a7785-ac90-411a-8e23-7fafb12b71ae%2FUntitled.png?table=block&id=474df216-eb35-4482-ac64-0fa7d6c84d8a&spaceId=c201bf83-8b0f-4f26-aff6-11cb5d30850e&width=2000&userId=4b9f37e7-280d-4f3d-bb98-bcfe01bcc215&cache=v2"/>




