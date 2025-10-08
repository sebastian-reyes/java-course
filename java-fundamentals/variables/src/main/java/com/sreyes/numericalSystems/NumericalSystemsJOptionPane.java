package com.sreyes.numericalSystems;

import javax.swing.*;

public class NumericalSystemsJOptionPane {
  public static void main(String[] args) {
    System.out.println("Numerical Systems");
    System.out.println("==================");

    String numero = JOptionPane.showInputDialog("Ingresa un número decimal:");
    int decimal = 0;

    try {
      decimal = Integer.parseInt(numero);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Error: Por favor ingresa un número decimal válido.");
      System.out.println("Error: Por favor ingresa un número decimal válido.");
      main(args);
      System.exit(0);
    }

    decimal = Integer.parseInt(numero);
    System.out.println("Número decimal = " + decimal);

    String binaryResult = "Número binario de " + decimal + " = " + Integer.toBinaryString(decimal);
    System.out.println(binaryResult);

    String octalResult = "Número octal de " + decimal + " = " + Integer.toOctalString(decimal);
    System.out.println(octalResult);

    String hexadecimalResult = "Número hexadecimal de " + decimal + " = " + Integer.toHexString(decimal);
    System.out.println(hexadecimalResult);

    String messageFinal = binaryResult + "\n" + octalResult + "\n" + hexadecimalResult;
    JOptionPane.showMessageDialog(null, messageFinal);
  }
}
