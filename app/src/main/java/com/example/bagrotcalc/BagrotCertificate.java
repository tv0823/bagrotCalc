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

    public Double bagrotAvg() {
        double sum = 0;
        int unitCount = 0;
        for(int i = 0; i<subjectsCount; i++) {
            sum += grades[i].gradeWithBonus() * grades[i].getLevel();
            unitCount += grades[i].getLevel();
        }
        if(subjectsCount == 10) {
            sum += grades[9].gradeWithBonus() * 5;
            unitCount += grades[9].getLevel();
        }
        return sum/unitCount;
    }

    private int sumRequiredSubjects() {
        int sum = 0;
        for(int i = 0; i<7; i++) {
            sum += grades[i].gradeWithBonus() * grades[i].getLevel();
        }
        return sum;
    }

    public String allAvg() {
        String averages = "";
        double sum = 0;

        int unitCount = 0;
        for(int i = 0; i<7; i++) {
            unitCount += grades[i].getLevel();
        }
        if(subjectsCount == 9) {
            if(grades[8].getLevel() == 1) {
                averages += bagrotAvg() + "\n";
            } else {
                for(int i = 7; i<subjectsCount; i++) {
                    sum = sumRequiredSubjects();
                    sum += grades[i].gradeWithBonus() * grades[i].getLevel();
                    averages += sum/(unitCount + grades[i].getLevel()) + "\n";
                }
                sum = sumRequiredSubjects();
                for(int i = 7; i<subjectsCount; i++) {
                    sum += grades[i].gradeWithBonus() * grades[i].getLevel();
                    unitCount += grades[i].getLevel();
                }
                averages += sum/unitCount;
            }
        } else {
            for(int i = 7; i<subjectsCount; i++) {
                sum = sumRequiredSubjects();
                sum += grades[i].gradeWithBonus() * grades[i].getLevel();
                averages += sum/(unitCount + grades[i].getLevel()) + "\n";
            }
            int tempUnitCount = unitCount;
            for(int i = 7; i<subjectsCount; i++) {
                for(int j = i+1; j<subjectsCount; j++) {
                    sum = sumRequiredSubjects();
                    unitCount = tempUnitCount;
                    sum += grades[i].gradeWithBonus() * grades[i].getLevel();
                    unitCount += grades[i].getLevel();
                    sum += grades[j].gradeWithBonus() * grades[j].getLevel();
                    unitCount += grades[j].getLevel();
                    averages += sum/unitCount + "\n";
                }
            }
        }
        return averages;
    }
}
