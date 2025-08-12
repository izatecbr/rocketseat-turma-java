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

