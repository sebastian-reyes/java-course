package com.sreyes.numericalSystems;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NumericalSystemsScanner {
  public static void main(String[] args) {
    System.out.println("Numerical Systems");
    System.out.println("==================");

    Scanner scanner = new Scanner(System.in);
    System.out.println("Ingresa un número decimal:");
    int decimal = 0;

    try {
      decimal = scanner.nextInt();
    } catch (InputMismatchException e) {
      System.out.println("Error: Por favor ingresa un número decimal válido.");
      main(args);
      System.exit(0);
    }

    System.out.println("Número decimal = " + decimal);

    String binaryResult = "Número binario de " + decimal + " = " + Integer.toBinaryString(decimal);
    String octalResult = "Número octal de " + decimal + " = " + Integer.toOctalString(decimal);
    String hexadecimalResult = "Número hexadecimal de " + decimal + " = " + Integer.toHexString(decimal);

    String messageFinal = binaryResult + "\n" + octalResult + "\n" + hexadecimalResult;
    System.out.println(messageFinal);
  }
}
