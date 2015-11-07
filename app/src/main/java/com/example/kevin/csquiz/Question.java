package com.example.kevin.csquiz;

import java.util.Random;

public class Question
{
    private String quest;
    private String[] pAns;
    private String ans;

    public Question(String quest, String[] pAns, String ans)
    {
        this.quest = quest;
        this.pAns = pAns;
        this.ans = ans;
        shuffleAnswers();
    }

    public void shuffleAnswers()
    {
        Random rnd = new Random();
        for(int i = 0; i < pAns.length; i++)
        {
            int index = rnd.nextInt(i + 1);

            String temp = pAns[index];
            pAns[index] = pAns[i];
            pAns[i] = temp;
        }
    }

    public boolean isCorrect(String answer)
    {
        if(answer.equals(ans) )
        {
            return true;
        }

        return false;
    }

    public String getQuestion()
    {
        return quest;
    }

    public String[] getPotAnswers()
    {
        return pAns;
    }

    public String getAnswer()
    {
        return ans;
    }
}
