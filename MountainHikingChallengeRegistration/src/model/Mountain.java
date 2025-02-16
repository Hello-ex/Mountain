package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Namle
 */
public class Mountain {
    private String mountainCode;
    private String mountain;
    private String province;
    private double tuitionFee;
    private String description;

    public Mountain() {
    }

    public Mountain(String mountainCode, String mountain, String province,double tuitionFee, String description) {
        this.mountainCode = mountainCode;
        this.mountain = mountain;
        this.province = province;
        this.tuitionFee=tuitionFee;
        this.description = description;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public String getMountain() {
        return mountain;
    }

    public void setMountain(String mountain) {
        this.mountain = mountain;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Mountain Code: " + mountainCode + "| Mountain: " + mountain + "| Province: " + province +"| Tuition Fee: "+tuitionFee +"| Description: " + description;
    }
    
    
}
