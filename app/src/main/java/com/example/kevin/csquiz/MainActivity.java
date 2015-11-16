package com.example.kevin.csquiz;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private QuestionBank bank;
    private Question currentQuestion;
    private int questionCount;
    private int score;

    TextView questionText, scoreText;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent menu = getIntent();
        String category = menu.getStringExtra("category");
        bank = new QuestionBank(category, this);
        questionCount = 0;
        score = 0;

        radioGroup = (RadioGroup)findViewById(R.id.radioAnswers);
        questionText = (TextView)findViewById(R.id.questionText);
        scoreText = (TextView)findViewById(R.id.scoreText);
        nextBtn = (Button)findViewById((R.id.nxtBtn));

        setNextBtn();
        nextQuestion();
    }

    private void nextQuestion()
    {
        nextBtn.setText("answer");
        setNextBtn();
        radioGroup.setEnabled(true);

        if(questionCount < bank.getSize())
        {
            currentQuestion = bank.getQuestion(questionCount);
            questionText.setText(currentQuestion.getQuestion());

            String[] potentialAnswers = currentQuestion.getPotentialAnswers();
            RadioButton answerBtn;

            for(int i = 0; i < radioGroup.getChildCount(); i++)
            {
                answerBtn = (RadioButton)radioGroup.getChildAt(i);
                answerBtn.setText(potentialAnswers[i]);
                answerBtn.setBackgroundResource(R.drawable.radiobutton_drawable);
            }

            questionCount++;
        }
        else
        {
            endQuiz();
        }
    }

    /**
     * Checks the answer
     */
    private void checkAnswer()
    {
        RadioButton answerBtn = null;
        int i = 0;

        // Since answers are always shuffled, find the correct answer button
        while(answerBtn == null && i < radioGroup.getChildCount())
        {
            RadioButton temp = (RadioButton)radioGroup.getChildAt(i);
            String answer = temp.getText().toString();

            if(currentQuestion.isCorrect(answer))
            {
                answerBtn = temp;
            }

            i++;
        }

        // Get users answer
        int checked = radioGroup.getCheckedRadioButtonId();
        RadioButton checkedBtn = (RadioButton)findViewById(checked);

        if(answerBtn == checkedBtn)
        {
            score++; // Add to score
            scoreText.setText("Score:" + Integer.toString(score));
        }
        else
        {
            checkedBtn.setBackgroundResource(R.drawable.incorrectradio_drawable); // Set incorrect button red
        }

        answerBtn.setBackgroundResource(R.drawable.correctradio_drawable); // Set correct button green
        radioGroup.setEnabled(false);
        nextBtn.setText("next");
        setNextBtn();
    }

    private void setNextBtn()
    {
        if(nextBtn.getText().toString() == "next")
        {
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextQuestion();
                }
            });
        }
        else
        {
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer();
                }
            });
        }
    }

    private void endQuiz()
    {
        Intent endQuiz = new Intent(this, ResultActivity.class);
        endQuiz.putExtra("score", score);
        endQuiz.putExtra("totalQuestions", bank.getSize());
        finish();
        startActivity(endQuiz);
    }
}
