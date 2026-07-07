package com.sreyes.pipelines;

import com.sreyes.database.Database;
import com.sreyes.model.Review;
import reactor.core.publisher.Flux;

public class PipelineAllComments {
  /**
   * box -> [item, item, item], box -> [item, item] </br>
   * .map box -> boxWithLabel = 2 items </br>
   * .flatMap: item -> useItem(item) // 5 objects
   */
  public static Flux<String> getAllReviewsComments(){
    return Database.getDataAsFlux()
        .flatMap(game -> Flux.fromIterable(game.getReviews()))
        .map(Review::getComment);
  }
}
