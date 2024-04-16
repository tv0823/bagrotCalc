package com.example.bagrotcalc;

import java.io.Serializable;

public class BagrotGrade implements Serializable {
    private String subject;
    private int level;
    private int grade;

    public BagrotGrade(String subject, int level, int grade){
        this.subject = subject;
        this.level = level;
        this.grade = grade;
    }

    public static boolean isGradeValid(String grade) {
        return !grade.isEmpty() && Integer.parseInt(grade) <= 100 && Integer.parseInt(grade) >= 0;
    }

    public static boolean isMathLevelValid(String level) {
        return !level.isEmpty() && (Integer.parseInt(level) == 5 || Integer.parseInt(level) == 4 || Integer.parseInt(level) == 3);
    }

    public static boolean isEnglishLevelValid(String level) {
        return !level.isEmpty() && (Integer.parseInt(level) == 5 || Integer.parseInt(level) == 4);
    }

    public int getGrade() {
        return this.grade;
    }

    public int getLevel() {
        return  this.level;
    }

    public int gradeWithBonus() {
        int finalGrade = this.grade;
        if ((this.subject.equals("Math") && this.level == 5) || (this.subject.equals("English") && this.level == 5)) {
            finalGrade += 30;
        } else if ((this.subject.equals("Math") && this.level == 4) || this.subject.equals("English") && this.level == 4) {
            finalGrade += 15;
        } else if (this.level == 5) {
            finalGrade += 20;
        } else if (this.level == 4) {
            finalGrade += 10;
        }
        return finalGrade;
    }
    @Override
    public String toString() {
        return subject + " \t " + level + " \t " + grade + " \t " + gradeWithBonus();
    }
}
