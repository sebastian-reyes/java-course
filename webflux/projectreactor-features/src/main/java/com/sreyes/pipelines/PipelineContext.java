package com.sreyes.pipelines;

import com.sreyes.database.Database;
import com.sreyes.model.Console;
import com.sreyes.model.Videogame;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@Slf4j
public class PipelineContext {

  static void main() {
    Database.getDataAsFlux()
        .filterWhen(videogame -> Mono.deferContextual(context -> {
          var userId = context.getOrDefault("userId", "0");
          if (userId.startsWith("1")) {
            return Mono.just(videogameForConsole(videogame, Console.XBOX));
          } else if (userId.startsWith("2")) {
            return Mono.just(videogameForConsole(videogame, Console.PLAYSTATION));
          }
          return Mono.just(false);
        }))
        .contextWrite(Context.of("userId", "12374"))
        .subscribe(videogame -> log.info("Recommend Videogame: {}, Console {}",
            videogame.getName(),
            videogame.getConsole()));
  }

  private static boolean videogameForConsole(Videogame videogame, Console console) {
    return videogame.getConsole() == console || videogame.getConsole() == Console.ALL;
  }
}
