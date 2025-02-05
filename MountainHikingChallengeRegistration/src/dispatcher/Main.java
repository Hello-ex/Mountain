/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatcher;

import business.StudentManager;
import java.util.Scanner;
import tool.Acceptable;
import tool.Inputter;

/**
 *
 * @author Namle
 */
public class Main {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        Inputter inputScanner = new Inputter();
        int choice = 0;
        
        do{
            System.out.println("1. New Registration");
            System.out.println("2. Update Registration Information");
            System.out.println("3. Display Registered List");
            System.out.println("4. Delete Registration Information");
            System.out.println("5. Search participants by Name");
            System.out.println("6. Filter Data by Campus");
            System.out.println("7. Statistics of Registration Numbers by Locations");
            System.out.println("8. Save Data to File");
            System.out.println("9. Exit the Program");
            choice = inputScanner.getValidChoice(1, 9);
            switch(choice){
                case 1:
                    studentManager.addNewRegistration();
                    break;
                
                case 2:
                    studentManager.updateRegistrationInformation();
                    break;
                
                case 3:
                    studentManager.displayRegisteredList();
                    break;
                    
                case 4:
                    studentManager.deleteRegistrationInformation();
                    break;
                    
                case 5:
                    String nameStudent = inputScanner.inputAndLoop("What's your name?\t", Acceptable.NAME_VALID);
                    studentManager.searchByName(nameStudent);
                    break;
                    
                case 6:
                    studentManager.filterByCampusCode();
                    break;
                case 7:
                    studentManager.statisticalizeByMountainPeak();
                    break;
                    
                case 8:
                    studentManager.saveToFile();
                    break;
                    
                case 9:
                    studentManager.exitTheProgram();
                    break;
                    
            }
    }while(choice<=9 || choice>=1);
        
    }
}
