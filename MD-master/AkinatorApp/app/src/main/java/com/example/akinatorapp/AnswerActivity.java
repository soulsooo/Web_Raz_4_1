package com.example.akinatorapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AnswerActivity extends AppCompatActivity {

    private TextView answerTextView;
    private ImageView answerImageView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        answerTextView = findViewById(R.id.answerTextView);
        answerImageView = findViewById(R.id.answerImageView);
        backButton = findViewById(R.id.backButton);

        // Получаем текст ответа и ID изображения из Intent
        String answer = getIntent().getStringExtra("answer");
        int imageResId = getIntent().getIntExtra("image", 0);

        // Устанавливаем текст и изображение
        answerTextView.setText(answer);
        if (imageResId != 0) {
            answerImageView.setImageResource(imageResId);
        }

        backButton.setOnClickListener(v -> finish());
    }
}