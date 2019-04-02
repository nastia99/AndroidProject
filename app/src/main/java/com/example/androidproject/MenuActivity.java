package com.example.androidproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Class d'accueil qui permet à l'utilisateur de choisir entre 3 boutons
 * Jouer - Parametres - Règles
 */

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        addEditListener();

        String drawableName = "gradient_bg";
        View view = this.getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(ResourcesCompat.getDrawable(getResources(), this.getResources().getIdentifier(drawableName, "drawable", this.getPackageName()), null));
        }
        // Save color preference
        SharedPreferences sharedPref = this.getSharedPreferences("bgColorFile",this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("color", drawableName);
        editor.apply();

        // Get the color preference
        sharedPref = getSharedPreferences("bgColorFile",this.MODE_PRIVATE);
        drawableName = sharedPref.getString("color", null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            findViewById(R.id.menuLayout).setBackground(ResourcesCompat.getDrawable(getResources(), this.getResources().getIdentifier(drawableName, "drawable", this.getPackageName()), null));
        }
    }

    @Override
    protected void onResume () {
        super.onResume();
        // Get the color preference
        SharedPreferences sharedPref = getSharedPreferences("bgColorFile",this.MODE_PRIVATE);
        String drawableName = sharedPref.getString("color", null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            findViewById(R.id.menuLayout).setBackground(ResourcesCompat.getDrawable(getResources(), this.getResources().getIdentifier(drawableName, "drawable", this.getPackageName()), null));
        }
    }

    public void addEditListener(){

        Button parameters = findViewById(R.id.paramButton);
        Button play = findViewById(R.id.playButton);
        Button rules = findViewById(R.id.rulesButton);

        parameters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), ParametersActivity.class);
                startActivity(intent);
            }
        });

        rules.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MyWebViewActivity.class);
                intent.putExtra("url", "https://github.com/nastia99/AndroidProject/blob/master/options/rules.md");
                startActivity(intent);
            }
        });

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QcmActivity.class);
                startActivity(intent);
            }
        });

    }
}
