# EcoAudit Pro: Sistema de Governança e Compliance

## 1. Descrição do Projeto
O EcoAudit Pro é uma plataforma mobile de nível corporativo projetada para otimizar processos de auditoria socioambiental e conformidade normativa. O sistema permite a entrada estruturada de dados, processamento de índices de conformidade e o armazenamento seguro de relatórios para rastreabilidade institucional.

## 2. Especificações Técnicas e Stack
* **Plataforma**: Android Nativo.
* **Linguagem**: Kotlin (100% do código-fonte original).
* **Interface**: Jetpack Compose com UI declarativa.
* **Persistência Local**: SQLite através da biblioteca Room Database.
* **Compilação**: Kotlin Symbol Processing (KSP) para geração dinâmica de DAOs.
* **Arquitetura**: MVVM (Model-View-ViewModel) adaptada para fluxos de auditoria.

## 3. Implementação e Persistência (Room)
O projeto utiliza uma estrutura robusta de dados para garantir a integridade dos relatórios salvos:
* **AuditReport**: Entidade de dados que encapsula Código (RV), Empresa, Setor, Data e Score.
* **AuditDao**: Interface de acesso a dados com métodos suspensos para operações assíncronas (Insert, Query, Delete).
* **AuditDatabase**: Singleton para gerenciamento de instância do banco de dados com migração destrutiva segura.

## 4. Padrões de Governança SPBR
Conforme diretrizes de governança estabelecidas, o sistema implementa:
* **Padronização de IDs**: Relatórios identificados pelo código alfa-numérico (ex: RV 001235).
* **Precisão de Dados**: Score de conformidade renderizado com precisão de duas casas decimais.
* **Identidade Visual**: Tipografia e paleta de cores em conformidade com o tom profissional de governança.

## 5. Procedimentos de Build e Instalação
Para a correta execução do ambiente de desenvolvimento:
1. Clonar o repositório master.
2. Executar 'Build > Clean Project'.
3. Executar 'Build > Rebuild Project' para inicializar os processadores KSP.
4. Realizar o Deploy em dispositivo Android (Testado em Samsung SM Series).

## 6. Identificação e Autoria
* **Responsável Técnico**: Patrick Maia.
* **ID Acadêmico**: RM 562748.
* **Instituição**: FIAP - 2026.
