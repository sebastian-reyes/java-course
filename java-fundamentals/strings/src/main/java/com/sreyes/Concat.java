package com.sreyes;

public class Concat {
  public static void main(String[] args) {
    String curso = "Programación";
    String lenguaje = "Java";

    String resultado = curso + " " + lenguaje;
    System.out.println("Resultado usando + : " + resultado);

    String resultado2 = curso.concat(" ").concat(lenguaje);
    System.out.println("Resultado usando concat(): " + resultado2);
  }
}
