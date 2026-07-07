package com.sreyes.pipelines;

import com.sreyes.database.Database;
import com.sreyes.model.Videogame;
import reactor.core.publisher.Mono;

public class PipelineSumAllPricesInDiscount {

  /**
   * return sum of prices of videogames with have discount
   */
  public static Mono<Double> getAllPricesInDiscount() {
    return Database.getDataAsFlux()
        .filter(Videogame::getIsDiscount)
        .map(Videogame::getPrice)
        .reduce(Double::sum);
  }
}
