# Sprint 3 POO - Programação Orientada a Objeto

> **Professor:** Ygor Moraes Martins dos Anjos
---

## Instruções para entrega

O objetivo macro desta Sprint é evoluir a arquitetura do sistema implementando a camada de persistência de dados. Utilizando a API JDBC pura e o banco de dados Oracle, o foco é aplicar o padrão arquitetural DAO (Data Access Object) para realizar operações de CRUD e persistir o histórico de relatórios automáticos, integrando o armazenamento físico às regras de negócio e validações Fail-Fast estabelecidas na modelagem Orientada a Objetos.
<ul>
    <li>Criar arquivo 'seu-script-criacao.sql' com as tabelas criadas a partir das classes de domínio.
    </li>
    <li>Criar arquivo 'seu-script-dados.sql' com a carga de dados iniciais.
    </li>
    <li>Apresentar banco de dados configurado e scripts validados.
    </li>
    <li>Incluir classe de conexão configurada para acesso ao Oracle.
    </li>
    <li>Garantir padrão DAO (Data Access Object) implementado para todas as entidades.
    </li>
    <li>Criar 'RelatorioPrioridadeDAO' implementado com uso do Java Record.
    </li>
    <li>Criar 'GeradorRelatorio.java' evoluído para persistir o histórico no banco de dados.
    </li>
    <li>Incluir classe 'SistemaMonitoramento.java' (Main) demonstrando o ciclo de vida completo (CRUD) e Fail-Fast.
    </li>
</ul>

---
### Tecnologias utilizadas
<p>
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=git,java" />
  </a>
</p>

### Lógica para desenvolvimento do sistema
Com o objetivo de implementar melhorias no projeto entregue durante o processo avaliativo da Sprint 2, realizou-se uma nova organização dos arquivos e a reestruturação do código, de modo a atender às solicitações e aos requisitos estabelecidos para a entrega da Sprint 3.
  ### Organização de arquivos da Sprint 2:

  ```
  sistemamotiva/
  ├── main
  │   └── SistemaMonitoramento.java
  └── model
      ├── Autoestrada.java
      ├── AutoestradaSensorizada.java
      ├── EquipeManutencao.java
      ├── EstradaVicinal.java
      ├── IdentificacaoTrecho.java
      ├── IntervencaoOperacional.java
      ├── Manutencao.java
      ├── MockTrechoSensorizado.java
      ├── MonitoravelViaIoT.java
      ├── Pulverizacao.java
      ├── RegrasNegocio.java
      ├── RocadaMecanizada.java
      └── TrechoRodovia.java
```

### Organização de arquivos da Sprint 3:

```text
sistema-motiva/
├── lib/
│   └── ojdbc17.jar                      # Driver JDBC oficial do Oracle (Java 17+)
├── sql/
│   ├── seu-script-criacao.sql           # DDL: Criação das tabelas no Oracle (IDENTITY, PKs)
│   └── seu-script-dados.sql             # DML: Carga inicial de dados de teste
├── src/
│   └── br/
│       └── com/
│           └── sistemamotiva/
│               ├── db/
│               │   └── ConexaoBanco.java         # Gerenciamento de conexões
│               │
│               │
│               ├── exception/
│               │   └── CredenciaisInvalidasException.java # Exceção customizada (ORA-01017)
│               │
│               ├── model/                        # Camada de Domínio / Regras de Negócio
│               │   ├── IdentificacaoTrecho.java 
│               │   ├── TrechoRodovia.java
│               │   ├── Autoestrada.java
│               │   ├── EstradaVicinal.java
│               │   ├── MonitoravelViaIoT.java
│               │   ├── AutoestradaSensorizada.java
│               │   ├── MockTrechoSensorizado.java
│               │   ├── EquipeManutencao.java
│               │   ├── IntervencaoOperacional.java
│               │   ├── RocadaMecanizada.java
│               │   ├── Pulverizacao.java
│               │   └── Manutencao.java
│               │
│               ├── dao/                          # Camada de Persistência (Data Access Object)
│               │   ├── EquipeManutencaoDAO.java   # CRUD completo para a tabela EQUIPES
│               │   ├── TrechoRodoviaDAO.java      # CRUD com extração polimórfica (TRECHOS)
│               │   ├── IntervencaoOperacionalDAO.java # CRUD para catálogo de INTERVENCOES
│               │   └── RelatorioPrioridadeDAO.java# Persistência de histórico via Java Record
│               │
│               ├── service/                      # Camada de Serviços da Aplicação
│               │   └── GeradorRelatorio.java      # Orquestrador de varredura e persistência
│               │
│               └── main/
│                   └── SistemaMonitoramento.java  # Pista de testes, auditoria e execução CRUD
├── .gitignore
└── README.md
```


## Como executar o projeto no Visual Studio Code? Faça como eu fiz!

### Pré-requisitos
1. Ter o **Java Development Kit (JDK 17+)** instalado.
2. Ter o **VS Code** instalado com a extensão **Extension Pack for Java** (fornecida pela Microsoft).
3. Acesso a um banco de dados **Oracle** (laboratório da faculdade ou versão XE/Docker local).

### Passo 1: Preparar o Banco de Dados
Antes de rodar o código Java, é necessário preparar as tabelas:
1. Abra sua ferramenta de banco de dados (ex: *Oracle SQL Developer for VSCode*, *DBeaver* ou *SQL Developer*).
2. Conecte-se ao seu banco de dados Oracle.
3. Execute o script DDL: 'sql/seu-script-criacao.sql' (Ele apagará versões antigas e recriará as tabelas limpas).
4. Execute o script DML: 'sql/seu-script-dados.sql' (Ele populará o banco com informações de teste).

### Passo 2: Configurar as credenciais no Java
Para que o sistema consiga acessar o banco de dados:
1. Navegue até o arquivo 'src/br/com/sistemamotiva/db/ConexaoBanco.java'.
2. Altere as variáveis contendo a **URL**, **Usuário** e **Senha** para refletir as credenciais do seu banco de dados Oracle.

### Passo 3: Configurar o driver JDBC no VS Code
O projeto necessita do 'ojdbc17.jar' para se comunicar com o banco:
1. No menu lateral esquerdo do VS Code, abra a aba **Java Projects** (fica geralmente na parte inferior do painel Explorer).
2. Expanda o projeto 'sistema-motiva'.
3. Procure por **Referenced Libraries** (Bibliotecas Referenciadas).
4. Clique no ícone de **+** (Add Jar/Folder) que aparece ao passar o mouse por cima.
5. Selecione o arquivo 'ojdbc17.jar' localizado na pasta 'lib/' do projeto.

### Passo 4: Executar a aplicação
1. No explorador de arquivos, abra a classe principal: 'src/br/com/sistemamotiva/main/SistemaMonitoramento.java'.
2. Acima do método 'public static void main(String[] args)', o VS Code exibirá um botão escrito **Run** (Executar).
3. Clique em **Run**.
4. Acompanhe no terminal integrado do VS Code a demonstração completa dos testes de infraestrutura, proteções POO, operações CRUD e geração do relatório em banco!

---

## Destaques Técnicos
- **Design Padrão Single Table:** Utilizamos polimorfismo no banco de dados. Os trechos de rodovia são salvos em uma única tabela, e o DAO recria automaticamente as subclasses ('Autoestrada' ou 'EstradaVicinal') via 'instanceof'.
- **Validação Fail-Fast:** Aplicamos validações rígidas direto no modelo ('Autoestrada', 'Manutencao'). O sistema lança exceções (*IllegalArgumentException*) impedindo que objetos fiquem em estado inválido na memória, protegendo antes mesmo do DAO tentar inserir no banco.
- **Clean Code (Regra de Três Parâmetros):** O *Value Object* 'IdentificacaoTrecho' foi criado especificamente para evitar que 'TrechoRodovia' recebesse múltiplos parâmetros primitivos no construtor.
- **Fechamento Seguro:** Todos os recursos JDBC ('Connection', 'PreparedStatement', 'ResultSet') estão devidamente tratados em blocos 'try/catch/finally' prevenindo vazamento de memória.