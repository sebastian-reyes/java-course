package com.sreyes.pipelines;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class ColdPublisher {

  static void main() throws InterruptedException {
    Flux<Integer> coldPublisher = Flux.range(1,10);
    Flux<Long> hotPublisher = Flux.interval(Duration.ofSeconds(1))
        .publish()
        .autoConnect();

    log.info(">>>>> Cold publisher <<<<<");

    log.info("Subscriber 1 subscribes to the cold publisher");
    coldPublisher.subscribe(i -> log.info("[Subscriber 1]: {}", i));

    log.info("Subscriber 2 subscribes to the cold publisher");
    coldPublisher.subscribe(i -> log.info("[Subscriber 2]: {}", i));

    log.info("Subscriber 3 subscribes to the cold publisher");
    coldPublisher.subscribe(i -> log.info("[Subscriber 3]: {}", i));

    log.info(">>>>> Hot publisher <<<<<");

    log.info("Subscriber 4 subscribes to the hot publisher");
    hotPublisher.subscribe(i -> log.info("[Subscriber 4]: {}", i));

    Thread.sleep(2000);
    log.info("Subscriber 5 subscribes to the hot publisher");
    hotPublisher.subscribe(i -> log.info("[Subscriber 5]: {}", i));

    Thread.sleep(1000);
    log.info("Subscriber 6 subscribes to the hot publisher");
    hotPublisher.subscribe(i -> log.info("[Subscriber 6]: {}", i));

    Thread.sleep(12000);
  }
}
