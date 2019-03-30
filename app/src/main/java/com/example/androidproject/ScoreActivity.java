package com.example.androidproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private TextView score;
    private Button correction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);
        correction = findViewById(R.id.correction);
        setListener();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int points = extras.getInt("POINTS");
            int total = extras.getInt("TOTAL");
            showScore(points, total);
        }
    }

    private void setListener() {
        correction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CorrectionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showScore(int points,int total){
        score = findViewById(R.id.score);
        String s = points + " sur " + total;
        score.setText(s);
        if(points >= total/2)
            score.setTextColor(Color.GREEN);
        else
            score.setTextColor(Color.RED);
    }
}
