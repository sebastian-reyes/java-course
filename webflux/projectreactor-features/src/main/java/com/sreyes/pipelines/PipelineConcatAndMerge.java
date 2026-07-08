package com.sreyes.pipelines;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class PipelineConcatAndMerge {

  static void main() {
    Flux<String> fluxA = Flux.just("1", "2", "3").delayElements(Duration.ofMillis(100));
    Flux<String> fluxB = Flux.just("a", "b", "c").delayElements(Duration.ofMillis(50));

    Flux<String> mergedFlux = Flux.merge(fluxA, fluxB);
    Flux<String> concatFlux = Flux.concat(fluxB, fluxA);

    mergedFlux.doOnNext(System.out::println).blockLast();
    System.out.println("-------------------");
    concatFlux.doOnNext(System.out::println).blockLast();
  }
}
