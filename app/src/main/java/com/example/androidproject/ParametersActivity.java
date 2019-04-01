package com.example.androidproject;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class ParametersActivity extends AppCompatActivity {

    private static final String TAG = "ParametersActivity";

    public static Context contextOfApplication;

    SectionsParametersAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextOfApplication = getApplicationContext();
        setContentView(R.layout.parameters_layout);
        Log.d(TAG, "onCreate: Starting.");
        mSectionsPageAdapter = new SectionsParametersAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsParametersAdapter adapter = new SectionsParametersAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragDifficulties(), "Difficulté");
        adapter.addFragment(new FragOptions(), "Options");
        viewPager.setAdapter(adapter);
    }

}
