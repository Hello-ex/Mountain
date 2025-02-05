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
    
    public String getInputString(String mess){
        System.out.println(mess);
        return scanner.nextLine();
    }
    
    
    public int getInputInt(String mess){
        int result =0;
        String temp = getInputString(mess);
        if(Acceptable.isValid(temp, Acceptable.INTEGER_VALID))
            result = Integer.parseInt(temp);
        return result;
    }
    
    public int getInputDouble(String mess){
        int result =0;
        String temp = getInputString(mess);
        if(Acceptable.isValid(temp, Acceptable.DOUBLE_VALID))
            result = Integer.parseInt(temp);
        return result;
    }
    
    public String inputAndLoop(String mess, String pattern){
        String result ="";
        boolean more = true;
        do{
            result = getInputString(mess);
            more = !Acceptable.isValid(result, pattern);
            if(more ) System.out.println("Data is invalid!. Re-enter ...");
        }while(more);
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
    
    
    public String inputOptional(String mess, String pattern) {
    String result = "";
    boolean isValid;
    
    do {
        result = getInputString(mess).trim();
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
}