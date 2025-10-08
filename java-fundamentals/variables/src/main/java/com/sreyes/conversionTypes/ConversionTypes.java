package com.sreyes.conversionTypes;

public class ConversionTypes {
  public static void main(String[] args) {
    System.out.println("Conversion Types in Java");
    System.out.println("=========================\n");

    System.out.println("Converting from String to int/double/boolean");
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
    System.out.println("=========================\n");


    System.out.println("Converting from int/double to String");
    System.out.println("=========================");
    int intNumber = 100;
    System.out.println("intNumber: " + intNumber);

    String intNumberStr = Integer.toString(intNumber);
    System.out.println("int to String: " + intNumberStr);

    intNumberStr = String.valueOf(intNumber + 10);
    System.out.println("int to String (valueOf): " + intNumberStr);

    double doubleNumber = 123.45;
    String doubleNumberStr = Double.toString(doubleNumber);
    System.out.println("double to String: " + doubleNumberStr);

    doubleNumberStr = String.valueOf(doubleNumber);
    System.out.println("double to String (valueOf): " + doubleNumberStr);

    System.out.println("\nConverting primitive types");
    System.out.println("=========================");

    int i = 22768;
    short s = (short) i; // Explicit conversion
    System.out.println("s = " + s);
    long l = i; // Implicit conversion
    System.out.println("l = " + l);
    System.out.println("Short max value = " + Short.MAX_VALUE);
    char b = (char) i; // Explicit conversion
    System.out.println("b = " + b);
    float f = i; // Implicit conversion
    System.out.println("f = " + f);
    double d = i; // Implicit conversion
    System.out.println("d = " + d);
  }
}
