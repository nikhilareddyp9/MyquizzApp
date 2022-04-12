package com.example.myquizzapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView,questionNumberTextView;
    private Button option1Button,option2Button,option3Button,option4Button;
    private ArrayList<QuizFile> questionlist;
    Random random;
    int score=0,questionatempted=1,currentPosition=0;
    LinearLayout quizLayout;

    AudioAttributes audioAttributes;
    SoundPool soundPool;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView=findViewById(R.id.question);
        questionNumberTextView=findViewById(R.id.questionatempted);
        option1Button=findViewById(R.id.optionA);
        option2Button=findViewById(R.id.optionB);
        option3Button=findViewById(R.id.optionC);
        option4Button=findViewById(R.id.optionD);
        questionlist=new ArrayList<>();

        audioAttributes=new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();
        soundPool=new SoundPool.Builder()
                .setMaxStreams(2)
                .setAudioAttributes(audioAttributes)
                .build();

        final int right=soundPool.load(this,R.raw.correct,1);
        final int wrong=soundPool.load(this,R.raw.incorrect,1);



        getQuizQuestion(questionlist);


        setQuizQuestion(currentPosition);


        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionlist.get(currentPosition).getAnswer().trim().toLowerCase().equals(option1Button.getText().toString().trim().toLowerCase())){
                    score++;
                    soundPool.play(right,1,1,1,0,1);
                }
                else{
                    soundPool.play(wrong,1,1,1,0,1);
                }
                questionatempted++;
                currentPosition++;


                setQuizQuestion(currentPosition);

            }
        });
        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionlist.get(currentPosition).getAnswer().trim().toLowerCase().equals(option2Button.getText().toString().trim().toLowerCase())){
                    score++;
                    soundPool.play(right,1,1,1,0,1);
                }
                else{
                    soundPool.play(wrong,1,1,1,0,1);
                }
                questionatempted++;
                currentPosition++;
                setQuizQuestion(currentPosition);
            }
        });
        option3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionlist.get(currentPosition).getAnswer().trim().toLowerCase().equals(option3Button.getText().toString().trim().toLowerCase())){
                    score++;
                    soundPool.play(right,1,1,1,0,1);
                }
                else{
                    soundPool.play(wrong,1,1,1,0,1);
                }
                questionatempted++;
                currentPosition++;
                setQuizQuestion(currentPosition);
            }
        });
        option4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionlist.get(currentPosition).getAnswer().trim().toLowerCase().equals(option4Button.getText().toString().trim().toLowerCase())){
                    score++;
                    soundPool.play(right,1,1,1,0,1);
                }
                else{

                    soundPool.play(wrong,1,1,1,0,1);
                }
                questionatempted++;
                currentPosition++;
                setQuizQuestion(currentPosition);
            }
        });

    }
    private void setQuizQuestion(int currentPosition){
        if(currentPosition<10){
            questionNumberTextView.setText("Questions Attempted = "+questionatempted+" out of 10");

            questionTextView.setText(questionlist.get(currentPosition).getQuestion());
            option1Button.setText(questionlist.get(currentPosition).getOption1());
            option2Button.setText(questionlist.get(currentPosition).getOption2());
            option3Button.setText(questionlist.get(currentPosition).getOption3());
            option4Button.setText(questionlist.get(currentPosition).getOption4());
            currentPosition++;
        }
        else{
            String value=""+score;
            Intent intent=new Intent(MainActivity.this,FinalActivity.class);
            intent.putExtra("Score",value);
            startActivity(intent);
//            Toast.makeText(this, "Score= "+score, Toast.LENGTH_SHORT).show();
        }

    }

    private void getQuizQuestion(ArrayList<QuizFile> questionlist){
        questionlist.add(new QuizFile("Which is the largest country in the world?","China","England","Russia","America","Russia"));
        questionlist.add(new QuizFile("Which country has the largest population in the world?","India","England","Russia","China","China"));
        questionlist.add(new QuizFile("What is the capital city of India?\n","Hyderabad","Chennai","Kerala","New Delhi","New Dehli"));
        questionlist.add(new QuizFile("In which country would you find the Leaning Tower of Pisa?\n","Italy","Russia","India","America",""));
        questionlist.add(new QuizFile("Which planet is nearest to the Earth?\n","Pluto","Jupyter","Venus","Saturn","Venus"));
        questionlist.add(new QuizFile("Which is the hottest continent on Earth?\n","Australia","Africa","England","India","Africa"));
        questionlist.add(new QuizFile("Which is the largest country in the world?\n","China","England","Russia","America","Russia"));
        questionlist.add(new QuizFile("From whom did the USA purchase Alaska form","Russia","India","Canada","China","Russia"));
        questionlist.add(new QuizFile("Ceylon is the former name of which country","India","south africa","Sri lanka","Nepal",""));
        questionlist.add(new QuizFile("Which is the largest state in terms of population","Texas","New York","Washington","California","California"));
        questionlist.add(new QuizFile("At which height does the hill become a mountain","500","400","600","700","700  "));

    }
}