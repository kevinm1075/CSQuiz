package com.example.kevin.csquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    Switch algorithms, dataStructures;
    SeekBar numQuestionsSlider;
    TextView curQuestions;
    Button startButton;
    int maxQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        algorithms = (Switch)findViewById(R.id.algSwitch);
        dataStructures = (Switch)findViewById(R.id.dataStructSwitch);
        numQuestionsSlider = (SeekBar)findViewById(R.id.seekBar);
        curQuestions = (TextView)findViewById(R.id.numQuestionsText);
        startButton = (Button)findViewById(R.id.startBtn);

        algorithms.setChecked(true);
        final int numAlgQ = QuestionBank.NUM_SORT_QUESTIONS;

        dataStructures.setChecked(true);
        final int numDSQ = 0;

        maxQuestions = numAlgQ + numDSQ;

        numQuestionsSlider.setMax(maxQuestions);
        numQuestionsSlider.setProgress(maxQuestions);
        curQuestions.setText(Integer.toString(maxQuestions));

        algorithms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adjustQuestionsBar(numAlgQ, isChecked);
            }
        });

        dataStructures.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adjustQuestionsBar(numDSQ, isChecked);
            }
        });

        numQuestionsSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                curQuestions.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(v.getContext(), MainActivity.class);
                start.putExtra("numQuestions", numQuestionsSlider.getProgress());
                start.putExtra("algorithms", algorithms.isChecked());
                start.putExtra("data structures", dataStructures.isChecked());
                finish();
                startActivity(start);
            }
        });

    }

    /**
     * adjustQuestionsBar - Changes the available questions of the slider
     * @param num - amount to adjust slider
     * @param checked - include or remove question amount
     */
    private void adjustQuestionsBar(int num, boolean checked)
    {
        if(checked)
        {
            maxQuestions += num;
        }
        else
        {
            maxQuestions -= num;
        }

        numQuestionsSlider.setMax(maxQuestions);
    }
}
