package com.sreyes;

public class CharacterPrimitive {
  public static void main(String[] args) {

    System.out.println("Character Primitive");
    System.out.println("==================");

    char charValue = 'A';// char uses single quotes
    System.out.println("charValue = " + charValue);
    System.out.println("char size = " + Character.BYTES);
    System.out.println("char bites = " + Character.SIZE);
    System.out.println("==================");

    char character = '\u0040'; // Unicode for '@'
    char decimal = 64; // Decimal for '@'
    char simbol = '@'; // Direct assignment
    System.out.println("character = " + character);
    System.out.println("decimal = " + decimal);
    System.out.println("simbol = " + simbol);
    System.out.println("Are character and decimal equal? " + (character == decimal));
    System.out.println("Are simbol and character equal? " + (simbol == character));
    System.out.println("==================");

    char space = '\u0020';
    char backspace = '\b';
    char tab = '\t';
    char newLine = '\n';
    char carriageReturn = '\r';
    char formFeed = '\f';

    System.out.println("space = '" + space + "'");
    System.out.println("backspace = '" + backspace + "'");
    System.out.println("tab = '" + tab + "'");
    System.out.println("newLine = '" + newLine + "'");
    System.out.println("carriageReturn = '" + carriageReturn + "'");
    System.out.println("formFeed = '" + formFeed + "'");
  }
}
