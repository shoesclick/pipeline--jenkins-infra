# Projeto Para efetuar A infra estrutura no Ambiente EKS

Projeto em Grovvy para executar pipeline de apis:

### Pré requisitos

```
Jenkins: (https://www.jenkins.io/download/)

Jenkins cli: (<SUA URL DO JENKINS>/jnlpJars/jenkins-cli.jar)

Docker: (https://www.docker.com/products/docker-desktop/)

Kubernetes: (https://kubernetes.io/releases/download/)

GWAK (https://gnuwin32.sourceforge.net/packages/gawk.htm) - Somente para Windows
```

OBS: O Docker desktop já vem com o Kubernetes instalado.
     Configurar variaveis de Ambiente para o Java, Maven e Gawk

## Estrutura:

```
src : Classes dos projetos
```

```
Var : onde ficará o script do pipeline.
```

## Fluxo:


```
Efetua deploy no Kubernetes dos arquivos de deploy do kubernates.
```

## Configuração:

#### Gerenciar Jenkins >> Credentials >> System >> Global credentials (unrestricted)

Definir:

* **Scope: Global**
* **ID: <definir um id ex: Github-dev>**
* **Username: git**
* **Private Key: <Inserir private key SSH> "**

#### Gerenciar Jenkins >> Credentials >> Users

Definir:


#### Gerenciar Jenkins >> Configurar Sistema (System) >> Global Pipeline Libraries

Definir:

* **Name: jenkinspipelineinfralib - Será utilizado para importar no arquivo Jenkinsfile**
* **Default version: master - branch do projeto**
* **Retrieval method: Modern SCM**
* **Source Code Management: Git**
* **Project Repository: git@github.com:shoesclick/pipeline--jenkins-infra.git - Utilzando o SSH**
* **Credential: git**

#### Gerenciar Jenkins >> Configurar Sistema (System) >> Propriedades globais

## Salvar.

## Criando um Token de usuário para utilziar o Jenkins Cli:

No usuario logado vá em  <seu usuario> >> Configure

Passe de API >> Adicionar novo Passe

DEfina um Nome e clique em "Gerar"

Salvar


## Criando os jobs utilizando o Jenkins Cli:

Importante: Execute os jobs onde se encontram os arquivos xml (diretório jenkins)
            Ou defina o caminho completo do arquivo xml
            Se o jenkins-cli não tiver no diretorio, defina o caminho completo

```
java -jar <CAMINHO_DO_JAR>/jenkins-cli.jar -s http://<URL_JENKINS> -auth <USUARIO_TOKEN>:<TOKEN_GERADO> create-job k8s--infra-deploy < k8s--infra-deploy.xml

``


Referencia: (<SUA URL DO JENKINS>/jnlpJars/jenkins-cli.jar)


