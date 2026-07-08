package com.sreyes.error;

import com.sreyes.database.Database;
import com.sreyes.model.Console;
import com.sreyes.model.Videogame;
import reactor.core.publisher.Flux;

public class HandleDisabledVideogame {

  public static Flux<Videogame> handleDisabledVideogame() {
    return Database.getDataAsFlux()
        .handle((videogame, sink) -> {
          if (Console.DISABLED == videogame.getConsole()) {
            sink.error(new RuntimeException("Videogame is disabled"));
            return;
          }
          sink.next(videogame);
        })
        .onErrorResume(error -> {
          System.err.println("Error occurred: " + error.getMessage());
          return Flux.merge(
              Database.getDataAsFlux(),
              Database.fluxAssassinsDefault);
        })
        .cast(Videogame.class)
        .distinct(Videogame::getName);
  }
}
