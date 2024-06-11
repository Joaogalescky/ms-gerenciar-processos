﻿# Desafio 3 micro serviço para gestão de votação de propostas para funcionários

Este projeto consiste em três microserviços distintos, um para a gestão de votação, de proposta e para resultado. Ambos os microserviços foram desenvolvidos utilizando Spring Boot com Java, estes microserviços fornecem uma maneira eficiente de gerenciar funcionários, de propostas e resultados.

## Tecnologias Utilizadas

- Spring Boot
- H2 Database
- Java
- Spring Data JPA
- RESTful Web Services
- OpenFeign
- Maven
- Trello
- Swagger
- Postman
- Lombok

## Instalação

```
git clone https://github.com/CarlosMantovani/ms-gerenciar-processos.git
```

## Como Usar

Instruções de uso: Apos o clone abri as duas pastas separadamente e rodar elas separadas.

## Autor
- Carlos Henrique Mantovani
- João Guilherme
- Gabriel Vieira
- João Vitor

## Status do Projeto

Em desenvolvimento

## Endpoints:
## Funcionário
```
POST /api/v1/funcionarios: Cria um novo aluno. Retorna o aluno criado.
```
## Proposta
```
POST /api/v1/propostas: Cria uma nova matrícula. Retorna informações sobre o curso e os alunos matriculados após a matrícula ser efetuada.
```
## Resultado
```
POST /api/v1/resultados: Cria um novo curso. Retorna o curso criado.
```
## Para contribuir com micro_servico, siga estas etapas:

1. Bifurque este repositório.
2. Crie um branch: `git checkout -b <nome_branch>`.
3. Faça suas alterações e confirme-as: `git commit -m '<mensagem_commit>'`
4. Envie para o branch original: `git push origin micro_servico/ <local>`
5. Crie a solicitação de pull.
