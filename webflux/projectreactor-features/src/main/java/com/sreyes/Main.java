package com.sreyes;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Main {

  static void main() {
    //Publisher
    Mono<String> mono = Mono.just("Hello, World!")
        .doOnNext(value -> log.info("[onNext] {}", value))
        .doOnSuccess(value -> log.info("[onSuccess] {}", value))
        .doOnError(error -> log.info("[onError] {}", error.getMessage()));

    //Consumer
    mono.subscribe(
        data -> log.info("receiving data: {}", data),
        err -> log.info("receiving error: {}", err.getMessage()),
        () -> log.info("complete success!")
    );
  }
}
