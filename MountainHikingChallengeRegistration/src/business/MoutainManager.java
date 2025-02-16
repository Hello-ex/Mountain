/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Namle
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import model.Mountain;
import tool.Inputter;



public class MoutainManager {
    private Inputter input = new Inputter();
    public ArrayList<Mountain> mountainList = new ArrayList<>();
    private static String FILE_PATH = "src/data/MountainList.csv";
    private String[] VNPT_PREFIXES = {"081", "082", "083", "084", "085", "088", "091", "094"};
    private String[] VIETTEL_PREFIXES = {"032", "033", "034", "035", "036", "037", "038", "039", "096", "097", "098"};

    public MoutainManager() {
    }
    
    public Mountain getMountainByCode(String mountainCode){
        for (Mountain mountain : mountainList) {
            if(mountain.getMountainCode().equals(mountainCode)){
                return mountain;
            }
        }
        return null;
    }
    
    
    public Mountain convertDataToMountain(String text){
        String[] fields = text.split(",");
        String mountainCode = fields[0].trim();
        String mountainName = fields[1].trim();
        String province = fields[2].trim();
        String tuitionFeeRaw = fields[3].trim();
        String description = fields[4].replace(";", System.lineSeparator()).trim();

        double tuitionFee = 0;
        try {
            tuitionFee = Double.parseDouble(tuitionFeeRaw);
        } catch (NumberFormatException e) {
            System.out.println("Invalid tuition fee format: " + tuitionFeeRaw + " -> Defaulting to 0");
        }
        
        return new Mountain(mountainCode, mountainName, province, tuitionFee, description);
    }
    
    public void readFromFile() throws FileNotFoundException, IOException{
        FileReader fileReader = null;
        try{
            File mountainFile = new File(this.FILE_PATH);
            
            if(!mountainFile.exists()){
                System.out.println("MountainList.csv file not found !.");
                return ;
            }
            
            fileReader = new FileReader(mountainFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            String tmp = "";

            while((tmp = bufferedReader.readLine())!=null){
                Mountain mountain = convertDataToMountain(tmp);
                if(mountain!=null) 
                    mountainList.add(mountain);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error: " +ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error: " +ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error: " +ex.getMessage());
        } finally {
            try {
                fileReader.close();
            } 
            catch (IOException ex) {
                System.out.println("Error: " +ex.getMessage());
            }
        }    
    }
    
    
    public String getFormattedMountainCode(int number) {
        return String.format("MT%02d", number);
    }
    
    public void showAll(){
        showAll(this.mountainList);
    }
    
    public void showAll(ArrayList<Mountain> moutains) {
        if (moutains == null || moutains.isEmpty()) {
            System.out.println("No data available to display.");
            return;
        }
        
        System.out.println("+----+------------------------+-------------+-------------------+---------------------------------------------------------------------------------------+");
        System.out.println(" No  | Mountain Name          | Province    | Tuition Fee       | Description                               ");
        System.out.println("+----+------------------------+-------------+-------------------+---------------------------------------------------------------------------------------+");
        
        for (Mountain moutain : moutains) {
            System.out.printf(" %-3s | %-22s | %-10s | %-18.2f | %s\n",
                    moutain.getMountainCode(),
                    moutain.getMountain(),
                    moutain.getProvince(),
                    moutain.getTuitionFee(),
                    moutain.getDescription());
        }
        System.out.println("+----+------------------------+-------------+-------------------+---------------------------------------------------------------------------------------+");
    }
    
    
    public void displayMountainList(){
        try{
            readFromFile();
        }catch(IOException ioException){
            System.out.println("Error reading from file: " + ioException.getMessage());
        }if(mountainList.isEmpty()){
            System.out.println("No data to display");
        }else{
            showAll();
        }
    }
    
    public double calculatTuitionFee(int mountainCode, String phone){
        try{
            readFromFile();
        }catch(IOException iOException){
            System.out.println("Error reading from file: " + iOException.getMessage());
        }
        
        double finalTuitionFee=0;
        boolean found = false;
        for (Mountain mountain : mountainList) {
        if (mountain.getMountainCode().equals(String.valueOf(mountainCode))) {
            found = true;
            if (input.isValidPrefix(phone, VIETTEL_PREFIXES) || input.isValidPrefix(phone, VNPT_PREFIXES)) {
                finalTuitionFee = mountain.getTuitionFee() * 0.65;
            } else {
                finalTuitionFee = mountain.getTuitionFee();
            }
            System.out.println("Tuition: " + finalTuitionFee);
            break; // Thoát khỏi vòng lặp ngay khi tìm thấy
        }
    }

    if (!found) {
        System.out.println("Wrong mountain code or phone");
    }
        return finalTuitionFee;
        
    }
}