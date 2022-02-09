package com.mrknight.reactiveexample.controller;

import com.mrknight.reactiveexample.services.ExampleReactiveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class ReactiveExController {

  @Autowired
  ExampleReactiveService service;

  @RequestMapping("/saludo")
  public Mono<String> Saludo() {
    return service.Saludo();
  }

  @RequestMapping("/despedida")
  public Mono<String> Adios() {
    return service.Adios();
  }

  @RequestMapping(value = "/educado")
  public Flux<String> Educado() {
    long t1 = System.currentTimeMillis();
    Mono<String> texto1 = service.Saludo();
    System.out.println(texto1);
    Mono<String> texto2 = service.Adios();
    System.out.println(texto2);
    long t2 = System.currentTimeMillis();
    Flux<String> flujo = Flux.merge(texto1, texto2);
    System.out.println(t2 - t1);
    System.out.println(flujo);
    return flujo;
  }

}
