package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity {

    Random random = new Random();
    TextView textViewScore, textViewLife, textViewTime, textViewQuestion;
    EditText editTextUserAnswer;
    Button buttonNext;
    int num1, num2, userAnswer, answer;
    int score=0, life=3, time=20;
    CountDownTimer timer;
    private static final long PLAYTIME = 20000;
    long playtime = PLAYTIME;
    String input;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewScore = findViewById(R.id.textViewScore);
        textViewLife = findViewById(R.id.textViewLife);
        textViewTime = findViewById(R.id.textViewTime);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        editTextUserAnswer = findViewById(R.id.editTextUserAnswer);
        buttonNext = findViewById(R.id.buttonNext);

        textViewScore.setText(""+score);
        textViewLife.setText(""+life);
        textViewTime.setText(""+time);
        editTextUserAnswer.setText("");

        gamePlay();
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
                resetTimer();
                input = editTextUserAnswer.getText().toString();
                if(!input.equals("")){
                    userAnswer = Integer.parseInt(input);
                    if(userAnswer==answer){
                        score = score + 10;
                        textViewScore.setText(""+score);
                    }
                    else{
                        life = life -1;
                        textViewLife.setText(""+life);
                    }
                }
                else{
                    life = life -1;
                    textViewLife.setText(""+life);
                }
                if(life<1){
                    Intent intent = new Intent(Game.this,Result.class);
                    intent.putExtra("score",score);
                    startActivity(intent);
                    finish();
                }
                else{
                    editTextUserAnswer.setText("");
                    gamePlay();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void gamePlay(){
        startTimer();
        num1 = random.nextInt(100);
        num2 = random.nextInt(100);
        answer = num1 + num2;
        textViewQuestion.setText(num1+" + "+num2);


    }
    public void startTimer(){
        timer = new CountDownTimer(playtime,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                playtime = millisUntilFinished;
                updateText();
            }

            @Override
            public void onFinish() {
                life = life - 1;
                textViewLife.setText(""+life);
                editTextUserAnswer.setText("");
                pauseTimer();
                resetTimer();
                if(life<1){
                    Intent intent = new Intent(Game.this,Result.class);
                    intent.putExtra("score",score);
                    startActivity(intent);
                    finish();
                }else{
                    gamePlay();
                }
            }
        }.start();

    }
    public void updateText() {
        int second = (int)(playtime / 1000)%60;
        String timeLeft = String.format(Locale.getDefault(),"%02d",second);
        textViewTime.setText(timeLeft);

    }
    public void pauseTimer(){
        timer.cancel();
    }
    public void resetTimer() {
        playtime = PLAYTIME;
    }

}