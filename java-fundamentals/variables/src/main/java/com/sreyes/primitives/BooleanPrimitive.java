package com.sreyes.primitives;

public class BooleanPrimitive {
  public static void main(String[] args) {
    System.out.println("Boolean Primitive");
    System.out.println("==================");

    boolean booleanValue = true;
    System.out.println("booleanValue = " + booleanValue);
    System.out.println("boolean size (not precisely defined) = " + Boolean.TRUE);
    System.out.println("==================");

    boolean isTrue = true;
    boolean isFalse = false;

    System.out.println("isTrue = " + isTrue);
    System.out.println("isFalse = " + isFalse);

    if (isTrue) {
      System.out.println("The condition is true!");
    } else {
      System.out.println("The condition is false!");
    }
    System.out.println("==================");

    boolean logicData = true;
    System.out.println("logicData = " + logicData);

    double d = 98765.43e-3;
    float f = 1.2345e2f;

    System.out.println("d = " + d);
    System.out.println("f = " + f);

    logicData = d > f;
    System.out.println("logicData (d > f) = " + logicData);

    System.out.println("==================");
    boolean isEqual = (3-2 == 1);
    System.out.println("isEqual (3-2 == 1) = " + isEqual);
  }
}
