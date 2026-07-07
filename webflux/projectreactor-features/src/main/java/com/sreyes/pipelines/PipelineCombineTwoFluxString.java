package com.sreyes.pipelines;

import reactor.core.publisher.Flux;

public class PipelineCombineTwoFluxString {

  static void main() {
    Flux<String> fluxA = Flux.just("a", "b");
    Flux<String> fluxB = Flux.just("c", "d");

    Flux<String> combinedFlux = fluxA
        .flatMap(v1 -> fluxB
            .map(v2 -> v1 + "-" + v2));

    combinedFlux
        .map(String::toUpperCase)
        .doOnNext(System.out::println)
        .subscribe();
  }
}
