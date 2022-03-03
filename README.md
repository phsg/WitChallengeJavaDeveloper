# WitChallengeJavaDeveloper

O objeto do projeto e um projeto Java/Spring com dois modulos distintos com a comunicação entre eles sendo feita pelo RabbitMq.

Links para o desenvolvimento do projeto
----
Criar projeto Spring
https://start.spring.io

----
RabbitMQ

Conhecendo o RabbitMQ
https://www.youtube.com/watch?v=BwQMTRh1hCc&list=PL1OeYyl9zqzHDN67rto7KMtezTLmk1N-K&index=2
https://github.com/DaniloCaneschi/microservico-estoquepreco-rabbitmq

Messaging with RabbitMQ in Spring Boot Application
https://www.appsdeveloperblog.com/messaging-using-rabbitmq-in-spring-boot-application/

SpringBoot+RabbitMQ for RPC calls
https://www.springcloud.io/post/2022-01/springboot-rabbitmq-rpc/

RPC implementation of rabbitmq series
https://developpaper.com/rpc-implementation-of-rabbitmq-series/

Eventual Consistency: Decoupling Microservices with Spring AMQP and RabbitMQ
https://programmaticponderings.com/tag/rabbitmq/

Exchanges
http://nelsonsar.github.io/2013/11/07/RabbitMQ-exchange-types.html

RabbitMQ - O que é? Como funciona? Quando utilizar?
https://www.dio.me/articles/rabbitmq-o-que-e-como-funciona-quando-utilizar

Request/Response Pattern with Spring AMQP
https://reflectoring.io/amqp-request-response/

Rabbit MQ — Parte I
https://medium.com/dev-cave/rabbit-mq-parte-i-c15e5f89d94
RabbitMQ — Parte II

https://medium.com/dev-cave/rabbitmq-parte-ii-fa61a469ba2
----
Log

Utilizando Logging Slf4j com Spring Boot no Java - Vídeo #1
https://www.youtube.com/watch?v=ChIy6-ySpPM
https://gitlab.com/alansouz4/logger.slf4j-spring

HTTP Logging in Spring Boot
https://ozymaxx.github.io/blog/2020/06/19/logback-access-en/
----
MDC
Spring Boot: Setting a unique ID per request
https://medium.com/@d.lopez.j/spring-boot-setting-a-unique-id-per-request-dd648efef2b
---
Correlation Id
Implementando correlation ID em uma aplicação Spring
https://medium.com/@helder.versatti/implementando-correlation-id-em-uma-aplicação-spring-c9c3a92c67e5
-------
Projeto exemplificando a implementação do header X-Correlation-ID em requisições HTTP, usando o Feign.

Para rodar o projeto use: mvn spring-boot:run

Com a aplicação rodando, teremos 2 endpoints para exemplo, /hello, que irá realizar uma chamada para o endpoint /foo . Cada um dos endpoins gera um log com uma mensagem e o X-Correlation-ID para podermos acompanhar o seu funcionamento.
https://github.com/fabiojb/correlation-id-example


=================
Outros
Implementing Correlation IDs in Spring Boot (for Distributed Tracing in SOA/Microservices)
https://dzone.com/articles/implementing-correlation-ids-0

The Value of Correlation IDs
https://www.rapid7.com/blog/post/2016/12/23/the-value-of-correlation-ids/

Logging with Request Correlation using MDC
http://www.javabyexamples.com/logging-with-request-correlation-using-mdc/

[RabbitMQ] Introdução ao mundo das filas
https://imasters.com.br/back-end/rabbitmq-introducao-ao-mundo-das-filas

Guia de Design REST
https://oliveira-michel.github.io/artigos/2019/07/11/guia-de-design-rest.htm

----
Green Summer is a set of different utilities that can be added on top of Spring to augment the basic functionality. It started as a set of controllers and configuration that I felt tempted to copy/paste into each of my projects. So I decided to create a shared library instead. Some of the functionality might be developed in the future in Spring (Boot) itself, and then I'll be more than happy to deprecate it and move along. In the mean time, this is a brief summary of the utilities that one can find in Green Summer
https://github.com/Verdoso/GreenSummer


Projeto exemplificando a implementação do header X-Correlation-ID em requisições HTTP, usando o Feign.
Para rodar o projeto use: mvn spring-boot:run
Com a aplicação rodando, teremos 2 endpoints para exemplo, /hello, que irá realizar uma chamada para o endpoint /foo . Cada um dos endpoins gera um log com uma mensagem e o X-Correlation-ID para podermos acompanhar o seu funcionamento.
https://github.com/fabiojb/correlation-id-example
