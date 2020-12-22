# Comunicação por Java RMI

Trabalho de implementação da 1ª avaliação da disciplina de Sistemas Distribuídos.

## Uso

### Windows

0. Definir uma variável local para o diretório raiz do projeto (opcional)

```
set ROOT=C:\diretorio\raiz\do\projeto\
```

1. Iniciar o `rmiregistry`:

```
cd %ROOT%\build\classes\java\main
start rmiregistry ^
    -J-Djava.rmi.server.useCodebaseOnly=false
```

2. Iniciar o servidor RMI:

```
cd %ROOT%\src\main\java
java ^
    -cp %ROOT%\build\classes\java\main ^
    -Djava.rmi.server.codebase=file:\%ROOT%\build\classes\java\main\ ^
    com.example.p1.Server localhost
```

3. Iniciar o cliente RMI:

```
cd %ROOT%\src\main\java
java ^
    -cp %ROOT%\build\classes\java\main ^
    com.example.p1.Client localhost
```

### Linux

0. Definir uma variável local para o diretório raiz do projeto (opcional)

```
ROOT=/diretorio/raiz/do/projeto/
```

1. Iniciar o `rmiregistry`:

```
cd $ROOT/build/classes/java/main/
rmiregistry \
    -J-Djava.rmi.server.useCodebaseOnly=false &
```

2. Iniciar o servidor RMI:

```
cd $ROOT/src/main/java/
java \
    -cp $ROOT/build/classes/java/main/ \
    -Djava.rmi.server.codebase=file:$ROOT/build/classes/java/main/ \
    com.example.p1.Server localhost
```

3. Iniciar o cliente RMI:

```
cd $ROOT/src/main/java/
java \
    -cp $ROOT/build/classes/java/main/ \
    com.example.p1.Client localhost
```