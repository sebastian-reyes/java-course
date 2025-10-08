package com.sreyes.primitives;

public class FloatPrimitive {

  static float variable;

  public static void main(String[] args) {
    System.out.println("Float Primitives");
    System.out.println("==================");

    float floatValue = 3.4028235E38f;
    System.out.println("floatValue = " + floatValue);
    System.out.println("float size = " + Float.BYTES);
    System.out.println("float bites = " + Float.SIZE);
    System.out.println("float min = " + Float.MIN_VALUE);
    System.out.println("float max = " + Float.MAX_VALUE);
    System.out.println("==================");

    double doubleValue = 1.7976931348623157E308d;
    System.out.println("doubleValue = " + doubleValue);
    System.out.println("double size = " + Double.BYTES);
    System.out.println("double bites = " + Double.SIZE);
    System.out.println("double min = " + Double.MIN_VALUE);
    System.out.println("double max = " + Double.MAX_VALUE);
    System.out.println("==================");

    System.out.println("variable = " + variable);
  }
}
