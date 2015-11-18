package com.example.kevin.csquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button newQuiz = (Button)findViewById(R.id.newQuiz);
        TextView scoreVal = (TextView)findViewById(R.id.scoreVal);

        // Get final score and set
        Intent results = getIntent();
        int score = results.getIntExtra("score", 0);
        int totalQuestions = results.getIntExtra("totalQuestions", 0);
        scoreVal.setText(Integer.toString(score) + " / " + Integer.toString(totalQuestions));

        // Start new game
                newQuiz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                Intent newQuiz = new Intent(v.getContext(), MenuActivity.class);
                finish();
                        startActivity(newQuiz);
            }
        });
    }
}
