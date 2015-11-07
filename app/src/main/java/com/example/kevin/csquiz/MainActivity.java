package com.example.kevin.csquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private QuestionBank bank;
    private Question currentQuestion;
    private int questionCount;
    private int totalQuestions;
    private int score;

    TextView questionText, scoreNum;
    Button ansBtn0, ansBtn1, ansBtn2, ansBtn3;
    ProgressBar quizProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent menu = getIntent();

        questionText = (TextView)findViewById(R.id.questionText);
        scoreNum = (TextView)findViewById(R.id.scoreNum);
        ansBtn0 = (Button)findViewById(R.id.ans0Btn);
        ansBtn1 = (Button)findViewById(R.id.ans1Btn);
        ansBtn2 = (Button)findViewById(R.id.ans2Btn);
        ansBtn3 = (Button)findViewById(R.id.ans3Btn);

        ansBtn0.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                checkAnswer(ansBtn0.getText().toString());
            }
        });
        ansBtn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkAnswer(ansBtn1.getText().toString());
            }
        });
        ansBtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkAnswer(ansBtn2.getText().toString());
            }
        });
        ansBtn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkAnswer(ansBtn3.getText().toString());
            }
        });

        quizProgress = (ProgressBar)findViewById(R.id.quizProgress);
        totalQuestions = menu.getIntExtra("numQuestions", 0);
        quizProgress.setMax(totalQuestions);

        boolean includeAlgs = menu.getBooleanExtra("algorithms", false);
        boolean includeDataStructs = menu.getBooleanExtra("data structures", false);
        bank = new QuestionBank();
        addQuestions(includeAlgs, includeDataStructs);

        questionCount = 0;
        score = 0;
        nextQuestion();
    }

    private void addQuestions(boolean includeAlgs, boolean includeDataStructs)
    {
        if(includeAlgs)
            bank.addQuestions(QuestionBank.SORT_FILE, this);
    }

    private void nextQuestion()
    {
        quizProgress.setProgress(questionCount);
        scoreNum.setText(Integer.toString(score));

        if(questionCount < totalQuestions)
        {
            currentQuestion = bank.getQuestion(questionCount);

            questionText.setText(currentQuestion.getQuestion());

            String[] potentialAnswers = currentQuestion.getPotAnswers();
            ansBtn0.setText(potentialAnswers[0]);
            ansBtn1.setText(potentialAnswers[1]);
            ansBtn2.setText(potentialAnswers[2]);
            ansBtn3.setText(potentialAnswers[3]);

            questionCount++;
        }
        else
        {
            Intent endQuiz = new Intent(this, ResultActivity.class);
            endQuiz.putExtra("score", score);
            endQuiz.putExtra("totalQuestions", totalQuestions);
            finish();
            startActivity(endQuiz);
        }
    }

    private void checkAnswer(String ans)
    {
        if(currentQuestion.isCorrect(ans))
        {
            score++;
        }

        nextQuestion();
    }
}
