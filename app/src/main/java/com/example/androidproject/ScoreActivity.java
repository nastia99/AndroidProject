package com.example.androidproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * Class qui affiche à l'utilisateur le score avec une animation et
 * donne le choix à l'utilisateur de voir la correction
 * ou bien retourner au menu
 */

public class ScoreActivity extends AppCompatActivity {

    private TextView score;
    private Button correction;
    private Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);
        correction = findViewById(R.id.correction);
        menu = findViewById(R.id.menu);
        setListener();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int points = extras.getInt("POINTS");
            int total = extras.getInt("TOTAL");
            showScore(points, total);
        }

        // Get the color preference
        SharedPreferences sharedPref = getSharedPreferences("bgColorFile",this.MODE_PRIVATE);
        String drawableName = sharedPref.getString("color", null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            findViewById(R.id.scoreLayout).setBackground(ResourcesCompat.getDrawable(getResources(), this.getResources().getIdentifier(drawableName, "drawable", this.getPackageName()), null));
        }
    }

    @Override
    protected void onResume () {
        super.onResume();
        // Get the color preference
        SharedPreferences sharedPref = getSharedPreferences("bgColorFile",this.MODE_PRIVATE);
        String drawableName = sharedPref.getString("color", null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            findViewById(R.id.scoreLayout).setBackground(ResourcesCompat.getDrawable(getResources(), this.getResources().getIdentifier(drawableName, "drawable", this.getPackageName()), null));
        }
    }

    private void setListener() {
        correction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCorrection = new Intent(getApplicationContext(), CorrectionActivity.class);
                startActivity(intentCorrection);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenu = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intentMenu);
            }
        });
    }

    // methode qui permet de recuperer les points et le total de question pour calculer le score grâce aux intent
    private void showScore(int points,int total){
        score = findViewById(R.id.score);
        String s = points + " sur " + total;
        score.setText(s);
        if(points >= total/2)
            score.setTextColor(Color.GREEN);
        else
            score.setTextColor(Color.RED);
        Animation zoom = AnimationUtils.loadAnimation(this,R.anim.zoom);
        score.startAnimation(zoom);
    }
}
