package com.example.akinatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button yesButton;
    private Button noButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на экран ответа с сообщением "Я уверен, это Мата Хари!"
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                intent.putExtra("answer", "Я уверен, что персонаж которого вы загадали - Мата Хари!");
                intent.putExtra("image", R.drawable.mata_hari);
                startActivity(intent);
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на экран ответа с сообщением "Я уверен, это Михаил Круг"
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                intent.putExtra("answer", "Я уверен, что персонаж которого вы загадали - Михаил Круг!");
                intent.putExtra("image", R.drawable.krug);
                startActivity(intent);
            }
        });
    }
}