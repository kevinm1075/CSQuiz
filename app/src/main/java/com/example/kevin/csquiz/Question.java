package com.example.kevin.csquiz;

import java.util.Random;

public class Question
{
    private String question;
    private String[] potentialAns;
    private String answer;

    /**
     * Question Constructor -
     * @param question Question Text
     * @param potentialAns Potential answers
     * @param answer Correct answer
     */
    public Question(String question, String[] potentialAns, String answer)
    {
        this.question = question;
        this.potentialAns = potentialAns;
        this.answer = answer;
        shuffleAnswers();
    }

    /**
     * ShuffleAnswer - shuffles the potential answers
     */
    public void shuffleAnswers()
    {
        Random rnd = new Random();
        for(int i = 0; i < potentialAns.length; i++)
        {
            int index = rnd.nextInt(i + 1);

            String temp = potentialAns[index];
            potentialAns[index] = potentialAns[i];
            potentialAns[i] = temp;
        }
    }

    /**
     * isCorrect - Checks if a sent answer is correct
     * @param answer Answer text
     * @return True if correct, False if incorrect
     */
    public boolean isCorrect(String answer)
    {
        if(answer.equals(this.answer) )
        {
            return true;
        }

        return false;
    }

    /**
     * getQuestion
     * @return Question text
     */
    public String getQuestion()
    {
        return question;
    }

    /**
     * getPotentialAnswers
     * @return String array containing potential answers
     */
    public String[] getPotentialAnswers()
    {
        return potentialAns;
    }

    /**
     * getAnswer
     * @return Answer text
     */
    public String getAnswer()
    {
        return answer;
    }
}
