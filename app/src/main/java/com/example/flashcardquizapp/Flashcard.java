package com.example.flashcardquizapp;


import java.io.Serializable;

public class Flashcard implements Serializable {
    private String question;
    private String[] options;
    private int correctAnswerIndex;

    public Flashcard(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
