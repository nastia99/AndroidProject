package com.example.androidproject;

import java.util.List;

public class Question {

    private String question;
    private List<String> propositions;
    private List<Boolean> correctAnswers;

    public Question(String question, List<String> propositions, List<Boolean> correctAnswers) {
        this.question = question;
        this.propositions = propositions;
        this.correctAnswers = correctAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getPropositions() {
        return propositions;
    }

    public List<Boolean> getCorrectAnswers() {
        return correctAnswers;
    }

}
