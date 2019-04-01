package com.example.androidproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FragDifficulties extends Fragment {

    private static final String TAG = "FragDifficulties";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.difficulties_layout,container,false);

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroupDiff);

        if(MonAppli.difficulte.equalsIgnoreCase(getString(R.string.radio_easy))){
            RadioButton rb = view.findViewById(R.id.radioEasy);
            rb.setChecked(true);
        }
        else if(MonAppli.difficulte.equalsIgnoreCase(getString(R.string.radio_medium))){
            RadioButton rb = view.findViewById(R.id.radioMedium);
            rb.setChecked(true);
        }
        else{
            RadioButton rb = view.findViewById(R.id.radioHard);
            rb.setChecked(true);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb=(RadioButton)view.findViewById(checkedId);
                String texte = rb.getText().toString();
                if(texte.equalsIgnoreCase(getString(R.string.radio_easy))){
                    MonAppli.difficulte = getString(R.string.radio_easy);
                }
                else if(texte.equalsIgnoreCase(getString(R.string.radio_medium))){
                    MonAppli.difficulte = getString(R.string.radio_medium);
                }
                else{//has to be hard
                    MonAppli.difficulte = getString(R.string.radio_hard);
                }
            }
        });

        return view;
    }

}
