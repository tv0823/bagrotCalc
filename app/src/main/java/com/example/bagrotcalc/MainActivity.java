package com.example.bagrotcalc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText eTuserName, eTlashon, eTsaparot, eThistory, eTezrahot, eTbible;
    final int REQUEST_CODE = 3602;
    String eTmath = "", eTenglish = "", levelMath = "", levelEnglish = "", eTgradeSub1 = "", eTgradeSub2 = "", eTgradeSub3 = "", eTsub1 = "", eTsub2 = "", eTsub3 = "",tBsub2 = "no", tBsub3 = "no";
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

            si.putExtra("eTmath", eTmath);
            si.putExtra("eTenglish", eTenglish);
            si.putExtra("levelMath", levelMath);
            si.putExtra("levelEnglish", levelEnglish);
            si.putExtra("eTgradeSub1", eTgradeSub1);
            si.putExtra("eTgradeSub2", eTgradeSub2);
            si.putExtra("eTgradeSub3", eTgradeSub3);
            si.putExtra("eTsub1", eTsub1);
            si.putExtra("eTsub2", eTsub2);
            si.putExtra("eTsub3", eTsub3);
            si.putExtra("tBsub2", tBsub2);
            si.putExtra("tBsub3", tBsub3);

            startActivityForResult(si, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int source, int result, @Nullable Intent data_back) {
        super.onActivityResult(source, result, data_back);
        if (data_back != null) {
            eTmath = data_back.getStringExtra("eTmath");
            eTenglish = data_back.getStringExtra("eTenglish");
            levelMath = data_back.getStringExtra("levelMath");
            levelEnglish = data_back.getStringExtra("levelEnglish");
            eTgradeSub1 = data_back.getStringExtra("eTgradeSub1");
            eTgradeSub2 = data_back.getStringExtra("eTgradeSub2");
            eTgradeSub3 = data_back.getStringExtra("eTgradeSub3");
            eTsub1 = data_back.getStringExtra("eTsub1");
            eTsub2 = data_back.getStringExtra("eTsub2");
            eTsub3 = data_back.getStringExtra("eTsub3");
            tBsub2 = data_back.getStringExtra("tBsub2");
            tBsub3 = data_back.getStringExtra("tBsub3");
        }
    }
}