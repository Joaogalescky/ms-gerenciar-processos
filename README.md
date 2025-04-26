# Desafio 03  - Microsserviço para gestão de tomada de decisões de uma empresa

## Introdução
Este projeto é um sistema back-end desenvolvido em Java e Spring Boot para gerenciar processos de tomada de decisões em uma empresa. Permite que equipes proponham e votem em diversas propostas para melhorias internas. O sistema é composto por diversos microsserviços, cada um responsável por uma parte específica da aplicação, sendo esses, o de funcionários, propostas e resultados.

## Tecnologias Utilizadas
- Java 17;
- Spring Boot 3.3.0;
- Maven;
- Swagger;
- Trello;
- OpenFeign;
- Postman;
- Lombok;
- Docker;
- H2 Database;
- Spring Cloud Gateway;
- Spring Actuator;
- Spring Data JPA.

## Arquitetura de Microsserviços
1. **API Gateway**: Ponto de entrada da aplicação;
2. **Serviço de Funcionários**: Cadastro e edição de funcionários;
3. **Serviço de Propostas**: Cadastro e edição de propostas de melhoria, incluído a gestão de sessões de votação, recebimento de votos e cadastramento das sessões;
4. **Serviço de Votação**: Gestão de sessões de votação, recebimento de votos, cálculo e fornecimento dos resultados;
5. **Serviço de Resultados**: Armazenamento e fornecimento dos resultados das votações.

## Requisitos Funcionais
1. Cadastro e Edição de Funcionários;
2. Registro de Propostas de Melhoria;
3. Início de Sessão de Votação;
4. Recebimento de Votos;
5. Contabilização de Votos e Fornecimento do Resultado.

## Requisitos Não Funcionais
1. Persistência dos dados mesmo após reinício da aplicação;
2. Monitoramento e métricas com Spring Actuator.

## Passos para Execução

### Pré-requisitos
- JDK 17;
- Maven 3.9.7;
- Docker (opcional, para execução com Docker).

### 1. Clonando o Repositório
```bash
git clone https://github.com/CarlosMantovani/ms-gerenciar-processos.git
```

### 2. Build do Projeto
```bash
cd ms-gerenciar-processos/funcionario
mvn clean install

cd ms-gerenciar-processos/gateway
mvn clean install

cd ms-gerenciar-processos/proposta
mvn clean install

cd ms-gerenciar-processos/resultado
mvn clean install

cd ms-gerenciar-processos/springBootAdmin
mvn clean install
```

### 3. Executando os Microsserviços
## API Gateway
```bash
cd ms-gerenciar-processos/gateway
mvn spring-boot:run
```
## Serviço de Funcionário
```bash
cd ms-gerenciar-processos/funcionario
mvn spring-boot:run
```
## Serviço de Proposta
```bash
cd ms-gerenciar-processos/proposta
mvn spring-boot:run
```
## Serviço de Resultado
```bash
cd ms-gerenciar-processos/resultado
mvn spring-boot:run
```

### 4. Endpoints da API
## Funcionários
Cadastro de Funcionário: 
```
POST /api/v1/funcionarios
```
- Corpo da requisição (JSON):
```bash
{
    "nome": "Nome do Funcionário",
    "cpf": "11111111111",
    "dataNasc": "(yyyy-MM-dd)",
    "sexo": "(F ou M)" 
}
```
Edição de Funcionário: 
```
PUT /api/v1/funcionarios/{id}
```
- Corpo da requisição (JSON):
```bash
{
    "nome": "Novo nome de Funcionário",
    "cpf": "22222222222",
    "dataNasc": "(yyyy-MM-dd)",
    "sexo": "(F ou M)" 
}
```
Deletar Funcionário: 
```
DELETE /api/v1/funcionarios/{id}
```
Buscar Funcionário: 
```
GET /api/v1/funcionarios/{id}
```
Listagem de Funcionários: 
```
GET /api/v1/funcionarios/
```

## Propostas
Cadastro de Proposta: 
```
POST /api/v1/propostas
```
- Corpo de requisição (JSON):
```bash
{
    "idFuncionario": ID do funcionário,
    "nome": "Nome da proposta",
    "descricao": "Descrição da proposta"
}
```
Edição de Proposta: 
```
PUT /api/v1/propostas/{id}
```
- Corpo de requisição (JSON):
```bash
{
    "nome": "Nome da proposta",
    "descricao": "Descrição da proposta",
    "status": "ATIVO ou INATIVO"
}
```
Listar Proposta:
```
GET /api/v1/propostas
```
Buscar Proposta:
```
GET /api/v1/propostas/{id}
```
Deletar Proposta: 
```
DELETE /api/v1/propostas/{id}
```

## Sessão de Votação
Cadastrar Votação: 
```
POST /api/v1/sessaovotacao
```
- Corpo de requisição (JSON):
```bash
{   
    "idProposta": ID Proposta,
    "tempoVotacao": "00:00"
}
```
Buscar Sessão: 
```
GET /api/v1/sessaovotacao/{id}
```
- Corpo de requisição (JSON):
```bash
{
    "nome": "Nome da sessão",
    "descricao": "Descrição da sessão",
    "tempoVoto": "00:00"
}
```

## Voto
Cadastrar Voto: 
```
POST /api/v1/votos
```
- Corpo de requisição (JSON):
```bash
{
    "idSessao": ID Sessão,
    "idFunc": ID Funcionário,
    "escolha": "APROVADO e REPROVADO"
}
```

## Resultados
Buscar Resultado: 
```
GET api/v1/resultados/{idSessao}
```

## Monitoramento - Métricas do Actuator
Para acessar as métricas do actuator, há duas formas:

**Portas dos Microsserviços:**
```
 8081 - Proposta
 8082 - Funcionários
 8083 - Resultado
 ```
**Postman:**
 
 Actuator EndPoints
```
GET {porta do microsserviço}/actuator
```
 Health 
```
GET {porta do microsserviço}/actuator/health
```
 Info 
```
GET {porta do microsserviço}/actuator/info
```
 Metrics 
```
GET {porta do microsserviço}/actuator/metrics
```
**SpringBootAdmin:**
```
http/localhost:{porta do microsserviço}/applications
```
Para acessar as API's, há duas possibilidades, sendo:
- Wallboard - acessível pelo menu onboard;
- Applications - acessível pela página home.
## 

### 5. Versionamento da API
Para versionar a API, adotamos a estratégia de versionamento na URL. Exemplo:
```
/v1/funcionarios
```
```
/v2/funcionarios
```
Essa abordagem é simples e eficaz, permitindo que diferentes versões da API coexistam, facilitando a evolução e manutenção do sistema sem quebrar a compatibilidade com clientes antigos.
Para executar os testes `@GET`,  `@PUT`,  `@POST` e `@DELETE`, é necessário a colocação da versão na URL para a execução das requisições.

### 6. Considerações Finais
Este projeto demonstra uma arquitetura de microsserviços robusta, utilizando tecnologias modernas e práticas recomendadas para garantir escalabilidade, manutenibilidade e facilidade de desenvolvimento. Sinta-se à vontade para explorar e contribuir para este projeto!

## Para contribuir com ms-gerenciar-processos:
1. Bifurque este repositório.
2. Crie um branch: `git checkout -b <nome_branch>`.
3. Faça suas alterações e confirme-as: `git commit -m '<mensagem_commit>'`
4. Envie para o branch original: `git push origin ms-gerenciar-processos/ <local>`
5. Crie a solicitação de pull.
