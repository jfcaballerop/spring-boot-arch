package com.mrknight.reactiveexample.services;

import java.time.Duration;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class ExampleReactiveService {

  public Mono<String> Saludo() {

    return Mono.just("HOLA sincrono").delayElement(Duration.ofSeconds(5));
  }

  public Mono<String> Adios() {
    return Mono.just("Adios sincrono").delayElement(Duration.ofSeconds(2));

  }

}
