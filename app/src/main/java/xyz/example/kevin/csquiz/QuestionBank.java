package xyz.example.kevin.csquiz;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class QuestionBank
{
    private static ArrayList<Question> bank;

    public final String SORT_FILE = "sorting.txt";
    public final String LINKED_LISTS_FILE = "linked_lists.txt";
    public final String ARRAYS_FILE = "arrays.txt";
    public final String TREES_FILE = "trees.txt";
    public final String HASHES_FILE = "hashes.txt";
    public final String STACK_FILE = "stack.txt";

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
        // Scans the text file and obtains all questions
        try
        {
            InputStream stream = instance.getAssets().open(fileName);
            Scanner scan = new Scanner(stream);

            String line = "";
            Question currentQ;
            String[] delimit;
            int DELIMIT_FORMAT = 6; // Questions are delimited into 6 sections

            while(scan.hasNextLine()) {
                line = scan.nextLine();

                delimit = line.split("\\|");

                if(delimit.length == DELIMIT_FORMAT) // Check if format of current line is valid
                {
                    String questionText = delimit[0];
                    String[] potentialAnswers = {delimit[1].trim(), delimit[2].trim(), delimit[3].trim(), delimit[4].trim()};
                    String answer = delimit[5].trim();

                    currentQ = new Question(questionText, potentialAnswers, answer);
                    bank.add(currentQ);
                }
            }

            Collections.shuffle(bank); // Shuffle added questions
        }
        catch(FileNotFoundException ex)
        {
            Log.e("CSQuiz", "exception", ex);
        }
        catch(IOException ex)
        {
            Log.e("CSQuiz", "exception", ex);
        }
    }

    /**
     * addCategory - adds a question category to the bank
     * @param category - name of category
     * @param instance - context instance
     */
    public void addCategory(String category, Context instance)
    {
        switch(category) {
            case "sorting": {this.addQuestions(SORT_FILE, instance);
                            break;}
            case "linked lists": {this.addQuestions(LINKED_LISTS_FILE, instance);
                                break;}
            case "arrays": {this.addQuestions(ARRAYS_FILE, instance);
                break;}
            case "hashes": {this.addQuestions(HASHES_FILE, instance);
                break;}
            case "stack": {this.addQuestions(STACK_FILE, instance);
                break;}
            case "trees": {this.addQuestions(TREES_FILE, instance);
                break;}
            default: {break;}
        }
    }

    /**
     * getQuestion - get Question object at specified index
     * @param x - index
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
