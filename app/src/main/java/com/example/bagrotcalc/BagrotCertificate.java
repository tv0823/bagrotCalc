package com.example.bagrotcalc;

public class BagrotCertificate {
    private int subjectsCount;
    private BagrotGrade[] grades;

    public BagrotCertificate(int subjectsCount, BagrotGrade[] grades){
        this.subjectsCount = subjectsCount;
        this.grades = grades;
    }

    public String toString() {
        String summarise = "";
        for(int i = 0; i<subjectsCount; i++) {
            summarise += grades[i] + "\n";
        }
        return summarise;
    }
}
