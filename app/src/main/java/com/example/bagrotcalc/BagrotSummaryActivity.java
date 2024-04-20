package com.example.bagrotcalc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BagrotSummaryActivity extends AppCompatActivity {
    TextView allAverages, bestAverage, subjectList, levelList, gradeList, bonusList, userInfo, average;
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
        bestAverage = findViewById(R.id.bestAverage);

        gi = getIntent();
        String username = gi.getStringExtra("username");
        BagrotGrade sub3 = (BagrotGrade) gi.getSerializableExtra("sub3");

        if(sub3 == null) {
            grades =  new BagrotGrade[]{
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
            grades =  new BagrotGrade[]{
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

        BagrotCertificate certificate = new BagrotCertificate(subjectsCount, grades);
        summary = certificate.BagrotSummary();
        bagrotAvg = certificate.bagrotAvg();

        userInfo.setText(username + " bagrot certificate");
        subjectList.setText(summary[0]);
        levelList.setText(summary[1]);
        gradeList.setText(summary[2]);
        bonusList.setText(summary[3]);
        average.setText("The average is: " + String.format("%.3f", bagrotAvg));

        allAvgArr = certificate.allAvg();

        for(int i = 0; i < allAvgArr.length; i++) {
            allAvg += String.format("%.3f", allAvgArr[i]) + "\n";
        }
        allAverages.setText(allAvg);

        bestAvg = allAvgArr[0];

        for(int i = 1; i < allAvgArr.length; i++) {
            if(allAvgArr[i] > bestAvg) {
                bestAvg = allAvgArr[i];
            }
        }

        if (bagrotAvg > bestAvg) {
            bestAvg = bagrotAvg;
        }
        bestAverage.setText("The best average is: " + String.format("%.3f", bestAvg));
    }

    public void prev(View view) {
        finish();
    }
}