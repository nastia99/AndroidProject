package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class CorrectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correction_layout);
        addItemListView();
    }

    private void addItemListView() {
        final ListView lv = (ListView) findViewById(R.id.listView);
        final QuestionList listQ = QuestionList.getInstance();

        QuestionAdapter ad = new QuestionAdapter(this,listQ);
        lv.setAdapter((ListAdapter) ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ResumeActivity.class);
                intent.putExtra("POSITION", i);
                startActivity(intent);
            }
        });
    }
}
