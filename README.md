# API de Agregador de Investimentos

Bem-vindo à documentação da API de Agregador de Investimentos! Esta API permite que usuários se cadastrem, associem contas a seus perfis, vinculem ações às contas e consultem informações sobre essas ações, incluindo seus valores. A API utiliza **OpenFeign** para consumir dados de APIs externas, garantindo integração com fontes de informações financeiras.

## Sumário
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Endpoints](#endpoints)
  - [Cadastro de Usuário](#cadastro-de-usuário)
  - [Associação de Conta](#associação-de-conta)
  - [Associação de Ação a Conta](#associação-de-ação-a-conta)
  - [Consulta de Ações da Conta](#consulta-de-ações-da-conta)
- [Exemplo de Uso](#exemplo-de-uso)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Funcionalidades
- **Cadastro de Usuário**: Permite criar um novo usuário no sistema.
- **Associação de Conta**: Vincula uma conta financeira a um usuário existente.
- **Associação de Ações**: Permite associar uma ou mais ações a uma conta.
- **Consulta de Ações**: Retorna as ações associadas a uma conta, incluindo seus valores, obtidos via integração com APIs externas.

## Tecnologias Utilizadas
- **Java** (ou especifique a linguagem, se diferente)
- **Spring Boot** (presumido, ajuste se necessário)
- **OpenFeign**: Cliente HTTP para consumir APIs externas de dados financeiros.
- **Banco de Dados**: [Especifique, ex. MySQL, PostgreSQL, MongoDB, etc.]
- **Maven** ou **Gradle** (para gerenciamento de dependências)

## Pré-requisitos
- Java 17+ (ou versão específica)
- Maven/Gradle (especifique a versão, se aplicável)
- Banco de dados configurado (ex. MySQL com schema criado)
- Chave de API para serviços externos (se necessário, ex. Alpha Vantage, Yahoo Finance)

## Instalação
1. Clone o repositório:
   ```bash
   git clone https://github.com/tailansanttos/agregador-de-investimentos.git

  ## Endpoints

### Cadastro de Usuário
Cria um novo usuário no sistema.

- **URL**: `/users`
- **Método**: `POST`
- **Body**:
  ```json
  {
    "username": "João Silva",
    "email": "joao@example.com",
    "password": "senha123"
  }


Associa um usuário a uma conta.

- **URL**: `/users/{userId}/accounts`
- **Método**: `POST`
- **Body**:
  ```json
  {
  "description": "Conta de teste",
  "street": "Rua dos testes",
  "number": 3
  }


Associa uma ação a uma conta.

- **URL**: `/accounts{accountId}/stocks`
- **Método**: `POST`
- **Body**:
  ```json
  {
	"stockId":"ITUB4",
	"quantity":20
}
