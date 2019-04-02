package com.example.androidproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class relative à l'affichage du quiz
 * Questions - Reponses
 */

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
        response1 = (Button)findViewById(R.id.response1);
        response2 = (Button) findViewById(R.id.response2);
        response3 = (Button) findViewById(R.id.response3);
        response4 = (Button) findViewById(R.id.response4);

        // Get the color preference
        SharedPreferences sharedPref = getSharedPreferences("bgColorFile",this.MODE_PRIVATE);
        String drawableName = sharedPref.getString("color", null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            findViewById(R.id.qcmLayout).setBackground(ResourcesCompat.getDrawable(getResources(), this.getResources().getIdentifier(drawableName, "drawable", this.getPackageName()), null));
        }

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
        setQuestionView();
    }

    @Override
    protected void onResume () {
        super.onResume();
        // Get the color preference
        SharedPreferences sharedPref = getSharedPreferences("bgColorFile",this.MODE_PRIVATE);
        String drawableName = sharedPref.getString("color", null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            findViewById(R.id.qcmLayout).setBackground(ResourcesCompat.getDrawable(getResources(), this.getResources().getIdentifier(drawableName, "drawable", this.getPackageName()), null));
        }
    }

    private void reset(){
        response1.setBackgroundResource(android.R.drawable.btn_default);
        response1.setClickable(true);
        response2.setBackgroundResource(android.R.drawable.btn_default);
        response2.setClickable(true);
        response3.setBackgroundResource(android.R.drawable.btn_default);
        response3.setClickable(true);
        response4.setBackgroundResource(android.R.drawable.btn_default);
        response4.setClickable(true);
        setQuestionView();
    }

    // methode qui verifie les reponses si le quiz est termine change d'activite
    private void terminer(){
        checkAnswers();
        response1.setClickable(false);
        response2.setClickable(false);
        response3.setClickable(false);
        response4.setClickable(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                changeActivity();
            }
        }, 1000);   //2 seconds
    }

    private void setQuestionView() {
        currentQ = listQ.get(idQ);
        question.setText(currentQ.getQuestion());
        response1.setText(currentQ.getPropositions().get(0));
        response2.setText(currentQ.getPropositions().get(1));
        response3.setText(currentQ.getPropositions().get(2));
        response4.setText(currentQ.getPropositions().get(3));
        idQ++;
    }

    // methode qui permet de verifier si la reponse selectionnee est juste,
    // si oui l'affiche en vert sinon en rouge
    public void checkAnswers(){
        List<Boolean> answers = new ArrayList<>();
        boolean but1 = response1.isClickable();
        boolean but2 = response2.isClickable();
        boolean but3 = response3.isClickable();
        boolean but4 = response4.isClickable();
        answers.add(but1);
        answers.add(but2);
        answers.add(but3);
        answers.add(but4);

        if(answers.equals(currentQ.getCorrectAnswers())){
            //Correct answer
            isAnswerCorrect[idQ -1] = true;
            points++;
            if(but1){
                response1.setBackgroundColor(Color.GREEN);
            }
            else if(but2){
                response2.setBackgroundColor(Color.GREEN);
            }
            else if (but3){
                response3.setBackgroundColor(Color.GREEN);
            }
            else{
                response4.setBackgroundColor(Color.GREEN);
            }
        }
        else {
            //Incorrect
            isAnswerCorrect[idQ -1 ]= false;
            if(but1){
                response1.setBackgroundColor(Color.RED);
            }
            else if(but2){
                response2.setBackgroundColor(Color.RED);
            }
            else if (but3){
                response3.setBackgroundColor(Color.RED);
            }
            else{
                response4.setBackgroundColor(Color.RED);
            }
            List<Boolean> curr = currentQ.getCorrectAnswers();
            if(curr.get(0)){
                response1.setBackgroundColor(Color.GREEN);
            }
            else if(curr.get(1)){
                response2.setBackgroundColor(Color.GREEN);
            }
            else if(curr.get(2)){
                response3.setBackgroundColor(Color.GREEN);
            }
            else{
                response4.setBackgroundColor(Color.GREEN);
            }
        }
    }

    public void changeActivity() {
        if(idQ < listQ.size()){ // il reste encore des questions à afficher --> relancer
            reset();
        }
        else { // toutes les questions ont ete affiche
            //System.out.println("POINTS : " + points + "/" +listQ.size());
            Intent intent = new Intent(getBaseContext(), ScoreActivity.class);
            intent.putExtra("POINTS", points); // on recupere le nombre de bonnes reponses
            intent.putExtra("TOTAL",listQ.size()); // on recupere le total de questions
            startActivity(intent);
        }
    }
}
