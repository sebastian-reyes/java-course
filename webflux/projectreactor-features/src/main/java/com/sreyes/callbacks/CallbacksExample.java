package com.sreyes.callbacks;

import com.sreyes.database.Database;
import com.sreyes.model.Videogame;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class CallbacksExample {

  public static Flux<Videogame> callbacks(){
    return Database.getDataAsFlux()
        .doOnSubscribe(subscription -> log.info("[doOnSubscribe]: {}", subscription))
        .doOnRequest(request -> log.info("[doOnRequest]: {}", request))
        .doOnNext(videogame -> log.info("[doOnNext]: {}", videogame.getName()))
        .doOnCancel(() -> log.warn("[doOnCancel]: Subscription has been canceled"))
        .doOnError(error -> log.error("[doOnError]: {}", error.getMessage()))
        .doOnComplete(() -> log.info("[doOnComplete]: All items have been processed"))
        .doOnTerminate(() -> log.info("[doOnTerminate]: Stream has terminated"))
        .doFinally(signalType -> log.info("[doFinally]: {}", signalType));
  }
}
