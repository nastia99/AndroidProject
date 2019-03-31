package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QcmActivity extends AppCompatActivity {

    private int idQ = 0;
    private int points = 0;
    private QuestionList listQ;
    private Question currentQ;
    private TextView question;
    private Button response1, response2, response3, response4;
    static boolean[] isAnswerCorrect; // permet de savoir si l'utilisateur a bien repondu à la question isAnswerCorrect[i]x

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qcm_layout);
        listQ = QuestionList.getInstance();
        listQ.construireListe(getApplicationContext());
        isAnswerCorrect = new boolean[listQ.size()];
        question = (TextView)findViewById(R.id.question);

        addEditListener();
        setQuestionView();
        changeActivity();
    }

    public void addEditListener(){

        response1 = (Button)findViewById(R.id.response1);
        response2 = (Button) findViewById(R.id.response2);
        response3 = (Button) findViewById(R.id.response3);
        response4 = (Button) findViewById(R.id.response4);

        response1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                response2.setClickable(false);
                response3.setClickable(false);
                response4.setClickable(false);
                terminer();
            }
        });
        response2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                response1.setClickable(false);
                response3.setClickable(false);
                response4.setClickable(false);
                terminer();
            }
        });
        response3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                response1.setClickable(false);
                response2.setClickable(false);
                response4.setClickable(false);
                terminer();
            }
        });
        response4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                response1.setClickable(false);
                response2.setClickable(false);
                response3.setClickable(false);
                terminer();
            }
        });

    }

    private void reset(){
        response1.setClickable(true);
        response2.setClickable(true);
        response3.setClickable(true);
        response4.setClickable(true);
        setQuestionView();
    }

    private void terminer(){
        checkAnswers();
        changeActivity();
    }

    private void setQuestionView() {
        currentQ = listQ.get(idQ);
        question.setText(currentQ.getQuestion());
        response1.setText(currentQ.getPropositions().get(0));
        response1.setClickable(false);
        response2.setText(currentQ.getPropositions().get(1));
        response2.setClickable(false);
        response3.setText(currentQ.getPropositions().get(2));
        response3.setClickable(false);
        response4.setText(currentQ.getPropositions().get(3));
        response4.setClickable(false);
        idQ++;
    }

    public void checkAnswers(){
        List<Boolean> answers = new ArrayList<>();
        answers.add(response1.isClickable());
        answers.add(response2.isClickable());
        answers.add(response3.isClickable());
        answers.add(response4.isClickable());

        if(answers.equals(currentQ.getCorrectAnswers())){
            //System.out.println("BONNE REPONSE");
            isAnswerCorrect[idQ -1] = true;
            points++;
        }
        else {
            // System.out.println("MAUVAISE REPONSE");
            isAnswerCorrect[idQ -1 ] = false;
        }
    }

    public void changeActivity() {
        if(idQ < listQ.size()){ // il reste encore des questions à afficher --> relancer
            reset();
        }
        else { // toutes les questions
            //System.out.println("POINTS : " + points + "/" +listQ.size());
            Intent intent = new Intent(getBaseContext(), ScoreActivity.class);
            intent.putExtra("POINTS", points);
            intent.putExtra("TOTAL",listQ.size());
            startActivity(intent);
        }
    }
}
