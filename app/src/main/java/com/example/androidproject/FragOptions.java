package com.example.androidproject;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Locale;

/**
 * Fragment qui gère les options de l'application
 * Luminosite - Theme - Langues - (Musique dans une prochaine évolution)
 */

public class FragOptions extends Fragment {

    private static final String TAG = "FragDifficulties";

    //Seek bar object
    private SeekBar seekBar;

    //Variable to store brightness value
    private int brightness;
    //Content resolver used as a handle to the system's settings
    private ContentResolver cResolver;
    //Window object, that will store a reference to the current window
    private Window window;

    View view=null;
    View view2 =null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.options_layout,container,false);
        // Get the color preference
        SharedPreferences sharedPref = getActivity().getSharedPreferences("bgColorFile",getActivity().MODE_PRIVATE);
        String drawableName = sharedPref.getString("color", null);
        view2 = getActivity().getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view2.setBackground(ResourcesCompat.getDrawable(getResources(), this.getResources().getIdentifier(drawableName, "drawable", getActivity().getPackageName()), null));
            view.setBackground(ResourcesCompat.getDrawable(getResources(), this.getResources().getIdentifier(drawableName, "drawable", getActivity().getPackageName()), null));
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(ParametersActivity.getContextOfApplication())) {
                // Do stuff here
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getActivity().getPackageName()));
                startActivityForResult(intent,200);
            }
        }

        //Instantiate seekbar object
        seekBar = (SeekBar) view.findViewById(R.id.seekLumi);

        //Get the content resolver
        cResolver = ParametersActivity.getContextOfApplication().getContentResolver();

        //Get the current window
        window = getActivity().getWindow();

        //Set the seekbar range between 0 and 255
        //seek bar settings//
        //sets the range between 0 and 255
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBar.setMin(0);
        }
        seekBar.setMax(255);
        //set the seek bar progress to 1
        seekBar.setKeyProgressIncrement(1);

        try
        {
            //Get the current system brightness
            brightness = Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);
        }
        catch (Settings.SettingNotFoundException e)
        {
            //Throw an error case it couldn't be retrieved
            Log.e("Error", "Cannot access system brightness");
            e.printStackTrace();
        }

        //Set the progress of the seek bar based on the system's brightness
        seekBar.setProgress(brightness);

        //Register OnSeekBarChangeListener, so it can actually change values
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                //Set the system brightness using the brightness variable value
                Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
                //Get the current window attributes
                WindowManager.LayoutParams layoutpars = window.getAttributes();
                //Set the brightness of this window
                layoutpars.screenBrightness = brightness / (float)255;
                //Apply attribute changes to this window
                window.setAttributes(layoutpars);
            }

            public void onStartTrackingTouch(SeekBar seekBar)
            {
                //Nothing handled here
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                //Set the minimal brightness level
                //if seek bar is 20 or any value below
                if(progress<=20)
                {
                    //Set the brightness to 20
                    brightness=20;
                }
                else //brightness is greater than 20
                {
                    //Set brightness variable based on the progress bar
                    brightness = progress;
                }
                //Calculate the brightness percentage
                float perc = (brightness /(float)255)*100;

            }
        });

        // This will get the radiogroup
        RadioGroup rGroup = (RadioGroup)view.findViewById(R.id.radioGroup);

        // This overrides the radiogroup onCheckListener
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);

                if(checkedRadioButton.getText().toString().equals("Thème 1")){
                    Drawable shape = ResourcesCompat.getDrawable(getResources(), R.drawable.gradient_bg, null);


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                        String drawableName = "gradient_bg";
                        View view = getActivity().getWindow().getDecorView();
                        view.findViewById(R.id.optionsLayout).setBackground(ResourcesCompat.getDrawable(getResources(), getActivity().getResources().getIdentifier(drawableName, "drawable", getActivity().getPackageName()), null));
                        view.findViewById(R.id.diffLayout).setBackground(ResourcesCompat.getDrawable(getResources(), getActivity().getResources().getIdentifier(drawableName, "drawable", getActivity().getPackageName()), null));


                        view.setBackground(ResourcesCompat.getDrawable(getResources(), getActivity().getResources().getIdentifier(drawableName, "drawable", getActivity().getPackageName()), null));
                        // Save color preference
                        SharedPreferences sharedPref = getActivity().getSharedPreferences("bgColorFile",getContext().MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("color", drawableName);
                        editor.apply();
                    }

                }

                if(checkedRadioButton.getText().toString().equals("Thème 2")){
                    Drawable shape = ResourcesCompat.getDrawable(getResources(), R.drawable.gradient_bg2, null);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                        String drawableName = "gradient_bg2";
                        View view = getActivity().getWindow().getDecorView();

                        view.findViewById(R.id.optionsLayout).setBackground(ResourcesCompat.getDrawable(getResources(), getActivity().getResources().getIdentifier(drawableName, "drawable", getActivity().getPackageName()), null));
                        view.findViewById(R.id.diffLayout).setBackground(ResourcesCompat.getDrawable(getResources(), getActivity().getResources().getIdentifier(drawableName, "drawable", getActivity().getPackageName()), null));

                        view.setBackground(ResourcesCompat.getDrawable(getResources(), getActivity().getResources().getIdentifier(drawableName, "drawable", getActivity().getPackageName()), null));
                        // Save color preference
                        SharedPreferences sharedPref = getActivity().getSharedPreferences("bgColorFile",getContext().MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("color", drawableName);
                        editor.apply();
                    }
                }
            }
        });


        // This will get the radiogroup
        RadioGroup rGroup2 = (RadioGroup)view.findViewById(R.id.radioGroup2);

        // This overrides the radiogroup onCheckListener
        rGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);

                if(checkedRadioButton.getText().toString().equals("Français")){
                    Log.e(TAG, checkedRadioButton.getText().toString());
                    Locale locale = new Locale("fr");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getBaseContext().getResources().updateConfiguration(config, getActivity().getBaseContext().getResources().getDisplayMetrics());
                    //Toast.makeText(this, getResources().getString(R.string.), Toast.LENGTH_SHORT).show();
                }

                if(checkedRadioButton.getText().toString().equals("English") ){
                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getBaseContext().getResources().updateConfiguration(config, getActivity().getBaseContext().getResources().getDisplayMetrics());
                }
            }
        });


        return view;
    }

    protected void onAttachedToWindow(){
        // Get the color preference
        SharedPreferences sharedPref = getActivity().getSharedPreferences("bgColorFile",getActivity().MODE_PRIVATE);
        String drawableName = sharedPref.getString("color", null);
        view2 = getActivity().getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view2.setBackground(ResourcesCompat.getDrawable(getResources(), this.getResources().getIdentifier(drawableName, "drawable", getActivity().getPackageName()), null));
            view.setBackground(ResourcesCompat.getDrawable(getResources(), this.getResources().getIdentifier(drawableName, "drawable", getActivity().getPackageName()), null));
        }
    }

}
