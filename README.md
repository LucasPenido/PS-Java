# PS-Java

# Sobre a atividade

O teste consiste em construir a camada de serviço de um pseudo ecommerce de games mobile utilizando Java

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven
- Lombok
- H2
- Mapstruct

# Como executar o projeto

## Back end
Pré-requisitos: Java 11

```bash
# clonar repositório
git clone https://github.com/LucasPenido/PS-Java.git

# entrar na pasta do projeto back end
cd PS-Java

# executar o projeto
./mvnw spring-boot:run
```
# Endpoints

## Produtos

### http://localhost:8080/api/products

| Métodos       | Descrição     
|:-------------:|:-------------
| GET           | Retorna uma lista de produtos que estão disponíveis para serem adicionados no carrinho.

Obs: O parâmetro "?orderBy=" pode ser combinado com 'score', 'name' ou 'price' para que os produdos sejam ordenados.
Ex: http://localhost:8080/api/products?orderBy=name para ordernar pelo nome dos produtos.

## Usuários
### http://localhost:8080/api/users

| Métodos       | Descrição     
|:-------------:|:-------------
| GET           | Retorna a lista de todos os usuários.

Obs: Apenas um usuário foi adicionado para fins de demonstração. O usuário com o CPF 8374619563 será utilizado como teste.

## Carrinho

### http://localhost:8080/api/carts

| Métodos       | Descrição     | Body
|:-------------:|:-------------:|:-:
| GET           | Retorna a lista de todos os carrinhos.| |
| POST          | Cria um carrinho para um determinado usuário passando seu cpf no body| Ex: {"cpf": "83746195633"}

### http://localhost:8080/api/carts/user/{cpf}

| Métodos       | Descrição     
|:-------------:|:-------------
| GET           | Retorna o carrinho do usuário com o cpf especificado.

### http://localhost:8080/api/carts/{cartId}

| Métodos       | Descrição     | Body
|:-------------:|:-------------:|:-:
| POST          | Adiciona a quantidade de um produto a um carrinho específico passando o id do produto com a quantidade que deseja adicionar| Ex: { "quantity": 3, "product": { "id": 12 } }
| DELETE | Remove a quantidade de um produto a um carrinho específico passando o id do produto e a quantidade que deseja remover| Ex: { "quantity": 1, "product": { "id": 12 } }

## Checkout

### http://localhost:8080/api/checkout

| Métodos       | Descrição     | Body
|:-------------:|:-------------:|:-:
| GET           | Retorna a lista de todos os checkouts.| |
| POST          | Cria um checkout para um determinado carrinho passando seu id no body| Ex: { "id": "1" }

### http://localhost:8080/api/checkout/{checkoutId}

| Métodos       | Descrição     
|:-------------:|:-------------
| GET           | Retorna o checkout com o id especificado.

### http://localhost:8080/api/checkout/cart/{cartId}

| Métodos       | Descrição     
|:-------------:|:-------------
| GET           | Retorna o checkout do carrinho especificado.

# Autor

Lucas Penido Antunes

https://www.linkedin.com/in/LucasPenidoAntunes/

