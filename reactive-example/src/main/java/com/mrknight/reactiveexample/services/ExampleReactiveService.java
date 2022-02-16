package com.mrknight.reactiveexample.services;

import java.time.Duration;

import com.mrknight.reactiveexample.model.responseIdName;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class ExampleReactiveService {

	public Mono<responseIdName> Saludo() {

		return Mono.just(new responseIdName(1, "HOLA sincrono")).delayElement(Duration.ofSeconds(5));
	}

	public Mono<responseIdName> Adios() {
		return Mono.just(new responseIdName(2, "Adios sincrono")).delayElement(Duration.ofSeconds(2));

	}

}
