package com.sreyes.pipelines;

import reactor.core.publisher.Flux;
import java.time.Duration;

public class PipelineZipAndTuples {

  static void main() {

    Flux<String> fluxShipments = Flux.just("shipment1", "shipment2", "shipment3").delayElements(Duration.ofMillis(120));
    Flux<String> fluxWarehouse = Flux.just("stock1", "stock2", "stock3").delayElements(Duration.ofMillis(50));
    Flux<String> fluxPayments = Flux.just("payment1", "payment2", "payment3").delayElements(Duration.ofMillis(150));
    Flux<String> fluxConfirm = Flux.just("confirm1", "confirm2", "confirm3").delayElements(Duration.ofMillis(20));

    Flux<String> reportFlux = Flux.zip(fluxShipments, fluxWarehouse, fluxPayments, fluxConfirm)
        .map(tuple ->
            tuple.getT1() + ", " +
            tuple.getT2() + ", " +
            tuple.getT3() + ", " +
            tuple.getT4());

    reportFlux
        .doOnNext(System.out::println)
        .blockLast();
  }
}
