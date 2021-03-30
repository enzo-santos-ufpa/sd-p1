# Comunicação por Java RMI

Trabalho de implementação da 1ª avaliação da disciplina de Sistemas Distribuídos.

A aplicação simula um servidor de uma universidade, onde cada usuário possui 
e-mail, nome, endereço, formação acadêmica, habilidades, experiências profissionais
e uma foto de perfil (opcional).

É possível realizar as seguintes operações:

- adicionar um usuário
- listar usuários por curso
- listar habilidades de usuários por endereço
- acrescentar experiência de um usuário
- exibir a experiência de um usuário
- listar informações de um ou de todos os usuários


## Instalação

0. Definir uma variável local para o diretório raiz do projeto (opcional)

**Windows**

```
set ROOT=C:\diretorio\raiz\do\projeto\
```

**Linux**

```
ROOT=/diretorio/raiz/do/projeto/
```

> Será preciso compilar os arquivos de código-fonte
> do projeto para a pasta build/classes/main/java/ no 
> diretório raiz. Utilizando o Gradle, é possível compilar
> utilizando o comando `gradle build`.

1. Iniciar o `rmiregistry`:

**Windows**

```
cd %ROOT%\build\classes\java\main
start rmiregistry ^
    -J-Djava.rmi.server.useCodebaseOnly=false
```

**Linux**

```
cd $ROOT/build/classes/java/main/
rmiregistry \
    -J-Djava.rmi.server.useCodebaseOnly=false &
```

2. Iniciar o servidor RMI:

**Windows**

```
cd %ROOT%\src\main\java
java ^
    -cp %ROOT%\build\classes\java\main ^
    -Djava.rmi.server.codebase=file:\%ROOT%\build\classes\java\main\ ^
    com.example.p1.Server [host]
```

**Linux**

```
cd $ROOT/src/main/java/
java \
    -cp $ROOT/build/classes/java/main/ \
    -Djava.rmi.server.codebase=file:$ROOT/build/classes/java/main/ \
    com.example.p1.Server [host]
```

> A opção `[host]` será o endereço onde o servidor será
>executado, podendo ser **localhost** ou **192.168.0.1**.

3. Iniciar o cliente RMI:

**Windows**

```
cd %ROOT%\src\main\java
java ^
    -cp %ROOT%\build\classes\java\main ^
    com.example.p1.Client [host]
```

**Linux**

```
cd $ROOT/src/main/java/
java \
    -cp $ROOT/build/classes/java/main/ \
    com.example.p1.Client [host]
```
