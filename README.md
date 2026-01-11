<div align="center">

# 🏥 HospitalSys

### Sistema de Gerenciamento Hospitalar - Trabalho Final DCC025 UFJF

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-Build-red.svg)](https://maven.apache.org/)

</div>

---

## 💡 Sobre o Sistema

HospitalSys é um sistema acadêmico desenvolvido para simular funcionalidades básicas de gerenciamento hospitalar. O projeto foi criado como trabalho final da disciplina DCC025 na Universidade Federal de Juiz de Fora, com o objetivo de aplicar conceitos fundamentais de programação orientada a objetos em Java.
O sistema implementa diferentes perfis de usuários (secretário, médico e paciente), permitindo explorar conceitos de controle de acesso e organização de funcionalidades específicas para cada tipo de usuário.

---

## 🔧 Stack Tecnológica

O projeto foi construído utilizando:

- **Linguagem:** Java versão 21 
- **Gerenciador de Build:** Maven

---

## 📦 Guia de Instalação e Execução

### Requisitos do Sistema

Antes de iniciar, certifique-se de ter as seguintes ferramentas instaladas:

- Java Development Kit (JDK)
- Maven 
- Terminal de linha de comando compatível

### Processo de Configuração

**Etapa 1: Navegação até o Diretório do Projeto**

Acesse o diretório raiz do **HospitalSys** através do terminal. Para confirmar que você está no local correto, execute o comando apropriado:

<table>
<tr>
<td><strong>Sistema Operacional</strong></td>
<td><strong>Comando</strong></td>
</tr>
<tr>
<td>Linux / macOS</td>
<td><code>ls</code></td>
</tr>
<tr>
<td>Windows</td>
<td><code>dir</code></td>
</tr>
</table>

A listagem deve exibir os seguintes itens essenciais:
- Diretório `src/` (código-fonte)
- Arquivo `pom.xml` (configuração Maven)

**Etapa 2: Compilação do Projeto**

Realize a compilação e empacotamento executando:

```bash
mvn clean install
```

Este processo irá gerar o diretório `target/` contendo o artefato executável `Main-1.0-SNAPSHOT.jar`.

**Etapa 3: Inicialização da Aplicação**

Para iniciar o sistema, execute:

```bash
mvn exec:java
```

O sistema será iniciado e estará pronto para uso.

---

## 🔐 Credenciais de Acesso

O sistema disponibiliza contas pré-configuradas para demonstração e testes de funcionalidades:

### 📋 Perfil Administrativo - Secretário

Responsável por operações administrativas e gerenciamento de registros.

```
📧 E-mail: maria@admin.com
🔑 Senha: admin123
👔 Função: Secretário
```

### 🩺 Perfil Médico

Acesso completo às funcionalidades clínicas e atendimento de pacientes.

```
📧 E-mail: jorge@medico.com
🔑 Senha: medico123
👨‍⚕️ Função: Médico
```

### 🧑‍🤝‍🧑 Perfil Paciente

Interface voltada para consultas, agendamentos e histórico médico.

```
📧 E-mail: rafael@paciente.com
🔑 Senha: paciente123
🏥 Função: Paciente
```

---

<div align="center">

**Desenvolvido com ☕ e dedicação na UFJF**

</div>
