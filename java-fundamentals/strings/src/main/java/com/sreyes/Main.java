package com.sreyes;

public class Main {
  public static void main(String[] args) {
    String curso = "Programación Java";
    String curso2 = new String("Programación Java");
    String curso3 = "Programación Java";
    System.out.println("curso 1: " + curso);
    System.out.println("curso 2: " + curso2);
    System.out.println("curso 3: " + curso3);
    System.out.println("------------------------");

    boolean esIgual = curso.equals(curso2);
    System.out.println("¿El texto es igual? " + esIgual);

    boolean esMismaReferencia = curso == curso2;
    System.out.println("¿Son la misma referencia? " + esMismaReferencia);

    boolean esMismaReferencia2 = curso == curso3;
    System.out.println("¿curso y curso3 tienen la misma referencia? " + esMismaReferencia2);
  }
}