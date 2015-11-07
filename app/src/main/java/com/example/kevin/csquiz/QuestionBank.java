package com.example.kevin.csquiz;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionBank
{
    private static final ArrayList<Question> bank = new ArrayList<Question>();
    private static final int DELIMIT_FORMAT = 6;
    public static final int NUM_SORT_Q = 9;
    public static final String SORT_FILE = "sorting.txt";

    public QuestionBank()
    {
    }

    public void addQuestions(Question q)
    {
        bank.add(q);
    }

    public void addQuestions(Question[] questions)
    {
        for(int i = 0; i < questions.length; i++)
        {
            bank.add(questions[i]);
        }
    }

    public void addQuestions(String database, Context instance)
    {
        try
        {
            InputStream stream = instance.getAssets().open(database);
            Scanner scan = new Scanner(stream);

            String line = "";
            String[] delimit;
            Question currentQ;

            while(scan.hasNextLine()) {
                line = scan.nextLine();

                delimit = line.split("\\|");

                if(delimit.length == DELIMIT_FORMAT)
                {
                    String questionText = delimit[0];
                    String[] pAns = {delimit[1].trim(), delimit[2].trim(), delimit[3].trim(), delimit[4].trim()};
                    String answer = delimit[5].trim();

                    currentQ = new Question(questionText, pAns, answer);
                    bank.add(currentQ);
                }
            }
        }
        catch(FileNotFoundException ex)
        {

        }
        catch(IOException ex)
        {

        }
    }

    public Question getQuestion(int x)
    {
        return bank.get(x);
    }

    public int getSize() { return bank.size(); }
}
