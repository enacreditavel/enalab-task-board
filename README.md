# Enalab Task Board

Este projeto é um sistema de quadro de tarefas (Task Board) desenvolvido para o Enalab. Ele permite o gerenciamento de quadros, colunas e tarefas, além de autenticação de usuários.

## 🚀 Tecnologias e Ferramentas

O projeto está sendo construído com tecnologias modernas do ecossistema Java:

*   **Java 21**
*   **Spring Boot 3**
*   **Spring Data JPA** para persistência de dados.
*   **MySQL** como banco de dados relacional principal.
*   **Spring Security & JWT (JSON Web Token)** para autenticação e autorização.
*   **Spring Modulith** para garantir a integridade e separação lógica dos módulos da aplicação.
*   **Lombok** para redução de código boilerplate.
*   **Docker & Docker Compose** para conteinerização e facilidade de execução dos ambientes.
*   **JUnit 5 & Mockito** para testes automatizados.

## 🏗️ Arquitetura e Padrões de Projeto

O desenvolvimento deste sistema é guiado pelos princípios da **Arquitetura Limpa (Clean Architecture)** e **Domain-Driven Design (DDD)**, visando um código altamente coeso, de baixo acoplamento e focado nas regras de negócio.

Alguns conceitos e padrões aplicados:
*   **Separação em Módulos**: O projeto atualmente é dividido em dois módulos principais:
    *   `auth`: Responsável por toda a parte de segurança, gerenciamento de usuários, login e geração/validação de tokens JWT.
    *   `tasking`: O core do negócio, responsável pelo gerenciamento de Quadros (`Board`), Colunas (`BoardColumn`) e Tarefas (`Task`).
*   **Design Patterns**: Utilização intensiva de padrões de projeto, como o **Builder** (para criação de entidades de domínio e objetos complexos) e o padrão **Use Case** (para representar as ações/comandos do sistema de forma isolada).
*   **Independência de Frameworks**: O domínio da aplicação é isolado de detalhes de infraestrutura (como banco de dados e frameworks web), facilitando testes e manutenção.

## 📖 Documentação

As operações de sistema e as APIs estão sendo desenvolvidas de forma iterativa.
Para acompanhar o progresso das implementações (operações CRUD) do módulo principal (`tasking`), consulte o arquivo [CRUD_OPERATIONS.md](CRUD_OPERATIONS.md) na raiz do projeto.

A documentação da API REST pode ser acessada via **Swagger UI** (fornecido pelo SpringDoc OpenAPI) quando a aplicação estiver em execução.

## ⚙️ Como Executar

O projeto possui um arquivo `Dockerfile` na raiz, configurado com build em múltiplos estágios (multi-stage build) utilizando Gradle e a imagem oficial do Eclipse Temurin 21.

Para construir e executar a aplicação via Docker:

1.  Faça o build da imagem:
    ```bash
    docker build -t enalab-board .
    ```
2.  Execute o container (lembre-se de configurar as variáveis de ambiente do banco de dados, caso necessário):
    ```bash
    docker run -p 8080:8080 enalab-board
    ```

Como o projeto utiliza `spring-boot-docker-compose`, iniciar a aplicação localmente via Gradle também subirá as dependências de infraestrutura (como o MySQL) automaticamente caso exista um arquivo `compose.yaml` configurado.

## 🛠️ Status do Desenvolvimento

O projeto encontra-se em fase de desenvolvimento ativo. Os módulos `auth` e `tasking` já possuem suas estruturas bases criadas e alguns casos de uso fundamentais implementados, porém ainda estão sendo finalizados antes de um lançamento inicial.
