package com.sreyes;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class Main {

  static void main() {
    //Publisher
    Mono<String> mono = Mono.just("Hello, World!")
        .doOnNext(value -> log.info("[Mono][onNext] {}", value))
        .doOnSuccess(value -> log.info("[Mono][onSuccess] {}", value))
        .doOnError(error -> log.info("[Mono][onError] {}", error.getMessage()));

    //Consumer
    mono.subscribe(
        data -> log.info("[Mono] receiving data: {}", data),
        err -> log.info("[Mono] receiving error: {}", err.getMessage()),
        () -> log.info("[Mono] complete success!")
    );

    Flux<String> flux = Flux.just("java", "spring", "reactor")
        .doOnNext(value -> log.info("[Flux][onNext] {}", value))
        .doOnComplete(() -> log.info("[Flux][onComplete]"))
        .doOnError(error -> log.info("[Flux][onError] {}", error.getMessage()));

    flux.subscribe(
        data -> log.info("[Flux] receiving data: {}", data),
        err -> log.info("[Flux] receiving error: {}", err.getMessage()),
        () -> log.info("[Flux] complete success!")
    );
  }
}
