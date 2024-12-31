package com.example.flashcardquizapp;



import java.io.Serializable;
import java.util.ArrayList;

public class Quiz implements Serializable {
    private ArrayList<Flashcard> flashcards;
    private int score;

    public Quiz() {
        this.flashcards = new ArrayList<>();
        this.score = 0;
    }

    public void addFlashcard(String question, String[] options, int correctAnswerIndex) {
        flashcards.add(new Flashcard(question, options, correctAnswerIndex));
    }

    public ArrayList<Flashcard> getFlashcards() {
        return flashcards;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }
}
