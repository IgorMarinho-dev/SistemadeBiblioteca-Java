# Sistema de Gerenciamento de Biblioteca

## Sobre o Projeto

O Sistema de Gerenciamento de Biblioteca foi desenvolvido com o objetivo de auxiliar na administração de bibliotecas físicas, permitindo o controle de livros, exemplares, usuários, empréstimos, devoluções e reservas.

O sistema busca facilitar a organização do acervo e otimizar os processos de gerenciamento da biblioteca.

---

## Funcionalidades

- Cadastro de livros
- Gerenciamento de usuários
- Controle de empréstimos
- Sistema de reservas
- Consulta de disponibilidade de exemplares

---

## Diagrama de Classes

O sistema possui as seguintes entidades principais:

- Livro
- Usuário
- Bibliotecário
- Empréstimo
- Reserva

---

## Tecnologias Utilizadas

- Java
- UML
- Git e GitHub

---

## Estrutura do Projeto

```text
src/
├── model/
│   ├── Bibliotecario.java
│   ├── Categoria.java
│   ├── Cliente.java
│   ├── Multa.java
│   ├── Livro.java
│   ├── Exemplar.java
│   ├── Usuario.java
│   ├── Emprestimo.java
│   └── Reserva.java
│
├── enums/
│   ├── StatusExemplar.java
│   └── StatusReserva.java
│
└── main/
    └── Main.java
```

## Como Executar

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
```

2. Abra o projeto na IDE.

3. Execute a classe `Main.java`.

---

## Projeto Acadêmico

Desenvolvido para a disciplina de Programação Orientada a Objetos.

Ano: 2026
