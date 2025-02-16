/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;


import java.util.Scanner;

/**
 *
 * @author Namle
 */
public class Inputter{
    private Scanner scanner;

    public Inputter() {
        this.scanner = new Scanner(System.in);
    }
    
    public String getInputString(String message){
        System.out.println(message);
        return scanner.nextLine();
    }
    
    
    public int getInputInt(String message){
        int result =0;
        String inputString = getInputString(message);
        if(Acceptable.isValid(inputString, Acceptable.INTEGER_VALID))
            result = Integer.parseInt(inputString);
        return result;
    }
    
    public int getInputDouble(String message){
        int result =0;
        String inputString = getInputString(message);
        if(Acceptable.isValid(inputString, Acceptable.DOUBLE_VALID))
            result = Integer.parseInt(inputString);
        return result;
    }
    
    public String inputAndLoop(String message, String pattern){
        String result ="";
        boolean isValidInput = true;
        do{
            result = getInputString(message);
            isValidInput = !Acceptable.isValid(result, pattern);
            if(isValidInput) System.out.println("Data is invalid!. Re-enter ...");
        }while(isValidInput);
        return result.trim();
    }
    
    
    public int getValidChoice(int min, int max){
    int choice =0;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Invalid choice! Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input! Please enter a valid number: ");
            }
        }
    }
    
    
    public String inputOptional(String message, String pattern) {
    String result = "";
    boolean isValid;
    
    do {
        result = getInputString(message).trim();
        if (result.isEmpty()) {
            return result;
        }
        isValid = Acceptable.isValid(result, pattern);
        if (!isValid) {
            System.out.println("Data is invalid! Re-enter or leave blank to skip...");
        }
    } while (!isValid);
    
    return result;
}
    
    public int extractNumberFromCode(String oldCode) {
        if (oldCode.startsWith("MT") && oldCode.length() == 4) {
            return Integer.parseInt(oldCode.substring(2));
        }
        return 0;
    }
    
    public String getFormattedMountainCode(int newNumber, int previousNumber) {
        if (newNumber == 0) {
            return String.format("MT%02d", previousNumber);
        } else {
            return String.format("MT%02d", newNumber);
        }
    }
    
    
    public boolean isValidPrefix(String phonePrefix, String[] prefixes) {
        for (String prefix : prefixes) {
            if (prefix.equals(phonePrefix)) {
                return true;
            }
        }
        return false;
    }
}