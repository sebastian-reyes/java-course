package com.sreyes.error;

import com.sreyes.database.Database;
import com.sreyes.model.Console;
import com.sreyes.model.Videogame;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FallbackService {
  public static Flux<Videogame> callFallback() {
    return Database.getDataAsFlux()
        .handle((videogame, sink) -> {
          if (Console.DISABLED == videogame.getConsole()) {
            sink.error(new RuntimeException("Videogame is disabled"));
            return;
          }
          sink.next(videogame);
        })
        .retry(5)
        .onErrorResume(error -> {
          log.error("Database error occurred: {}", error.getMessage());
          return Database.fluxFallback;
        })
        .repeat(1)
        .cast(Videogame.class);
  }
}
