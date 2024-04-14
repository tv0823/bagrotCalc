package com.example.bagrotcalc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BagrotSummaryActivity extends AppCompatActivity {
    TextView summary;
    Intent gi;
    BagrotGrade[] grades;
    int subjectsCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bagrot_summary);

        summary = findViewById(R.id.summary);

        gi = getIntent();
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
        summary.setText(certificate.toString());


    }
}