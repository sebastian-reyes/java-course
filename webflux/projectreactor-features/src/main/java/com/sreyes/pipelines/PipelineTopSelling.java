package com.sreyes.pipelines;

import com.sreyes.database.Database;
import com.sreyes.model.Videogame;
import reactor.core.publisher.Flux;

public class PipelineTopSelling {

  /**
   * return all names of videogames with have a sold > 80
   *
  */
  public static Flux<String> getTopSellingGames() {
    return Database.getDataAsFlux()
        .filter(game -> game.getTotalSold() > 80)
        .map(Videogame::getName);
  }
}
