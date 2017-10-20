package com.rocklct.equationquiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rocklct.equationquiz.R;
import com.rocklct.equationquiz.helper.EquationHelper;
import com.rocklct.equationquiz.model.Equation;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strart);
    }


    public void startQuiz(View view) {
        Intent intent = new Intent(this, QuizPaperActivity.class);
        startActivity(intent);
    }
}
