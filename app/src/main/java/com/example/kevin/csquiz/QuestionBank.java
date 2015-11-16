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
    private static ArrayList<Question> bank;

    public static final int NUM_SORT_QUESTIONS = 9;
    public final String SORT_FILE = "sorting.txt";

    public static final int NUM_DATA_STRUCT_QUESTIONS = 0;
    public final String DATA_STRUCT_FILE = "data_struct.txt";

    /**
     * QuestionBank Constructor
     */
    public QuestionBank(String category, Context instance)
    {
        bank = new ArrayList<Question>();
        addCategory(category, instance);
    }

    /**
     * addQuestions - Given file name, add questions to the bank.
     * @param fileName - file location
     * @param instance - context instance
     */
    private void addQuestions(String fileName, Context instance)
    {
        try
        {
            InputStream stream = instance.getAssets().open(fileName);
            Scanner scan = new Scanner(stream);

            String line = "";
            String[] delimit;
            Question currentQ;
            final int DELIMIT_FORMAT = 6;

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

    /**
     * addCategory
     * @param category
     * @param instance
     */
    public void addCategory(String category, Context instance)
    {
        switch(category) {
            case "sorting": {this.addQuestions(SORT_FILE, instance);
                            break;}
            case "linkedLists": {this.addQuestions(DATA_STRUCT_FILE, instance);
                                break;}
            default: {break;}
        }
    }

    /**
     * getQuestion - get Question object at position x
     * @param x - position
     * @return Question object
     */
    public Question getQuestion(int x)
    {
        return bank.get(x);
    }

    public int getSize()
    {
        return bank.size();
    }
}
