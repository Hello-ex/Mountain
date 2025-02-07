package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import model.Student;
import tool.Acceptable;
import tool.Inputter;
import java.io.EOFException;
import java.util.List;

public class StudentManager{
    private final Scanner inputScanner = new Scanner(System.in);
    private final MountainManager mountainManager = new MountainManager();
    private final StatisticManager statisticManager = new StatisticManager();
    private final Inputter input = new Inputter();
    private final double TUITION_FEE = 6000000.0;
    private boolean isSaved=true;
    public final ArrayList<Student> registrationList = new ArrayList<>();
    public final ArrayList<Student> unSavedData = new ArrayList<>();
    private static final String FILE_PATH = "E:\\FPT\\Spring 2025\\LAB211\\Lab\\MountainHikingChallengeRegistration\\src\\data\\registrations.dat";
    private String[] VNPT_PREFIXES = {"081", "082", "083", "084", "085", "088", "091", "094"};
    private String[] VIETTEL_PREFIXES = {"032", "033", "034", "035", "036", "037", "038", "039", "096", "097", "098"};
    public final String HEADER_TABLE =    "-------------------------------------------------------------------------------------------------------\n"
                                        + String.format("%-38s | %-20s | %-10s | %-10s | %-10s%n", "Student ID", "Name", "Phone", "Peak Code", "Fee")
                                        + "-------------------------------------------------------------------------------------------------------";
    public final String FOOTER_TABLE =    "-------------------------------------------------------------------------------------------------------";
    
    public boolean isSaved(){
        return isSaved;
    }
    
    private boolean isValidPrefix(String phonePrefix, String[] prefixes) {
    for (String prefix : prefixes) {
        if (prefix.equals(phonePrefix)) {
            return true;
        }
    }
    return false;
    }
    
    public String generateStudentID(){
        String studentUuid =UUID.randomUUID().toString();
        return studentUuid;
    }
    
    public Student searchById(String id){
        try{
            readFromFile();
        }catch(IOException iOException){
            System.out.println("Error reading from file: " + iOException.getMessage());
        }
        
        for (Student student : registrationList) {
            if(student.getIdStudent().equals(id)){
                return student;
            }
        }
        System.out.println("This student has not registred yet.");
        return null;
    }
    
    public void readFromFile() throws IOException {
        File file = new File(this.FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            System.out.println("No data file or the file is empty.");
            return;
        }
        registrationList.clear();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Student student = (Student) objectInputStream.readObject();
                    registrationList.add(student);
                } catch (EOFException ex) {
                    break;
                }
            }
            System.out.println("Data loaded successfully");
            this.isSaved = true;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found" + ex);
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("IO error: " + ex.getMessage());
        }
}
    
    public void showAll(){
        showAll(this.registrationList);
    }
    
    public void showAll(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("No data available to display.");
            return;
        }
        
        System.out.println(HEADER_TABLE);
        
        for(Student student : students) {
            System.out.printf("%-38s | %-20s | %-10s | %-10s | %,10.2f\n",
                    student.getIdStudent(),
                    student.getStudentName(),
                    student.getPhoneNumber(),
                    student.getMountainCode(),
                    student.getTuitionFee());
        }
        System.out.println(FOOTER_TABLE);
    }
    
    //Function 1: New Registration
    public void addNewRegistration(){
        try{
            readFromFile();
        }catch(IOException iOException){
            System.out.println("Error reading from file: " + iOException.getMessage());
        }
        String idStudent="";
        double finalTuitionFee;
        String userResponse;
        
        System.out.println("All campuses");
        System.out.println("1. CE: Can Tho\n" 
                         + "2. DE: Da Nang\n"
                         + "3. HE: Ha Noi\n"
                         + "4. SE: Ho Chi Minh\n"
                         + "5. QE: Quy Nhon");
        
            int choice =input.getValidChoice(1, 5);
            switch(choice){
                case 1:
                    idStudent= "CE"+generateStudentID();
                    break;
                
                case 2:
                    idStudent= "DE"+generateStudentID();
                    break;
                
                case 3:
                    idStudent= "HE"+generateStudentID();
                    break;
                    
                case 4:
                    idStudent= "SE"+generateStudentID();
                    break;
                    
                case 5:
                    idStudent= "QE"+generateStudentID();
                    break;
            }
        System.out.println("Your id: " + idStudent);
        String nameStudent = input.inputAndLoop("What is your name? ", Acceptable.NAME_VALID);
        String phoneNumber = input.inputAndLoop("What is your phone number? ", Acceptable.PHONE_VALID);
        String emailStudent = input.inputAndLoop("What is your email address? ", Acceptable.EMAIL_VALID);
        mountainManager.displayMountainList();
        System.out.print("Enter mountain code: ");
        String mountainCode = mountainManager.getFormattedMountainCode(input.getValidChoice(1, 13));
        String prefix = phoneNumber.substring(0, 3);
        if (isValidPrefix(prefix, VIETTEL_PREFIXES) || isValidPrefix(prefix, VNPT_PREFIXES)) {
            finalTuitionFee = TUITION_FEE * 0.65;
            System.out.println("Tuition: "+finalTuitionFee);
        }else{
            finalTuitionFee=TUITION_FEE;
            System.out.println("Tuition: " + finalTuitionFee);
        }
        
        Student newStudent = new Student(idStudent, nameStudent, phoneNumber,emailStudent,mountainCode,finalTuitionFee);
        
        
        
        System.out.print("Do you want to save the information?(Y/N) \t");
        userResponse = inputScanner.nextLine();
        if(userResponse.toLowerCase().contains("y")){
            registrationList.add(newStudent);
            saveToFile();
        }else{
            unSavedData.add(newStudent);
            isSaved = false;
        }
    }
    
    
    //Function 2: Update Registration Information
    public void updateRegistrationInformation(){
        try{
            readFromFile();
        }catch(IOException iOException){
            System.out.println("Error reading from file: " + iOException.getMessage());
        }
        double finalTuitionFee;
        String userResponse;
        
        String idStudent = input.inputAndLoop("Enter your id: ", Acceptable.STU_ID_VALID);
        Student foundStudent = searchById(idStudent);
        
        System.out.println(HEADER_TABLE);
        
        System.out.println(foundStudent);
        
        System.out.println(FOOTER_TABLE);
        
        String newNameStudent = input.inputOptional("What's your name? ", Acceptable.NAME_VALID);
        if (!newNameStudent.isEmpty()) foundStudent.setStudentName(newNameStudent);
        
        String newPhoneNumber = input.inputOptional("What is your phone number? ", Acceptable.PHONE_VALID);
        if (!newPhoneNumber.isEmpty()){
            foundStudent.setPhoneNumber(newPhoneNumber);
            
                String prefix = newPhoneNumber.substring(0, 3);
            if (isValidPrefix(prefix, VIETTEL_PREFIXES) || isValidPrefix(prefix, VNPT_PREFIXES)) {
                finalTuitionFee = TUITION_FEE * 0.65;
                System.out.println("Tuition: "+finalTuitionFee);
            }else{
                finalTuitionFee=TUITION_FEE;
                System.out.println("Tuition: " + finalTuitionFee);
            }

            foundStudent.setTuitionFee(finalTuitionFee);
        }
        
        
        String newEmail = input.inputOptional("What is your email address? ", Acceptable.EMAIL_VALID);
        if (!newEmail.isEmpty()) foundStudent.setEmail(newEmail);
         
        //mountaincode
        int previousMountainCode = input.extractNumberFromCode(foundStudent.getMountainCode());
        int newMountainNumber = input.getInputInt("Enter mountain code: ");
        String newMountainCode = input.getFormattedMountainCode(newMountainNumber, previousMountainCode);
        foundStudent.setMountainCode(newMountainCode);
        
        System.out.print("Do you want to save the information?(Y/N) \t");
        userResponse = inputScanner.nextLine().trim().toLowerCase();
        if(userResponse.contains("y")){
            saveToFile();
            System.out.println("Your information has been updated successfully.");
        }else{
            isSaved=false;
        }
    }
    
    //Function 3: Display Registered List
    public void displayRegisteredList(){
        try{
            readFromFile();
        }catch(IOException ioException){
            System.out.println("Error reading from file: " + ioException.getMessage());
        }if(registrationList.isEmpty()){
            System.out.println("No data to display");
        }else{
            showAll();
        }
    }
    
    //Function 4: Delete Registration Information
    public void deleteRegistrationInformation(){
        String idStudent = input.inputAndLoop("Enter your id: ", Acceptable.STU_ID_VALID);
        Student foundStudent = searchById(idStudent);
        String userResponse;
        
        
        System.out.println(FOOTER_TABLE);
        
        System.out.println("Student ID: "+ foundStudent.getIdStudent());
        System.out.println("Name      : "+ foundStudent.getStudentName());
        System.out.println("Phone     : "+ foundStudent.getPhoneNumber());
        System.out.println("Mountain  : "+ foundStudent.getMountainCode());
        System.out.println("Fee       : "+ String.format("%,9.0f",foundStudent.getTuitionFee()));
        
        System.out.println(FOOTER_TABLE);
        
        System.out.print("Are you sure you want to delete this registration?(Y/N):  \t");
        userResponse = inputScanner.nextLine().trim().toLowerCase();
        if(userResponse.equalsIgnoreCase("Y")){
            registrationList.remove(foundStudent);
            System.out.println("Student record deleted successfully.");
        }else{
            System.out.println("You have chosen not to delete your registration information.");
        }
        
        saveToFile();
    }
    
    //Function 5: Search Participants by Name
    public void searchByName(String name){
        try{
            readFromFile();
        }catch(IOException iOException){
            System.out.println("Error reading from file: " + iOException.getMessage());
        }
        
        List<Student> matchedStudents = new ArrayList<>();
        
        for (Student student : registrationList) {
            if(student.getStudentName().trim().toLowerCase().contains(name.trim().toLowerCase())){
                matchedStudents.add(student);
            }
        }
        if(!matchedStudents.isEmpty()){
                System.out.println(HEADER_TABLE);
            for(Student student : matchedStudents) {
                    System.out.println(student);
            }
            System.out.println(FOOTER_TABLE);
        }else{
            System.out.println("No one matches the search criteria!");
        }
    }
    
    //Function 6: Filter Data by Campus
    public void filterByCampusCode(){
            System.out.println("=== Filter Data by Campus ===");
            System.out.println("Available Campus Codes:");
            System.out.println("CE: Can Tho");
            System.out.println("DE: Da Nang");
            System.out.println("HE: Ha Noi");
            System.out.println("SE: Ho Chi Minh");
            System.out.println("QE: Quy Nhon");
            System.out.print("Enter Campus Code: ");
            String campusCode = inputScanner.nextLine().trim().toUpperCase();
            
            if (!campusCode.matches("^(CE|DE|HE|SE|QE)$")) {
                System.out.println("Invalid campus code. Please try again.");
                return;
            }
            
            
            ArrayList<Student> filteredStudents = new ArrayList<>();
            for (Student student : registrationList) {
                if (student.getIdStudent().startsWith(campusCode)) {
                    filteredStudents.add(student);
                }
            }
            
            if (filteredStudents.isEmpty()) {
                System.out.println("No students have registered under this campus.");
            } else {
                showAll(filteredStudents); // Calls the overloaded method
            }
        }
    
    
    //Function 7: StatisticManager of Registration Numbers by Location
    public void statisticalizeByMountainPeak(){
        try{
            readFromFile();
        }catch(IOException iOException){
            System.out.println("Error reading from file: " + iOException.getMessage());
        }
        statisticManager.clear();
        
        statisticManager.statisticalize(registrationList);
        
        statisticManager.show();
    }
    
    //Function 8: 
    public void saveToFile() {
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(this.FILE_PATH);
            fileOutputStream = new FileOutputStream(file);
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                for (Student student : registrationList) {
                    objectOutputStream.writeObject(student);
                }
            }
            System.out.println("Registration data has been successfully saved to `registrations.dat`.");
            this.isSaved = true;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IO error: " + ex.getMessage());
        } finally {
            try {
                if (fileOutputStream != null) fileOutputStream.close();
            } catch (IOException ex) {
                System.out.println("Error closing file: " + ex.getMessage());
            }
        }
}
    
    //Function 9: Exit the Program
    public void exitTheProgram() {
    if (!isSaved) {
        showAll(unSavedData);
        System.out.println("You have unsaved changes. Are you sure you want to exit without saving? (Y/N): ");
        String response = inputScanner.nextLine().trim().toLowerCase();
        
        if (response.equals("y")) {
            System.out.println("Exiting the program without saving...");
            System.exit(0);
        } else if (response.equals("n")) {
            registrationList.addAll(unSavedData);
            saveToFile();
            System.out.println("Changes saved. Exiting the program...");
            System.exit(0);
        } else {
            System.out.println("Invalid input. Returning to main menu...");
            return;
        }
    } else {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
}
