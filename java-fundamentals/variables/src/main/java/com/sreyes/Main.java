package com.sreyes;

public class Main {
  public static void main(String[] args) {

    String greeting = "Hello, World!";
    System.out.println(greeting);
    System.out.println("greeting.toUpperCase() = " + greeting.toUpperCase());

    int number = 10;
    System.out.println("number = " + number);
    System.out.println("number * 2 = " + (number * 2));

    boolean value = true;
    System.out.println("value = " + value);
    System.out.println("!value = " + !value);

    if (value) {
      number = 20;
      System.out.println("The value is true! Number updated to " + number);
    } else {
      System.out.println("The value is false! Number remains " + number);
    }

    var variable = 13;

    String nombre;
    nombre = "Sebastián";

    if (variable > 10) {
      nombre = "Juan";
    }
    System.out.println("nombre = " + nombre);

  }
}