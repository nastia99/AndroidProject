package com.example.androidproject;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Class qui affiche à l'utilisateur ses bonnes et mauvaises réponses grâce à un smiley :) ou :(
 */

public class CorrectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correction_layout);
        addItemListView();
        // Get the color preference
        SharedPreferences sharedPref = getSharedPreferences("bgColorFile",this.MODE_PRIVATE);
        String drawableName = sharedPref.getString("color", null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            findViewById(R.id.correctionLayout).setBackground(ResourcesCompat.getDrawable(getResources(), this.getResources().getIdentifier(drawableName, "drawable", this.getPackageName()), null));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Get the color preference
        SharedPreferences sharedPref = getSharedPreferences("bgColorFile",this.MODE_PRIVATE);
        String drawableName = sharedPref.getString("color", null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            findViewById(R.id.correctionLayout).setBackground(ResourcesCompat.getDrawable(getResources(), this.getResources().getIdentifier(drawableName, "drawable", this.getPackageName()), null));
        }
    }

    private void addItemListView() {
        final ListView lv = (ListView) findViewById(R.id.listView);
        final QuestionList listQ = QuestionList.getInstance();

        // utilisation d'un adapter pour afficher le numero et l'emoji associe selon la reponse dans une listview
        QuestionAdapter ad = new QuestionAdapter(this,listQ);
        lv.setAdapter((ListAdapter) ad);
    }
}
