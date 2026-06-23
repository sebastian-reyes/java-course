package com.sreyes;

import lombok.extern.java.Log;

@Log
public class Main {
  public static void main(String[] args) {

    //Publishers
    final ReactiveStream<String> stringReactiveStream = new ReactiveStream<>();
    final ReactiveStream<Integer> integerReactiveStream = new ReactiveStream<>();

    final String subscriberName1 = "subscriber1";
    final String subscriberName2 = "subscriber2";
    final String subscriberName3 = "subscriber3";
    final String subscriberName4 = "subscriber4";

    //Subscribers for stringReactiveStream
    final Subscriber<String> stringSubscriber1 = new SubscriberImpl<>(
        string -> "Length: " + string.length(),
        subscriberName1
    );
    final Subscriber<String> stringSubscriber2 = new SubscriberImpl<>(
        String::toUpperCase,
        subscriberName2
    );

    //Subscribers for integerReactiveStream
    final Subscriber<Integer> integerSubscriber1 = new SubscriberImpl<>(
        integer -> "Value: " + integer,
        subscriberName3
    );
    final Subscriber<Integer> integerSubscriber2 = new SubscriberImpl<>(
        integer -> "Square: " + (integer * integer),
        subscriberName4
    );

    stringReactiveStream
        .subscribe(stringSubscriber1)
        .subscribe(stringSubscriber2);

    integerReactiveStream
        .subscribe(integerSubscriber1)
        .subscribe(integerSubscriber2);

    log.info("----[Integers]----");
    integerReactiveStream.emit(5);
    integerReactiveStream.emit(10);
    integerReactiveStream.emit(3213123);

    log.info("----[Strings]----");
    stringReactiveStream.emit("sebastian");
    stringReactiveStream.emit("hola mundo :v");
    stringReactiveStream.emit("abcdefghijklm");

    stringReactiveStream
        .unsubscribe(stringSubscriber2);

    integerReactiveStream
        .unsubscribe(integerSubscriber1);

  }
}