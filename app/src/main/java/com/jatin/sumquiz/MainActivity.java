package com.jatin.sumquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import javax.crypto.Cipher;

public class MainActivity extends AppCompatActivity {
    Button start;
    TextView sum;
    int location;
    ArrayList<Integer> ans = new ArrayList<Integer>();
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView solution;
    TextView timer;
    ConstraintLayout game;
    int score = 0;
    int totalQues = 0;
    TextView scoresText;
    Button again;

    public void playAgain(View view){
        score=0;
        totalQues=0;
        timer.setText("30s");
        sum.setText(Integer.toString(score)+ " + " + Integer.toString(totalQues));
        newQuestion();

        again.setVisibility(View.INVISIBLE);
        solution.setText("");
        new CountDownTimer(30100,1000){
            public void onTick(long l){
                timer.setText(String.valueOf(l / 1000)+"s");
            }
            public void onFinish(){
                solution.setText("Game Over!");
                again.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    public void chooseOption(View view){
        if(Integer.toString(location).equals(view.getTag().toString())){
            solution.setText("Correct!");
            score++;
            MediaPlayer media1 = MediaPlayer.create(this,R.raw.right);
            media1.start();

        }
        else {
            solution.setText("Wrong :(");
            MediaPlayer media2 = MediaPlayer.create(this,R.raw.wrong);
            media2.start();
        }
        totalQues++;
        scoresText.setText(Integer.toString(score)+"/"+Integer.toString(totalQues));
        newQuestion();
    }

    public void newQuestion(){
        Random ran = new Random();
        int a = ran.nextInt(21);
        int b = ran.nextInt(21);
        ans.clear();
        sum.setText(Integer.toString(a)+ " + " + Integer.toString(b));
        location = ran.nextInt(4);
        for (int i=0;i<4;i++){
            if(i == location){
                ans.add(a+b);
            }
            else {
                int wrong = ran.nextInt(41);
                while (wrong == a+b){
                    wrong = ran.nextInt(41);
                }
                ans.add(wrong);
            }
        }
        button1.setText(Integer.toString(ans.get(0)));
        button2.setText(Integer.toString(ans.get(1)));
        button3.setText(Integer.toString(ans.get(2)));
        button4.setText(Integer.toString(ans.get(3)));
    }

    public void startButton(View view){
        start.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.score));
        game.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sum = findViewById(R.id.question);
        start = findViewById(R.id.go);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        solution = findViewById(R.id.answer);
        scoresText = findViewById(R.id.score);
        timer = findViewById(R.id.timer);
        again = findViewById(R.id.button6);
        game = findViewById(R.id.game);
        game.setVisibility(View.INVISIBLE);
    }
}
