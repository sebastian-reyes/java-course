package com.sreyes.exercise;

import java.util.Scanner;

public class InvoiceDetailExercise {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the product name:");
    System.out.println("=========================");
    String productName = scanner.nextLine();

    System.out.println("Enter the first product price:");
    System.out.println("=========================");
    double productPrice = scanner.nextDouble();

    System.out.println("Enter the second product price:");
    System.out.println("=========================");
    double productPrice2 = scanner.nextDouble();

    double grossPrice = productPrice + productPrice2;
    double tax = grossPrice * 0.19;
    double totalPrice = grossPrice + tax;

    System.out.println("\nInvoice Detail");
    System.out.println("=========================");
    System.out.println("Product: " + productName);
    System.out.println("Price 1: $" + productPrice);
    System.out.println("Price 2: $" + productPrice2);
    System.out.println("-------------------------");
    System.out.println("Gross Price: $" + grossPrice);
    System.out.println("Tax (19%): $" + tax);
    System.out.println("Total Price: $" + totalPrice);
  }
}
