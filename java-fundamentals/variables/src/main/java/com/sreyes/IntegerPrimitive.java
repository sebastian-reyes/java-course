package com.sreyes;

public class IntegerPrimitive {
  public static void main(String[] args) {

    System.out.println("Integer Primitives");
    System.out.println("==================");

    byte byteValue = 127;
    System.out.println("byteValue = " + byteValue);
    System.out.println("bytes size = " + Byte.BYTES);
    System.out.println("byte bites = " + Byte.SIZE);
    System.out.println("byte min = " + Byte.MIN_VALUE);
    System.out.println("byte max = " + Byte.MAX_VALUE);
    System.out.println("==================");

    short shortValue = 32767;
    System.out.println("shortValue = " + shortValue);
    System.out.println("short size = " + Short.BYTES);
    System.out.println("short bites = " + Short.SIZE);
    System.out.println("short min = " + Short.MIN_VALUE);
    System.out.println("short max = " + Short.MAX_VALUE);
    System.out.println("==================");

    int intValue = 2147483647;
    System.out.println("intValue = " + intValue);
    System.out.println("int size = " + Integer.BYTES);
    System.out.println("int bites = " + Integer.SIZE);
    System.out.println("int min = " + Integer.MIN_VALUE);
    System.out.println("int max = " + Integer.MAX_VALUE);
    System.out.println("==================");

    long longValue = 9223372036854775807L;
    System.out.println("longValue = " + longValue);
    System.out.println("long size = " + Long.BYTES);
    System.out.println("long bites = " + Long.SIZE);
    System.out.println("long min = " + Long.MIN_VALUE);
    System.out.println("long max = " + Long.MAX_VALUE);

    var numberVar = 9223372036854775808d;
  }
}
