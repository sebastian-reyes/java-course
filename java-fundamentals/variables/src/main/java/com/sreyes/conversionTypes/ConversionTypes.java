package com.sreyes.conversionTypes;

public class ConversionTypes {
  public static void main(String[] args) {
    System.out.println("Conversion Types in Java");
    System.out.println("=========================");

    String numberStr = "12345";
    int numberInt = Integer.parseInt(numberStr);
    System.out.println("String to int: " + numberInt);

    String realStr = "123.45";
    double realDouble = Double.parseDouble(realStr);
    System.out.println("String to double: " + realDouble);

    String logicStr = "true";
    boolean logicBool = Boolean.parseBoolean(logicStr);
    System.out.println("String to boolean: " + logicBool);
  }
}
