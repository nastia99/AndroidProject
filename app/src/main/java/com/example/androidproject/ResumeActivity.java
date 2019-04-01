package com.example.androidproject;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activité relative à l'affichage de la correction d'une question donnée bonnes réponses affichées en vert
 */
public class ResumeActivity extends AppCompatActivity {

    private Button response1;
    private Button response2;
    private Button response3;
    private Button response4;
    private TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_layout);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int position = extras.getInt("POSITION");
            response1 = (Button) findViewById(R.id.response1);
            response2 = (Button) findViewById(R.id.response2);
            response3 = (Button) findViewById(R.id.response3);
            response4 = (Button) findViewById(R.id.response4);
            question = (TextView) findViewById(R.id.question);
            setLayout(position);
        }

        // Get the color preference
        SharedPreferences sharedPref = getSharedPreferences("bgColorFile",this.MODE_PRIVATE);
        String drawableName = sharedPref.getString("color", null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            findViewById(R.id.qcmLayout).setBackground(ResourcesCompat.getDrawable(getResources(), this.getResources().getIdentifier(drawableName, "drawable", this.getPackageName()), null));
        }
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

    private void setLayout(int idQ) {
        Question q = QuestionList.getInstance().get(idQ);
        question.setText(q.getQuestion());
        response1.setText(q.getPropositions().get(0));
        if(q.getCorrectAnswers().get(0)) response1.setBackgroundColor(Color.GREEN);
        response2.setText(q.getPropositions().get(1));
        if(q.getCorrectAnswers().get(1)) response2.setBackgroundColor(Color.GREEN);
        response3.setText(q.getPropositions().get(2));
        if(q.getCorrectAnswers().get(2)) response3.setBackgroundColor(Color.GREEN);
        response4.setText(q.getPropositions().get(3));
        if(q.getCorrectAnswers().get(3)) response4.setBackgroundColor(Color.GREEN);
    }

}

