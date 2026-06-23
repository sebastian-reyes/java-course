package com.sreyes;

public interface Subscriber<T> {
  void onNext(T next);
  String getName();
}
