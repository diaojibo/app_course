package com.rocklct.equationquiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rocklct.equationquiz.R;

public class QuizSummaryActivity extends AppCompatActivity {

    TextView right_question;
    TextView wrong_question;
    TextView giveUp_question;
    TextView average_linear;
    TextView average_quadratic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_summary);
        initViews();
    }

    private void initViews(){
        right_question = (TextView) findViewById(R.id.right_question);
        wrong_question = (TextView) findViewById(R.id.wrong_question);
        average_linear = (TextView) findViewById(R.id.average_linear);
        average_quadratic = (TextView) findViewById(R.id.average_quadratic);
        giveUp_question = (TextView) findViewById(R.id.giveUp);

        Intent fromIntent = getIntent();
        int rights = fromIntent.getIntExtra("rights",0);
        int wrongs = fromIntent.getIntExtra("wrongs",0);
        int giveUps = fromIntent.getIntExtra("giveUps",0);
        long linear_durations = fromIntent.getLongExtra("durations_linear",0);
        long quadratic_durations = fromIntent.getLongExtra("durations_quadratic",0);

        double average_linear_time = Math.round(linear_durations/5000d * 100)/100d;
        double average_quadratic_time = Math.round(quadratic_durations/5000d *100)/100d;

        right_question.setText("questions correct: " + rights);
        wrong_question.setText("questions wrong:" + wrongs);
        average_linear.setText("average time(linear equations): " +average_linear_time + "s");
        average_quadratic.setText("average time(quadratic equations): " + average_quadratic_time + "s");
        giveUp_question.setText("questions given up: " + giveUps);

    }

    public void redo(View view){
        Intent intent = new Intent(this,QuizPaperActivity.class);
        startActivity(intent);
    }
}
