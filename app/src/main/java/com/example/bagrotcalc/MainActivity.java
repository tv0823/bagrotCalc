package com.example.bagrotcalc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText eTuserName, eTlashon, eTsaparot, eThistory, eTezrahot, eTbible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTuserName = findViewById(R.id.eTuserName);
        eTlashon = findViewById(R.id.eTlashon);
        eTsaparot = findViewById(R.id.eTsaparot);
        eThistory = findViewById(R.id.eThistory);
        eTezrahot = findViewById(R.id.eTezrahot);
        eTbible = findViewById(R.id.eTbible);
    }

    public void next(View view) {
        Intent si = new Intent(this,additionalSubjectsActivity.class);

        String userName = eTuserName.getText().toString();
        String gradeLashon = eTlashon.getText().toString();
        String gradeSaparot = eTsaparot.getText().toString();
        String gradeHistory = eThistory.getText().toString();
        String gradeEzrahot = eTezrahot.getText().toString();
        String gradeBible = eTbible.getText().toString();
        
        if(userName.isEmpty())
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
        else if (!BagrotGrade.isGradeValid(gradeLashon) || !BagrotGrade.isGradeValid(gradeSaparot) || !BagrotGrade.isGradeValid(gradeHistory) || !BagrotGrade.isGradeValid(gradeEzrahot) || !BagrotGrade.isGradeValid(gradeBible)) {
            Toast.makeText(this, "Invalid grade", Toast.LENGTH_SHORT).show();
        }
        else {
            si.putExtra("username", userName);
            si.putExtra("lashon", new BagrotGrade("Hebrew", 2, Integer.parseInt(gradeLashon)));
            si.putExtra("saparot", new BagrotGrade("Safrot", 2, Integer.parseInt(gradeSaparot)));
            si.putExtra("history", new BagrotGrade("History", 2, Integer.parseInt(gradeHistory)));
            si.putExtra("ezrahot", new BagrotGrade("Ezrahot", 2, Integer.parseInt(gradeEzrahot)));
            si.putExtra("bible", new BagrotGrade("Bible", 2, Integer.parseInt(gradeBible)));

            startActivity(si);
        }
    }
}