package com.nguyenkim.utils;

public class BMIResult {
    private double BMI;

    public double getBMI() {
        return BMI;
    }

    public BMIResult(double BMI, String description) {
        this.BMI = BMI;
        this.description = description;
    }

    public BMIResult() {
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;
}
