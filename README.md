# Gestor de Frotas

Projeto desenvolvido por **Gustavo Diniz** como projeto final.

## Sobre o projeto

O Gestor de Frotas e uma API para gerenciamento de frotas de caminhoes. O sistema permite cadastrar e consultar motoristas, veiculos e viagens, alem de calcular informacoes de rota para as viagens usando a API da TomTom.

A aplicacao foi criada para organizar dados importantes de uma frota, como placa, modelo, status do veiculo, dados do motorista, origem, destino, distancia, tempo estimado e data prevista de chegada.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- API TomTom
- Bean Validation

## Funcionalidades

- Cadastro de motoristas.
- Listagem, atualizacao e exclusao de motoristas.
- Cadastro de veiculos.
- Listagem, atualizacao e exclusao de veiculos.
- Cadastro de viagens.
- Listagem e atualizacao de viagens.
- Validacao de dados obrigatorios.
- Associacao de uma viagem com motorista e veiculo.
- Busca de coordenadas de origem e destino pela API TomTom.
- Calculo de distancia, duracao e chegada estimada da viagem.

## Arquitetura do sistema

O projeto segue uma arquitetura em camadas, comum em aplicacoes Spring Boot:

```text
Cliente / Front-end / Postman
        |
        v
Controller
        |
        v
Service
        |
        +------------------> TomTomService / API TomTom
        |
        v
Repository
        |
        v
Banco de dados MySQL
```

### Camada Controller

Responsavel por receber as requisicoes HTTP e devolver as respostas da API.

Arquivos:

- `motoristaController.java`
- `veiculoController.java`
- `viagemController.java`

Exemplo de fluxo: quando uma requisicao `POST /motorista` chega na API, o controller recebe os dados do motorista e chama a camada de servico para aplicar as regras antes de salvar.

### Camada Service

Responsavel pelas regras de negocio e validacoes do sistema.

Arquivos:

- `MotoristaService.java`
- `VeiculoService.java`
- `ViagemService.java`
- `TomTomService.java`

Principais responsabilidades:

- Validar dados obrigatorios de motorista, veiculo e viagem.
- Definir status padrao quando necessario.
- Verificar se motorista e veiculo existem antes de cadastrar uma viagem.
- Buscar coordenadas na API TomTom.
- Calcular distancia e tempo estimado da rota.
- Enviar os dados prontos para persistencia.

### Camada Repository

Responsavel pelo acesso ao banco de dados usando Spring Data JPA.

Arquivos:

- `motoristaRepository.java`
- `veiculoRepository.java`
- `viagemRepository.java`

Essas interfaces herdam de `JpaRepository`, permitindo operacoes como salvar, listar, buscar por ID, atualizar e deletar registros.

### Camada Model

Responsavel por representar as entidades do sistema e as tabelas do banco de dados.

Arquivos:

- `motoristaEntity.java`
- `veiculoEntity.java`
- `viagemEntity.java`

Principais entidades:

- `motoristaEntity`: armazena dados do motorista, como nome, CPF, CNH, telefone e e-mail.
- `veiculoEntity`: armazena dados do caminhao, como placa, marca, modelo, tipo, status, capacidade de carga e carroceria.
- `viagemEntity`: armazena os dados da viagem, origem, destino, datas, distancia, tempo estimado, motorista e veiculo.

## Fluxo de cadastro de viagem

O cadastro de viagem e o fluxo mais completo do sistema:

```text
1. Cliente envia POST /viagem
2. viagemController recebe os dados
3. ViagemService valida datas, origem, destino e status
4. ViagemService verifica se motorista e veiculo existem
5. TomTomService busca latitude e longitude da origem e do destino
6. TomTomService calcula distancia e duracao da rota
7. ViagemService preenche distancia, tempo estimado e data de chegada
8. viagemRepository salva a viagem no MySQL
9. API retorna a viagem cadastrada
```

## Endpoints principais

### Motoristas

| Metodo | Rota | Descricao |
| --- | --- | --- |
| POST | `/motorista` | Cadastra um motorista |
| GET | `/motorista` | Lista motoristas |
| PUT | `/motorista/{id}` | Atualiza um motorista |
| DELETE | `/motorista/{id}` | Remove um motorista |

### Veiculos

| Metodo | Rota | Descricao |
| --- | --- | --- |
| POST | `/veiculos` | Cadastra um veiculo |
| GET | `/veiculos` | Lista veiculos |
| PUT | `/veiculos/{id}` | Atualiza um veiculo |
| DELETE | `/veiculos/{id}` | Remove um veiculo |

### Viagens

| Metodo | Rota | Descricao |
| --- | --- | --- |
| POST | `/viagem` | Cadastra uma viagem |
| GET | `/viagem` | Lista viagens |
| PUT | `/viagem/{id}` | Atualiza uma viagem |

## Como executar o projeto

### Pre-requisitos

- Java 21 instalado.
- Maven ou Maven Wrapper.
- Banco MySQL configurado.
- Chave da API TomTom.

### Configuracao

Configure o arquivo `src/main/resources/application.properties` com os dados do banco MySQL e defina a variavel de ambiente da TomTom:

```powershell
$env:TOMTOM_API_KEY="sua-chave-da-api"
```

### Executando

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Em Linux/macOS:

```bash
./mvnw spring-boot:run
```

A aplicacao inicia, por padrao, em:

```text
http://localhost:8080
```

## Estrutura de pastas

```text
src/main/java/com/gestor_frotas/gestor_frotas
|
+-- Controller   # Entrada das requisicoes HTTP
+-- Service      # Regras de negocio, validacoes e integracao com TomTom
+-- Repository   # Comunicacao com o banco de dados
+-- Model        # Entidades JPA do sistema
```

## Observacoes

- O projeto usa MySQL como banco de dados principal.
- As tabelas sao atualizadas automaticamente pelo Hibernate com `spring.jpa.hibernate.ddl-auto=update`.
- Antes de publicar o projeto, revise as configuracoes sensiveis de banco e API para evitar exposicao de credenciais.
