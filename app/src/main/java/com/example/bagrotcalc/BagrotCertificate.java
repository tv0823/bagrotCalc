package com.example.bagrotcalc;

public class BagrotCertificate {
    private int subjectsCount;
    private BagrotGrade[] grades;

    public BagrotCertificate(int subjectsCount, BagrotGrade[] grades){
        this.subjectsCount = subjectsCount;
        this.grades = grades;
    }

    public String[] BagrotSummary() {
        String subjectStr = "", levelStr = "" , gradeStr = "", bonusStr = "";

        for(int i = 0; i < subjectsCount; i++) {
            subjectStr += grades[i].getSubject() + "\n";
            levelStr += grades[i].getLevel() + "\n";
            gradeStr += grades[i].getGrade() + "\n";
            bonusStr += grades[i].gradeWithBonus() + "\n";
        }

        return new String[]{subjectStr, levelStr, gradeStr, bonusStr};
    }

    public Double bagrotAvg() {
        double sum = 0, unitCount = 0;

        for(int i = 0; i < subjectsCount; i++) {
            sum += grades[i].gradeWithBonus() * grades[i].getLevel();
            unitCount += grades[i].getLevel();
        }

        return sum/unitCount;
    }

    private int sumRequiredSubjects() {
        int sum = 0;

        for(int i = 0; i < 7; i++) {
            sum += grades[i].gradeWithBonus() * grades[i].getLevel();
        }

        return sum;
    }

    public double[] allAvg() {
        double[] avgArr;
        double averages = 0;
        double sum = 0;
        int count = 0;
        int unitCount = 0;

        for(int i = 0; i < 7; i++) {
            unitCount += grades[i].getLevel();
        }

        if(subjectsCount == 9) {
            if(grades[8].getLevel() == 1) {
                averages = bagrotAvg();
                avgArr = new double[]{averages};
            } else {
                avgArr = new double[2];

                for(int i = 7; i < subjectsCount; i++) {
                    sum = sumRequiredSubjects() + (grades[i].gradeWithBonus() * grades[i].getLevel());
                    averages = sum/(unitCount + grades[i].getLevel());
                    avgArr[count] = averages;
                    count++;
                }
            }
        } else {
            avgArr = new double[6];

            for(int i = 7; i < subjectsCount; i++) {
                sum = sumRequiredSubjects() + (grades[i].gradeWithBonus() * grades[i].getLevel());
                averages = sum/(unitCount + grades[i].getLevel());
                avgArr[count] = averages;
                count++;
            }
            int tempUnitCount = unitCount;

            for(int i = 7; i < subjectsCount; i++) {
                for(int j = i+1; j < subjectsCount; j++) {
                    sum = sumRequiredSubjects();
                    unitCount = tempUnitCount;

                    sum += grades[i].gradeWithBonus() * grades[i].getLevel();
                    unitCount += grades[i].getLevel();

                    sum += grades[j].gradeWithBonus() * grades[j].getLevel();
                    unitCount += grades[j].getLevel();

                    averages = sum/unitCount;
                    avgArr[count] = averages;
                    count++;
                }
            }
        }

        return avgArr;
    }
}
