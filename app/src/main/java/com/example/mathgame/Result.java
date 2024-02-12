package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView finalScore;
    Button buttonAgain, buttonExit;
    int score = 0;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        finalScore = findViewById(R.id.textViewFinalScore);
        buttonAgain = findViewById(R.id.buttonAgain);
        buttonExit = findViewById(R.id.buttonExit);
        Intent intent = getIntent();
        score = intent.getIntExtra("score",0);
        String endScore = String.valueOf(score);
        finalScore.setText("Score : "+endScore);


        buttonAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Result.this, Game.class);
                startActivity(intent1);
                finish();
            }
        });
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}