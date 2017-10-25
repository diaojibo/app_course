package com.rocklct.equationquiz.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rocklct.equationquiz.R;
import com.rocklct.equationquiz.helper.EquationHelper;
import com.rocklct.equationquiz.model.Equation;
import com.rocklct.equationquiz.model.LinearEquation;
import com.rocklct.equationquiz.model.QuadraticEquation;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class QuizPaperActivity extends AppCompatActivity {
    private int thisQuestion = 0;
    boolean isAnswer = false;
    int rights = 0,wrongs = 0,giveUps = 0;
    ArrayList<Equation> questionList = new ArrayList<>();
    ArrayList<Boolean> correctness = new ArrayList<>();
    ArrayList<Long> timeUse = new ArrayList<>();
    TextView questionTitle;
    TextView equationString;
    TextView correctAnswer;
    TextView correctInformation;
    TextView extraInformation;
    Button submitButton;
    Button nextButton;
    EditText root1View;
    EditText root2View;
    Long timeA, timeB;

    private void initQuestions() {
        for (int i = 0; i < 5; i++) {
            questionList.add(EquationHelper.generateLinearEquation());
        }
        for (int i = 0; i < 5; i++) {
            questionList.add(EquationHelper.generateQuadraticEquation());
        }
    }

    private void initPage() {
        String title = "" + (thisQuestion + 1) + ". Question:what's the value of x";
        questionTitle.setText(title);
        extraInformation.setVisibility(TextView.GONE);
        isAnswer = false;
        root1View.setText("");
        root2View.setText("");
        if (thisQuestion < 5) {
            LinearEquation e = (LinearEquation) questionList.get(thisQuestion);
            equationString.setText(EquationHelper.getLinearEquationString(e));
            root2View.setEnabled(false);
            root2View.setHint("Disabled(Only one root)");
            if (!EquationHelper.isIntegers(e.getRoot())) {
                extraInformation.setText("please keep your answer in 2 decimal places!");
                extraInformation.setVisibility(TextView.VISIBLE);
            }

        } else {
            QuadraticEquation e = (QuadraticEquation) questionList.get(thisQuestion);
            equationString.setText(EquationHelper.getQuadraticEquationString(e));
            if (EquationHelper.areEqual(e.getRoot1(), e.getRoot2())) {
                root2View.setEnabled(false);
                root2View.setHint("Disabled(Only one root)");
            } else {
                root2View.setEnabled(true);
                root2View.setHint("root2");
            }
            if (!EquationHelper.isIntegers(e.getRoot1()) ||
                    EquationHelper.isIntegers(e.getRoot2())) {
                extraInformation.setText("please keep your answer in 2 decimal places!");
                extraInformation.setVisibility(TextView.VISIBLE);
            }

        }

        submitButton.setEnabled(true);
        timeA = System.currentTimeMillis();
        correctAnswer.setVisibility(TextView.GONE);
        correctInformation.setVisibility(TextView.GONE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initQuestions();
        setContentView(R.layout.activity_quiz_paper);

        // init views
        questionTitle = (TextView) findViewById(R.id.question_title);
        equationString = (TextView) findViewById(R.id.equation);
        correctAnswer = (TextView) findViewById(R.id.correct_answer);
        correctInformation = (TextView) findViewById(R.id.correct_information);
        submitButton = (Button) findViewById(R.id.submit);
        nextButton = (Button) findViewById(R.id.nextq);
        root1View = (EditText) findViewById(R.id.root1);
        root2View = (EditText) findViewById(R.id.root2);
        extraInformation = (TextView) findViewById(R.id.extra_information);

        initPage();

    }

    public void submitLinearEquation() {
        String ans1;
        ans1 = root1View.getText().toString();
        if (EquationHelper.upTwoDecimal(ans1)) {
            Toast.makeText(this, "Sorry!Please keep your input number in 2 decimal places", Toast.LENGTH_SHORT).show();
        } else if (!ans1.equals("")) {
            Double root1 = Double.parseDouble(ans1);
            isAnswer = true;
            submitButton.setEnabled(false);
            extraInformation.setVisibility(TextView.GONE);
            LinearEquation e = (LinearEquation) questionList.get(thisQuestion);
            correctAnswer.setText("The correct answer is: " + e.getRoot());
            if (EquationHelper.areEqual(root1, e.getRoot())) {
                correctness.add(true);
                rights++;
                correctInformation.setText("You are right!!! Congratulation!");
                correctInformation.setTextColor(Color.GREEN);
            } else {
                correctness.add(false);
                wrongs++;
                correctInformation.setText("You are wrong!! Really sorry");
                correctInformation.setTextColor(Color.RED);
            }
            correctInformation.setVisibility(TextView.VISIBLE);
            correctAnswer.setVisibility(TextView.VISIBLE);
        } else {
            Toast.makeText(this, "Your input is empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void submitQuadraticEquation() {
        String ans1;
        String ans2;
        QuadraticEquation e = (QuadraticEquation) questionList.get(thisQuestion);
        if (e.getRoot1() == e.getRoot2()) {
            ans1 = root1View.getText().toString();
            if (EquationHelper.upTwoDecimal(ans1)) {
                Toast.makeText(this, "Sorry!Please keep your input number in 2 decimal places", Toast.LENGTH_SHORT).show();
            } else if (ans1.equals("")) {
                Toast.makeText(this, "Your input is empty!", Toast.LENGTH_SHORT).show();
            } else {
                isAnswer = true;
                double root1 = Double.parseDouble(ans1);
                submitButton.setEnabled(false);
                extraInformation.setVisibility(TextView.GONE);
                correctAnswer.setText("Both the root1 and root2 are: " + e.getRoot1());
                if (EquationHelper.areEqual(root1, e.getRoot1())) {
                    correctness.add(true);
                    rights++;
                    correctInformation.setText("You are right!!! Congratulation!");
                    correctInformation.setTextColor(Color.GREEN);
                } else {
                    correctness.add(false);
                    wrongs++;
                    correctInformation.setText("You are wrong!! Really sorry");
                    correctInformation.setTextColor(Color.RED);
                }
                correctAnswer.setVisibility(TextView.VISIBLE);
                correctInformation.setVisibility(TextView.VISIBLE);
            }
        } else {
            ans1 = root1View.getText().toString();
            ans2 = root2View.getText().toString();
            if (EquationHelper.upTwoDecimal(ans1) || EquationHelper.upTwoDecimal(ans2)) {
                Toast.makeText(this, "Sorry!Please keep your input number in 2 decimal places", Toast.LENGTH_SHORT).show();
            } else if (ans1.equals("") || ans2.equals("")) {
                Toast.makeText(this, "Your input is empty!", Toast.LENGTH_SHORT).show();
            } else {
                isAnswer = true;
                double root1 = Double.parseDouble(ans1);
                double root2 = Double.parseDouble(ans2);
                submitButton.setEnabled(false);
                extraInformation.setVisibility(TextView.GONE);
                correctAnswer.setText("The roots of this equation are: " + e.getRoot1() + " and " + e.getRoot2());
                if (EquationHelper.areEqualRoots(root1, root2, e)) {
                    correctness.add(true);
                    rights++;
                    correctInformation.setText("You are right!!! Congratulation!");
                    correctInformation.setTextColor(Color.GREEN);
                } else {
                    correctness.add(false);
                    wrongs++;
                    correctInformation.setText("You are wrong!! Really sorry");
                    correctInformation.setTextColor(Color.RED);
                }
                correctInformation.setVisibility(TextView.VISIBLE);
                correctAnswer.setVisibility(TextView.VISIBLE);
            }
        }

    }

    public void submit(View view) {
        if (thisQuestion < 5) {
            submitLinearEquation();
        } else {
            if (thisQuestion < 10) {
                submitQuadraticEquation();
            }
        }
    }

    public void nextQuestion(View view) {
        timeB = System.currentTimeMillis();
        timeUse.add(timeB - timeA);
        if(!isAnswer){
            correctness.add(false);
            giveUps++;
        }
        thisQuestion++;
        if(thisQuestion >= 10){
            long linearTime = 0;
            long quadraticTime = 0;
            for(int i=0;i<5;i++){
                linearTime += timeUse.get(i);
                quadraticTime += timeUse.get(i+5);
            }
            Intent intent = new Intent(this,QuizSummaryActivity.class);
            intent.putExtra("durations_linear",linearTime);
            intent.putExtra("durations_quadratic",quadraticTime);
            intent.putExtra("rights",rights);
            intent.putExtra("wrongs",wrongs);
            intent.putExtra("giveUps",giveUps);
            startActivity(intent);
        }else{
            initPage();
        }
    }

}
