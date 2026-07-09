package com.sreyes;

import com.sreyes.error.FallbackService;
import com.sreyes.error.HandleDisabledVideogame;
import com.sreyes.pipelines.PipelineAllComments;
import com.sreyes.pipelines.PipelineSumAllPricesInDiscount;
import com.sreyes.pipelines.PipelineTopSelling;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  static void main() {
    PipelineTopSelling.getTopSellingGames()
        .subscribe(System.out::println);

    PipelineSumAllPricesInDiscount.getAllPricesInDiscount()
        .subscribe(price -> log.info("Total price of discounted games: ${}", price));

    
    PipelineAllComments.getAllReviewsComments()
        .subscribe(comment -> log.info("Review comment: {}", comment));

    HandleDisabledVideogame.handleDisabledVideogame()
        .subscribe(videogame -> log.info(videogame.toString()));

    FallbackService.callFallback()
        .subscribe(videogame -> log.info(videogame.toString()));
  }
}
