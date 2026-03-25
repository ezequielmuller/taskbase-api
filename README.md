# TaskBase API

> **⚠️ Status: Em Desenvolvimento** - Rotas e endpoints ainda em construção. A API está em fase inicial de desenvolvimento.

API RESTful para criação e gerenciamento de tarefas no estilo Kanban, permitindo organizar projetos em grupos e acompanhar tarefas com atribuição de usuários.
Projeto desenvolvido do zero com o objetivo de consolidar boas práticas em desenvolvimento Java - desde a modelagem do banco de dados até a construção de uma API RESTful completa, aplicando o padrão DAO, mapeamento de entidades e organização de um projeto real com Maven e Servlets.

---

## Tecnologias
- Java 21 (LTS)
- Maven 3.x
- Jakarta Servlets
- SQL & PostgreSQL 18.x
- Apache Tomcat 10+
- Docker | *(em breve)* |

---

## Arquitetura

O projeto segue o padrão **DAO (Data Access Object)** para separação das responsabilidades de acesso ao banco de dados.

```
taskbase-api/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/taskbaseapi/
│       │       ├── persistencia/          # Data Access Objects (DAO) - Services
│       │       ├── modelo/        # Entidades/Classes
│       │       └── servlet/      # Servlets (Controllers)
│       └── webapp/
│           └── WEB-INF/
│               └── web.xml
├── pom.xml
└── README.md
```

---

### Entidades

- **`usuario`** — Usuários do sistema (pode ser gerenciador), que irão participar das atividades.
- **`projeto`** — Projetos disponíveis na plataforma.
- **`grupo`** — Colunas do Kanban (ex: *A Fazer*, *Em Progresso*, *Concluído*)
- **`tarefas`** — Cards/tarefas atribuídas a usuários dentro de grupos
- **`usuario_projeto`** — Relacionamento N:N entre usuários e projetos

## Configuração e Execução

### ⚠️ Docker *(Em breve)*
O suporte a Docker está planejado para uma próxima etapa do desenvolvimento. O objetivo é conteinerizar tanto a aplicação quanto o banco de dados PostgreSQL via `docker-compose`.

---

### Pré-requisitos

- Java 21+
- Maven 3.x
- PostgreSQL rodando localmente
- Apache Tomcat 10+ ou outro servidor compatível com Jakarta Servlets para o deploy/execução da api.

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/taskbase-api.git
cd taskbase-api
```

### 2. Configure o banco de dados

- Crie o banco de dados com o nome taskbaseapi 
- Execute o script SQL localizado em ```./database/schema.sql```.
- Em seguida, configure as credenciais no arquivo de conexão `persistencia/Conexao.java`.

### 3. Build com Maven
 
```bash
mvn clean package
```

### 4. Deploy

Faça o deploy do `.war` gerado em um servidor compatível com Jakarta Servlets (ex: Apache Tomcat 10+).

---

## ⚠️ Endpoints *(Em definição)*
As rotas da API ainda estão sendo definidas. Esta seção será atualizada conforme o desenvolvimento avançar.

<p align="center">Desenvolvido com ☕ Java 21</p>
