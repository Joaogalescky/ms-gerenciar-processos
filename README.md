# Desafio 03 micro serviço para gestão de tomadade de decições de uma empresa

## Introdução
Este projeto é um sistema de backend desenvolvido em Java e Spring Boot para gerenciar processos de tomada de decisões em uma empresa. Ele permite que equipes proponham e votem em diversas propostas para melhorias internas. O sistema é composto por diversos microsserviços, cada um responsável por uma parte específica da aplicação, sendo esses, o de funcionários, propostas e resultados.

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.3.0
- Maven
- Swagger
- Trello
- OpenFeign
- Postman
- Lombok
- Docker
- H2 Database
- Spring Cloud Gateway
- Spring Actuator
- Spring Data JPA

## Arquitetura de Microsserviços
1. **API Gateway**: Ponto de entrada da aplicação.
2. **Serviço de Funcionários**: Cadastro e edição de funcionários.
3. **Serviço de Propostas**: Cadastro e edição de propostas de melhoria, incluído a gestão de sessões de votação, recebimento de votos, cá.
4. **Serviço de Votação**: Gestão de sessões de votação, recebimento de votos, cálculo e fornecimento dos resultados.
5. **Serviço de Resultados**: Armazenamento e fornecimento dos resultados das votações.

## Requisitos Funcionais
1. **Cadastro e Edição de Funcionários**
2. **Registro de Propostas de Melhoria**
3. **Início de Sessão de Votação**
4. **Recebimento de Votos**
5. **Contabilização de Votos e Fornecimento do Resultado**

## Requisitos Não Funcionais
- Persistência dos dados mesmo após reinício da aplicação.
- Monitoramento e métricas com Spring Actuator.

## Passos para Execução

### Pré-requisitos
- JDK 17
- Maven 4.0.0
- Docker (opcional, para execução com Docker)

### 1. Clonando o Repositório
```bash
git clone https://github.com/seu-usuario/sistema-decisao.git
cd sistema-decisao
```

### 2. Build do Projeto
```bash
mvn clean install
```

### 3. Executando os Microsserviços
## API Gateway
```bash
cd api-gateway
mvn spring-boot:run
```
## Serviço de Funcionário
```bash
cd funconario-service
mvn spring-boot:run
```
## Serviço de Proposta
```bash
cd proposta-service
mvn spring-boot:run
```
## Serviço de Resultado
```bash
cd resultado-service
mvn spring-boot:run
```

### 4. Endpoints da API
## Funcionários
Cadastro de Funcionário: POST /api/v1/funcionarios
- Corpo da requisição (JSON):
```bash
{
    "nome": "Nome do Funcionário",
    "cpf": "11111111111",
    "dataNasc": "(yyyy-MM-dd)",
    "sexo": "(F ou M)" 
}
```
Edição de Funcionário: PUT /api/v1/funcionarios/{id}
```bash
{
    "nome": "Novo nome de Funcionário",
    "cpf": "22222222222",
    "dataNasc": "(yyyy-MM-dd)",
    "sexo": "(F ou M)" 
}
```
Deletar Funcionário: DELETE /api/v1/funcionarios/{id}
Buscar Funcionário: GET /api/v1/funcionarios/{id}
Listagem de Funcionários: GET /api/v1/funcionarios/
## Propostas
Cadastro de Proposta: POST /api/v1/propostas
Edição de Proposta: PUT /api/v1/propostas/{id}
Deletar Proposta: DELETE /api/v1/propostas/{id}
Buscar Funcionário: GET /api/v1/propostas/{id}
## Votação
Início de Sessão de Votação: POST /voting-sessions
Receber Votos: POST /votes
## Resultados
Obter Resultado: GET /resultados/{idSessao}
## Sessão de Votação
Cadastro de Proposta: POST /api/v1/sessaovotacao
Obter Resultado: GET /api/v1/sessaovotacao/{id}
## Voto
Cadastro de Proposta: POST /api/v1/votos


### 5. Versionamento da API
Para versionar a API, adotamos a estratégia de versionamento na URL. Exemplo:
GET /v1/funcionarios
GET /v2/funcionarios
Essa abordagem é simples e eficaz, permitindo que diferentes versões da API coexistam, facilitando a evolução e manutenção do sistema sem quebrar a compatibilidade com clientes antigos.

### 6. Monitoramento
Cada microsserviço possui endpoints de monitoramento ativados pelo Spring Actuator. Exemplos:
GET /actuator/health
GET /actuator/metrics

### 7. Considerações Finais
Este projeto demonstra uma arquitetura de microsserviços robusta, utilizando tecnologias modernas e práticas recomendadas para garantir escalabilidade, manutenibilidade e facilidade de desenvolvimento. Sinta-se à vontade para explorar e contribuir para este projeto!
