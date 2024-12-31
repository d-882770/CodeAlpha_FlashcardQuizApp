package com.example.flashcardquizapp;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Quiz quiz;
    private EditText questionEditText;
    private EditText[] optionEditTexts = new EditText[4];
    private EditText correctOptionIndexEditText;
    private TextView questionTextView;
    private int currentFlashcardIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        questionEditText = findViewById(R.id.questionEditText);
        optionEditTexts[0] = findViewById(R.id.option1EditText);
        optionEditTexts[1] = findViewById(R.id.option2EditText);
        optionEditTexts[2] = findViewById(R.id.option3EditText);
        optionEditTexts[3] = findViewById(R.id.option4EditText);
        correctOptionIndexEditText = findViewById(R.id.correctOptionIndexEditText);
        Button addFlashcardButton = findViewById(R.id.addFlashcardButton);
        Button startQuizButton = findViewById(R.id.startQuizButton);
        questionTextView = findViewById(R.id.questionTextView);

        // Initialize quiz object
        quiz = new Quiz();

        // Set up button listeners
        addFlashcardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFlashcard();
            }
        });

        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz();
            }
        });
    }

    private void addFlashcard() {
        String question = questionEditText.getText().toString();
        String[] options = new String[4];
        for (int i = 0; i < 4; i++) {
            options[i] = optionEditTexts[i].getText().toString();
        }
        int correctAnswerIndex = Integer.parseInt(correctOptionIndexEditText.getText().toString()) - 1;

        if (!question.isEmpty() && !options[0].isEmpty() && !options[1].isEmpty() && !options[2].isEmpty() && !options[3].isEmpty()) {
            quiz.addFlashcard(question, options, correctAnswerIndex);
            questionEditText.setText("");
            for (EditText optionEditText : optionEditTexts) {
                optionEditText.setText("");
            }
            correctOptionIndexEditText.setText("");
            Toast.makeText(this, "Flashcard added!", Toast.LENGTH_SHORT).show();
        }
    }

    private void startQuiz() {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra("quiz", quiz);
        startActivity(intent);
    }
}
