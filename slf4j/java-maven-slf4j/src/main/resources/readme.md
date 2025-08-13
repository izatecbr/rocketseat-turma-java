🎬 Introdução — Por que Logging é tão importante?

Imagine que você está desenvolvendo um aplicativo Java e, de repente, algo dá errado. Um botão não responde, uma requisição falha, ou um dado desaparece. O que você faz?

A maioria dos iniciantes pensa:
"Vou colocar um System.out.println() aqui pra ver o que está acontecendo..."

Funciona? Às vezes, sim.
Mas em uma aplicação real — com centenas de usuários, rodando em produção — isso não é suficiente.

🤯 Desafios sem um sistema de logging adequado:
* Como saber onde e por que algo deu errado?
* Como registrar erros, alertas, informações úteis?
* Como separar o que é importante do que é ruído?
* Como coletar logs em aplicações distribuídas (como microserviços)?

✅ A resposta: usar um framework de logging profissional.
✨ Entra em cena: SLF4J
O SLF4J (Simple Logging Facade for Java) é uma fachada de logging — ou seja, ele não faz o logging diretamente, mas fornece uma interface padrão para você logar mensagens, enquanto escolhe a implementação que quiser por baixo (como Logback, Log4j, etc.).

Pense no SLF4J como um controle remoto universal:
você escreve uma vez, e pode trocar o "sistema de logging" sem reescrever todo o código.

📦 Nesta aula, você vai aprender a:
* Entender o papel do SLF4J no ecossistema Java
* Configurar o SLF4J em um projeto Maven
* Usar o SLF4J na prática com Spring Boot
* Aplicar níveis de log e boas práticas no dia a dia

## Implementações do SLF4J: Quem faz o trabalho de verdade?
O SLF4J se conecta a implementações reais de logging por trás dos panos. Aqui vão as mais conhecidas:

### Logback (a mais comum com Spring Boot)
* Desenvolvido pelo mesmo autor do Log4j original
* Performance ótima, configuração em XML ou Groovy
* É a implementação padrão no Spring Boot
* Dependência: ch.qos.logback:logback-classic

### Log4j / Log4j2
*  Log4j 1.x é legado (não recomendado)
*  Log4j2 é moderno, rápido e flexível
*  Log4j 2 recebeu atenção especial após o incidente do Log4Shell
*  Dependência: org.apache.logging.log4j:log4j-slf4j-impl

### Java Util Logging (JUL)
* Logging nativo do Java (java.util.logging)
* Pouco usado diretamente hoje em dia

Pode ser adaptado para SLF4J com um "bridge"

### TinyLog, Simple, JBoss Logging (menos comuns)
* Usados em contextos bem específicos
* Também podem ser conectados ao SLF4J

| Implementação | Recomendada para...                       | Observações                       |
| ------------- | ----------------------------------------- | --------------------------------- |
| **Logback**   | Quase tudo, especialmente com Spring Boot | Já vem configurado no Spring Boot |
| **Log4j2**    | Projetos grandes com foco em performance  | Mais complexa, mas muito poderosa |
| **JUL**       | Projetos pequenos ou legados              | Pouco flexível                    |
| **Outras**    | Casos específicos                         | Menos usadas                      |


## Setup - Maven

Como você está usando um projeto Maven puro (sem Spring Boot), é você quem escolhe a implementação do SLF4J. E como mencionou, podemos usar o Log4j2, que é uma escolha moderna, poderosa e flexível.

### Então o nosso setup será:
* SLF4J (interface) + Log4j2 (implementação real)
* Em um projeto Maven puro (sem Spring Boot)

#### Dependências

```xml
<dependencies>
    <!-- SLF4J API: a interface -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>2.0.13</version>
    </dependency>

    <!-- Implementação do SLF4J usando Log4j2 -->
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-slf4j2-impl</artifactId>
        <version>2.23.1</version>
    </dependency>

    <!-- Núcleo do Log4j2 -->
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.23.1</version>
    </dependency>

    <!-- Configuração via XML, JSON ou YAML -->
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-api</artifactId>
        <version>2.23.1</version>
    </dependency>
</dependencies>
```
#### Configuração
Crie um arquivo chamado `log4j2.xml` dentro da pasta:
```css
src/main/resources/
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
        </Console>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console" />
        </Root>
    </Loggers>
</Configuration>
```

#### Exemplo de uso com SLF4J no código:
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Iniciando a aplicação...");
        logger.warn("Isso é um aviso.");
        logger.error("Isso é um erro!");
    }
}

``` 

### 🧭 Níveis de Log do SLF4J (e Log4j2)
Cada nível indica a importância/severidade da mensagem. Use com consciência:

#### TRACE
* Mais detalhado possível.
* Usado para rastrear o passo a passo interno de uma execução.
* Ideal para debugging profundo, como dentro de loops ou chamadas recursivas.

```java
logger.trace("Entrando no método calcularTotal com valores: {}", valores);
```
#### DEBUG
* Detalhado, mas não tão verboso quanto o TRACE.
* Útil durante o desenvolvimento.
* Mostra estados intermediários, valores de variáveis, ou decisões de lógica.

```java
logger.debug("Usuário carregado do banco: {}", usuario);
```

#### INFO
* Informações relevantes para o fluxo normal da aplicação.
* Quando a aplicação inicia, processa algo com sucesso, finaliza uma tarefa etc.

```java
logger.info("Pedido processado com sucesso: {}", pedido.getId());
```

#### WARN
* Algo inesperado aconteceu, mas a aplicação continua funcionando.
* Pode ser um valor inválido, um tempo de resposta alto, ou uma tentativa mal sucedida de acesso.

```java
logger.warn("Tentativa de login com usuário inexistente: {}", email);
```

#### ERROR
* Falha real que impede parte da aplicação de continuar.
* Exceções, falhas em conexões, erros de banco, etc.

```java
logger.error("Erro ao salvar pedido no banco", exception);
```

> 🚨 Deve ser monitorado com alertas em produção.

#### (Opcional) 🔴 FATAL (não é suportado diretamente pelo SLF4J)
* É um nível do Log4j2, usado para erros críticos que exigem parada da aplicação.
* Como o SLF4J não tem esse nível, geralmente se usa logger.error() mesmo.

| Nível | Quando usar                                   | Mostrar em produção?      |
| ----- | --------------------------------------------- | ------------------------- |
| TRACE | Detalhes minuciosos, execuções internas       | ❌                         |
| DEBUG | Informações úteis para devs                   | ⚠️ (desligado por padrão) |
| INFO  | Eventos importantes e bem-sucedidos           | ✅                         |
| WARN  | Algo estranho, mas não quebra                 | ✅                         |
| ERROR | Erros e exceções                              | ✅                         |
| FATAL | (via Log4j2 apenas) Erros que exigem shutdown | ✅                         |

### Definindo Nível de Log

Você define os níveis de log no arquivo de configuração da implementação de logging. No nosso caso: log4j2.xml, já que estamos usando Log4j2 como implementação do SLF4J.

📌 Como funciona?
* Você pode definir o nível de log globalmente (para toda a aplicação)
* Ou definir níveis por pacote ou por classe específica

🎯 Resumo:
* Tudo no projeto segue o nível INFO por padrão (via <Root level="info">)
* No pacote `com.meuprojeto.meupacote`, os logs de `DEBUG` também vão aparecer
* Na classe `com.meuprojeto.meupacote.MinhaClasse`, até logs de `TRACE` serão exibidos

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
    </Appenders>

    <Loggers>
        <!-- Nível global: mostra apenas INFO ou superior -->
        <Root level="info">
            <AppenderRef ref="Console"/>
        </Root>

        <!-- Exibe DEBUG para esse pacote específico -->
        <Logger name="com.meuprojeto.meupacote" level="debug" additivity="false">
            <AppenderRef ref="Console"/>
        </Logger>

        <!-- Exibe TRACE para uma classe específica -->
        <Logger name="com.meuprojeto.meupacote.MinhaClasse" level="trace" additivity="false">
            <AppenderRef ref="Console"/>
        </Logger>
    </Loggers>
</Configuration>
```


## Configuração de Logs com Log4j2 em Aplicações Java (Maven)

Ao desenvolver aplicações Java, é essencial manter um bom sistema de **logs** para registrar eventos, erros e informações úteis para depuração e monitoramento. O **Log4j2** é uma das bibliotecas mais populares para essa finalidade.

A seguir, apresentamos duas formas principais de configurar o Log4j2 para gravar logs em **arquivos**:

### ✅ 1. Log em Arquivo com Caminho Relativo

Essa abordagem salva o log em um caminho **relativo ao diretório onde a aplicação está sendo executada**. É útil quando você quer manter os arquivos de log junto com a aplicação, sem depender de caminhos fixos no sistema.

```xml
<File name="FileLogger" fileName="logs/app.log">
    <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
</File>
```
Ideal para projetos portáteis ou ambientes de desenvolvimento.

* fileName="logs/app.log": grava o log no diretório logs, que será criado no mesmo local onde o .jar da aplicação está sendo executado.

### 📍 2. Log em Arquivo com Caminho Absoluto

Essa alternativa especifica um caminho completo no sistema de arquivos. É útil em ambientes de produção, onde os logs precisam ser salvos em locais padrão do sistema, como `/var/logs` em servidores Linux.
```xml
<File name="FileLogger" fileName="logs/app.log">
    <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
</File>
```

* fileName="/var/logs/minha-app/app.log": grava os logs diretamente no caminho especificado.
* Requer permissões adequadas de escrita no sistema.

### 🎯 Dica
Em ambos os casos, você pode combinar os logs de arquivo com o log no console, bastando adicionar os dois AppenderRef no seu <Root> ou nos loggers específicos:
```xml
<Root level="info">
    <AppenderRef ref="Console" />
    <AppenderRef ref="FileLogger" />
</Root>
```

### Windows

No Windows, o caminho absoluto de arquivos é diferente do Linux. Em vez de usar /var/logs/minha-app/app.log, você usaria um caminho como:

```xml
<File name="FileLogger" fileName="C:/logs/minha-app/app.log">
```

✅ Exemplos de caminhos válidos no Windows:

1. Caminho absoluto em C:

```xml
<File name="FileLogger" fileName="C:/logs/minha-app/app.log">
```
* Cria a pasta C:\logs\minha-app se ela não existir.
* Você precisa ter permissão de escrita nessa pasta.

2. Caminho dentro do diretório do usuário

```xml
<File name="FileLogger" fileName="${sys:user.home}/logs/app.log">
```
* Usa o diretório home do usuário (ex: C:\Users\seunome\logs\app.log).
* Mais seguro para ambientes com múltiplos usuários.

3. Caminho relativo (ainda funciona no Windows)

```xml
<File name="FileLogger" fileName="logs/app.log">
```

* Cria logs/app.log no diretório onde a aplicação for executada.
* Funciona igual tanto no Windows quanto no Linux.