# Gerenciador de Tarefas

## Descrição

O **Gerenciador de Tarefas** é uma aplicação web desenvolvida com Java e Spring Boot para gerenciar tarefas. A aplicação permite criar, editar, excluir e visualizar tarefas, com uma interface de usuário amigável desenvolvida com Thymeleaf.

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação utilizada para o desenvolvimento.
- **Spring Boot 3.3.3**: Framework para desenvolvimento rápido de aplicações Java.
- **Spring Data JPA**: Implementação de persistência para acesso a dados.
- **Spring Security**: Para autenticação e autorização.
- **Spring Boot Actuator**: Para monitoramento e gerenciamento da aplicação.
- **Thymeleaf**: Motor de templates para criar a interface web.
- **H2 Database**: Banco de dados em memória para testes e desenvolvimento.
- **SpringDoc OpenAPI**: Para geração automática de documentação da API.

## Funcionalidades

- **Criar Tarefas**: Adicione novas tarefas com título, descrição, estado e prioridade.
- **Listar Tarefas**: Visualize todas as tarefas em uma tabela.
- **Visualizar Detalhes**: Veja os detalhes de uma tarefa específica.
- **Editar Tarefas**: Modifique informações das tarefas existentes.
- **Excluir Tarefas**: Remova tarefas que não são mais necessárias.
- **Atualizar Estado**: Altere o estado de uma tarefa.
- **Priorizar Tarefas**: Defina a prioridade de uma tarefa.

## Como Rodar o Projeto

1. **Clone o Repositório**

   ```bash
   git clone https://github.com/andreyferraz/gerenciador-de-tarefas-supera.git
   cd gerenciador-de-tarefas-supera

2. **Compile e Execute**

    ```bash
    mvn spring-boot:run

3. **Acesse a Aplicação**

Abra seu navegador e vá para http://localhost:8080/listas para ver a lista de tarefas.

## Estrutura dos Diretórios

- src/main/java/com/supera/gerenciador_de_tarefas_supera: Contém o código-fonte da aplicação.
- models: Entidades JPA.
- controllers: Controladores REST e Thymeleaf.
- services: Lógica de negócios.
- repository: Interfaces de repositório para acesso a dados.
- src/main/resources: Contém arquivos de configuração e templates Thymeleaf.
- application.properties: Configurações da aplicação.
- templates: Templates Thymeleaf para o front-end.

## Testes

Os testes unitários e de integração estão localizados em src/test/java. Para executar os testes, use:
    ```bash
    mvn test

## Documentação da API

A documentação da API pode ser acessada em http://localhost:8080/swagger-ui.html após iniciar a aplicação.

## Contato

Para mais informações, entre em contato com andrey.developer@hotmail.com