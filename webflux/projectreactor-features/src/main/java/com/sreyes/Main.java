package com.sreyes;

import com.sreyes.pipelines.PipelineTopSelling;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  static void main() {
    PipelineTopSelling.getTopSellingGames()
        .subscribe(System.out::println);
  }
}
