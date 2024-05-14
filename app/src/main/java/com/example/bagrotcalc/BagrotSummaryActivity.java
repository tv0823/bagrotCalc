package com.example.bagrotcalc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BagrotSummaryActivity extends AppCompatActivity {
    TextView allAverages, allAveragesTitle, bestAverage, subjectList, levelList, gradeList, bonusList, userInfo, average;
    Intent gi;
    BagrotGrade[] grades;
    int subjectsCount;
    double[] allAvgArr;
    String[] summary;
    double bestAvg, bagrotAvg;
    String allAvg = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bagrot_summary);

        userInfo = findViewById(R.id.userInfo);
        subjectList = findViewById(R.id.subjectList);
        levelList = findViewById(R.id.levelList);
        gradeList = findViewById(R.id.gradeList);
        bonusList = findViewById(R.id.bonusList);
        average = findViewById(R.id.average);
        allAverages = findViewById(R.id.allAverages);
        allAveragesTitle = findViewById(R.id.allAveragesTitle);
        bestAverage = findViewById(R.id.bestAverage);

        gi = getIntent();

        initBagrotUI();
    }

    private BagrotCertificate initCertificate() {
        BagrotGrade sub3 = (BagrotGrade) gi.getSerializableExtra("sub3");
        if(sub3 == null) {
            grades = new BagrotGrade[]{
                    (BagrotGrade) gi.getSerializableExtra("lashon"),
                    (BagrotGrade) gi.getSerializableExtra("saparot"),
                    (BagrotGrade) gi.getSerializableExtra("history"),
                    (BagrotGrade) gi.getSerializableExtra("ezrahot"),
                    (BagrotGrade) gi.getSerializableExtra("bible"),
                    (BagrotGrade) gi.getSerializableExtra("math"),
                    (BagrotGrade) gi.getSerializableExtra("english"),
                    (BagrotGrade) gi.getSerializableExtra("sub1"),
                    (BagrotGrade) gi.getSerializableExtra("sub2")
            };
            subjectsCount = 9;
        } else {
            grades = new BagrotGrade[]{
                    (BagrotGrade) gi.getSerializableExtra("lashon"),
                    (BagrotGrade) gi.getSerializableExtra("saparot"),
                    (BagrotGrade) gi.getSerializableExtra("history"),
                    (BagrotGrade) gi.getSerializableExtra("ezrahot"),
                    (BagrotGrade) gi.getSerializableExtra("bible"),
                    (BagrotGrade) gi.getSerializableExtra("math"),
                    (BagrotGrade) gi.getSerializableExtra("english"),
                    (BagrotGrade) gi.getSerializableExtra("sub1"),
                    (BagrotGrade) gi.getSerializableExtra("sub2"),
                    sub3
            };
            subjectsCount = 10;
        }

        return new BagrotCertificate(subjectsCount, grades);
    }

    private void initBagrotUI() {
        BagrotCertificate certificate = initCertificate();
        String username = gi.getStringExtra("username");
        summary = certificate.BagrotSummary();
        bagrotAvg = certificate.bagrotAvg();

        userInfo.setText(username + " bagrot certificate");
        subjectList.setText("Subjects\n" + summary[0]);
        levelList.setText("Level\n" + summary[1]);
        gradeList.setText("Grade\n" + summary[2]);
        bonusList.setText("Bonus\n" + summary[3]);

        if(subjectsCount != 10 && grades[8].getLevel() != 5) {
            average.setText("The average is: " + String.format("%.3f", bagrotAvg));
        }
        else {
            allAvgArr = certificate.allAvg();

            allAvg += String.format("%.3f", bagrotAvg) + "\n";
            for (int i = 0; i < allAvgArr.length; i++) {
                allAvg += String.format("%.3f", allAvgArr[i]) + "\n";
            }
            allAveragesTitle.setVisibility(View.VISIBLE);
            allAverages.setText(allAvg);

            bestAvg = allAvgArr[0];

            for (int i = 1; i < allAvgArr.length; i++) {
                if (allAvgArr[i] > bestAvg) {
                    bestAvg = allAvgArr[i];
                }
            }

            if (bagrotAvg > bestAvg) {
                bestAvg = bagrotAvg;
            }
            bestAverage.setText("The best average is: " + String.format("%.3f", bestAvg));
        }
    }

    public void prev(View view) {
        finish();
    }
}