package org.example;

import java.util.*;

public class QuizQuestion {
    private String question;
    private List<String> choices;
    private int correctAnswerIndex;
    public QuizQuestion(String question, List<String> choices, int correctAnswerIndex) {
        this.question = question;
        this.choices = choices;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public QuizQuestion(String question, List<String> choices) {
        this.question = question;
        this.choices = choices;
    }
    public String getQuestion() {
        return question;
    }
    public List<String> getChoices() {
        return choices;
    }
    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
    public boolean isCorrectAnswer(int userChoice) {
        return choices.get(userChoice - 1).endsWith("*");
    }
}
