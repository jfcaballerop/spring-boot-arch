package com.mrknight.reactiveexample.controller;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

import com.mrknight.reactiveexample.model.responseIdName;
import com.mrknight.reactiveexample.services.ExampleReactiveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
@CrossOrigin
public class ReactiveExController {

	@Autowired
	ExampleReactiveService service;

	// @RequestMapping("/saludo")
	// public Mono<String> Saludo() {
	// return service.Saludo();
	// }

	// @RequestMapping("/despedida")
	// public Mono<String> Adios() {
	// return service.Adios();
	// }

	@RequestMapping(path = "/educado/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<responseIdName> Educado() {

		Flux<responseIdName> flujo = Flux.interval(Duration.ofSeconds(5))
				.map(seq -> {
					responseIdName resp = new responseIdName(new Random().nextInt(5),
							"Data " + new Random().nextInt(5));
					System.out.println(seq + " " + resp);
					return resp;
				});

		// flujo.subscribe(System.out::println);
		return flujo;
	}

}
