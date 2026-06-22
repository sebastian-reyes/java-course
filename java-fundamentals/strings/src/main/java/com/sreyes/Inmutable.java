package com.sreyes;

public class Inmutable {
  public static void main(String[] args) {
    String curso = "Programación Java";
    System.out.println("Curso inicial: " + curso);

    curso = curso.replace("Java", "Java SE");
    System.out.println("Curso modificado: " + curso);
  }
}
