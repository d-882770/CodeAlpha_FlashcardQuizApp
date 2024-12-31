package com.example.flashcardquizapp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private Quiz quiz;
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private RadioButton[] optionRadioButtons = new RadioButton[4];
    private Button nextButton;
    private int currentFlashcardIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Retrieve the quiz object
        quiz = (Quiz) getIntent().getSerializableExtra("quiz");

        // Initialize UI components
        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        optionRadioButtons[0] = findViewById(R.id.option1RadioButton);
        optionRadioButtons[1] = findViewById(R.id.option2RadioButton);
        optionRadioButtons[2] = findViewById(R.id.option3RadioButton);
        optionRadioButtons[3] = findViewById(R.id.option4RadioButton);
        nextButton = findViewById(R.id.nextButton);

        // Display the first question
        displayQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
                currentFlashcardIndex++;
                if (currentFlashcardIndex < quiz.getFlashcards().size()) {
                    displayQuestion();
                } else {
                    endQuiz();
                }
            }
        });
    }

    private void displayQuestion() {
        Flashcard flashcard = quiz.getFlashcards().get(currentFlashcardIndex);
        questionTextView.setText(flashcard.getQuestion());
        for (int i = 0; i < 4; i++) {
            optionRadioButtons[i].setText(flashcard.getOptions()[i]);
        }
        optionsRadioGroup.clearCheck();
    }

    private void checkAnswer() {
        int selectedOptionIndex = optionsRadioGroup.indexOfChild(findViewById(optionsRadioGroup.getCheckedRadioButtonId()));
        Flashcard flashcard = quiz.getFlashcards().get(currentFlashcardIndex);
        if (selectedOptionIndex == flashcard.getCorrectAnswerIndex()) {
            quiz.increaseScore();
        }
    }

    private void endQuiz() {
        Toast.makeText(this, "Quiz finished! Your score: " + quiz.getScore() + "/" + quiz.getFlashcards().size(), Toast.LENGTH_LONG).show();
        finish();
    }
}
