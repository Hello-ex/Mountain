package model;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Namle
 */public class Student implements Serializable{
    private static final long serialVersionUID = 1L;
    private String idStudent;
    private String studentName;
    private String phoneNumber;
    private String email;
    private String mountainCode;
    private double tuitionFee;
    
    public Student(){
        
    }

    public Student(String idStudent, String studentName, String phoneNumber, String email, String mountainCode, double tuitionFee) {
        this.idStudent = idStudent;
        this.studentName = studentName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.mountainCode = mountainCode;
        this.tuitionFee = tuitionFee;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    @Override
    public String toString() {
    return String.format("%-38s | %-20s | %-10s | %-10s | %-10s",
        idStudent, studentName, phoneNumber, mountainCode, String.format("%,.2f", tuitionFee));
    }
    
    
}
