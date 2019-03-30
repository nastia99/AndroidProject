package com.example.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        addEditListener();
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
                intent.putExtra("url", "https://git-iutinfo.unice.fr/mn707438/androidproject/blob/master/Règles.pdf");
                startActivity(intent);
            }
        });

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QCMActivity.class);
                startActivity(intent);
            }
        });

    }
}
