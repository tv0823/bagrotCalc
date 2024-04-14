package com.example.bagrotcalc;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class additionalSubjectsActivity extends AppCompatActivity {
    BagrotCertificate certificate;
    Intent gi;
    EditText eTmath, eTenglish, levelMath, levelEnglish, eTgradeSub1, eTgradeSub2, eTgradeSub3, eTsub1, eTsub2, eTsub3;
    ToggleButton tBsub2, tBsub3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_subjects);

        eTmath = findViewById(R.id.eTmath);
        eTenglish = findViewById(R.id.eTenglish);
        levelMath = findViewById(R.id.levelMath);
        levelEnglish = findViewById(R.id.levelEnglish);

        eTgradeSub1 = findViewById(R.id.eTgradeSub1);
        eTgradeSub2 = findViewById(R.id.eTgradeSub2);
        eTgradeSub3 = findViewById(R.id.eTgradeSub3);

        eTsub1 = findViewById(R.id.eTsub1);
        eTsub2 = findViewById(R.id.eTsub2);
        eTsub3 = findViewById(R.id.eTsub3);

        tBsub2 = findViewById(R.id.tBsub2);
        tBsub3 = findViewById(R.id.tBsub3);

        gi = getIntent();
    }

    public void prev(View view) {
        finish();
    }

    public void next(View view) {
        Intent si = new Intent(this, BagrotSummaryActivity.class);

        String gradeMath = eTmath.getText().toString();
        String gradeEnglish = eTenglish.getText().toString();
        String lvlMath = levelMath.getText().toString();
        String lvlEnglish = levelEnglish.getText().toString();

        String gradeSub1 = eTgradeSub1.getText().toString();
        String gradeSub2 = eTgradeSub2.getText().toString();
        String gradeSub3 = eTgradeSub3.getText().toString();
        String sub1 = eTsub1.getText().toString();
        String sub2 = eTsub2.getText().toString();
        String sub3 = eTsub3.getText().toString();

        if(!BagrotGrade.isMathLevelValid(lvlMath) || !BagrotGrade.isEnglishLevelValid(lvlEnglish)) {
            Toast.makeText(this, "Invalid units number", Toast.LENGTH_SHORT).show();
        } else if (!BagrotGrade.isGradeValid(gradeMath) || !BagrotGrade.isGradeValid(gradeEnglish) ||
                    !BagrotGrade.isGradeValid(gradeSub1) || !BagrotGrade.isGradeValid(gradeSub2) ||
                    (tBsub3.isChecked() && !BagrotGrade.isGradeValid(gradeSub3))) {
            Toast.makeText(this, "Invalid grade", Toast.LENGTH_SHORT).show();
        } else if(sub1.isEmpty() || sub2.isEmpty() || (tBsub3.isChecked() && sub3.isEmpty())) {
            Toast.makeText(this, "Please enter subject", Toast.LENGTH_SHORT).show();
        } else {
            si.putExtra("lashon", gi.getSerializableExtra("lashon"));
            si.putExtra("saparot", gi.getSerializableExtra("saparot"));
            si.putExtra("history", gi.getSerializableExtra("history"));
            si.putExtra("ezrahot", gi.getSerializableExtra("ezrahot"));
            si.putExtra("bible", gi.getSerializableExtra("bible"));

            si.putExtra("math", new BagrotGrade(getString(R.string.math_subject), Integer.parseInt(lvlMath), Integer.parseInt(gradeMath)));
            si.putExtra("english", new BagrotGrade(getString(R.string.english_subject), Integer.parseInt(lvlEnglish), Integer.parseInt(gradeEnglish)));
            si.putExtra("sub1", new BagrotGrade("sub1", 5, Integer.parseInt(gradeSub1)));

            if (!tBsub2.isChecked()) {
                si.putExtra("sub2", new BagrotGrade("sub2", 1, Integer.parseInt(gradeSub2)));
            } else {
                si.putExtra("sub2", new BagrotGrade("sub2", 5, Integer.parseInt(gradeSub2)));
            } if(tBsub3.isChecked()) {
                si.putExtra("sub3", new BagrotGrade("sub3", 5, Integer.parseInt(gradeSub3)));
            }

            startActivity(si);
        }
    }

    public void pressedtB3(View view) {
        if(tBsub3.isChecked()) {
            eTsub3.setVisibility(View.VISIBLE);
            eTgradeSub3.setVisibility(View.VISIBLE);
        } else {
            eTsub3.setVisibility(View.INVISIBLE);
            eTgradeSub3.setVisibility(View.INVISIBLE);
        }
    }
}