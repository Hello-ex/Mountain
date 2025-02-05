package business;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.StatisticalInfo;
import model.Student;
import model.StatisticalInfo;
/**
 *
 * @author Namle
 */
public class StatisticManager extends HashMap<String, StatisticalInfo>{
    
    public final String HEADER_TABLE =
        "--------------------------------------------------------------\n" +
        "| Peak Name     | Number of Participants | Total Cost        |\n" +
        "--------------------------------------------------------------";

    private final String FOOTER_TABLE =
        "--------------------------------------------------------------";

    public StatisticManager() {
        super();
    }
    
   public StatisticManager(ArrayList<Student> registrationList){
       super();
       statisticalize(registrationList);
   }
   

    public final void statisticalize(ArrayList<Student> registrationList) {
        for (Student students : registrationList) {
            if (this.containsKey(students.getMountainCode())) {
                StatisticalInfo statisticalInfo = this.get(students.getMountainCode());
                statisticalInfo.setNumOfStudent(statisticalInfo.getNumOfStudent()+ 1);
                statisticalInfo.setTotalCost(statisticalInfo.getTotalCost() + students.getTuitionFee());
            } else {
                StatisticalInfo newStatisticalInfo = new StatisticalInfo(students.getMountainCode(),
                                                        1 , students.getTuitionFee());
                this.put(students.getMountainCode(), newStatisticalInfo);
            }
        }
    }



    public void show() {
    System.out.println(HEADER_TABLE);
        for(StatisticalInfo statisticalInfo : this.values()){
            System.out.println(statisticalInfo);
        }
    System.out.println(FOOTER_TABLE);
}
}
