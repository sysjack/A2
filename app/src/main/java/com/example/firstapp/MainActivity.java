package com.example.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button yesBtn;
    private Button noBtn;
    private Button showAnswer;
    private TextView textView;

    private Question[] questions = new Question[]{
            new Question(R.string.question0, true),
            new Question(R.string.question1, false),
            new Question(R.string.question2, false),
            new Question(R.string.question3, false),
            new Question(R.string.question4, true),
            new Question(R.string.question5, false)
    };
    private boolean[] answers = new boolean[6];
    private int questionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("SYSTEM INFO: ","МЕТОД onCreate() запущен");

        if (savedInstanceState != null) {
            questionIndex = savedInstanceState.getInt("questionIndex");
        }


        yesBtn = findViewById(R.id.btnYes);
        noBtn = findViewById(R.id.btnNo);
        showAnswer = findViewById(R.id.showAnswer);
        textView = findViewById(R.id.textView);
        textView.setText(questions[questionIndex].getQuestionResId());

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questions[questionIndex].isAnswerTrue()) {
                    answers[questionIndex] = true;
                    Toast.makeText(MainActivity.this,R.string.correct,Toast.LENGTH_SHORT).show();
                } else {
                    answers[questionIndex] = false;
                    Toast.makeText(MainActivity.this,R.string.incorrect,Toast.LENGTH_SHORT).show();
                }
                questionIndex = (questionIndex + 1) % questions.length;
                if (questionIndex % questions.length == 0) {
                    Intent intent = new Intent(MainActivity.this, AnswerListActivity.class);
                    intent.putExtra("answers", answers);
                    startActivity(intent);
                } else
                    textView.setText(questions[questionIndex].getQuestionResId());
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questions[questionIndex].isAnswerTrue()) {
                    answers[questionIndex] = false;
                    Toast.makeText(MainActivity.this,R.string.incorrect,Toast.LENGTH_SHORT).show();
                } else {
                    answers[questionIndex] = true;
                    Toast.makeText(MainActivity.this,R.string.correct,Toast.LENGTH_SHORT).show();
                }
                questionIndex = (questionIndex + 1) % questions.length;
                if (questionIndex % questions.length == 0) {
                    Intent intent = new Intent(MainActivity.this, AnswerListActivity.class);
                    intent.putExtra("answers", answers);
                    startActivity(intent);
                } else
                    textView.setText(questions[questionIndex].getQuestionResId());
            }
        });

        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                intent.putExtra("answer", questions[questionIndex].isAnswerTrue());
                startActivity(intent);
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("SYSTEM INFO: ","МЕТОД onStart() запущен");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("SYSTEM INFO: ","МЕТОД onResume() запущен");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("SYSTEM INFO: ","МЕТОД onPause() запущен");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("SYSTEM INFO: ","МЕТОД onSaveInstanceState() запущен");
        outState.putInt("questionIndex", questionIndex);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("SYSTEM INFO: ","МЕТОД onStop() запущен");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SYSTEM INFO: ","МЕТОД onDestroy() запущен");
    }


}