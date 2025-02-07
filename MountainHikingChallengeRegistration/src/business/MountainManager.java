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
import java.util.List;
import model.Mountain;
import java.util.logging.Logger;
import java.util.logging.Level;

public class MountainManager {
    public ArrayList<Mountain> mountainList = new ArrayList<>();
    private String FILE_PATH = "E:\\FPT\\Spring 2025\\LAB211\\Lab\\MountainHikingChallengeRegistration\\src\\data\\MountainList.csv";

    public MountainManager() {
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
        String mountainCode = fields[0];
        String mountainName = fields[1];
        String province = fields[2];
        String description = fields[3];
        return new Mountain(mountainCode, mountainName, province, description);
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
        int index =1;
        if (moutains == null || moutains.isEmpty()) {
            System.out.println("No data available to display.");
            return;
        }
        
        System.out.println("+----+------------------------+------------+");
        System.out.println(" No  | Mountain Name          | Code   ");
        System.out.println("+----+------------------------+------------+");
        
        for (Mountain moutain : moutains) {
            System.out.printf(" %3s | %-22s | %-10s \n",
                    moutain.getMountainCode(),
                    moutain.getMountain(),
                    moutain.getProvince());
        }
        System.out.println("+----+------------------------+------------+");
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
}
