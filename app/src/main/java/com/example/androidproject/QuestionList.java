package com.example.androidproject;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuestionList {

    private List<Question> listQuestions;
    private static QuestionList instance;

    public static QuestionList getInstance()
    {
        if(instance == null)
        {
            instance = new QuestionList();
        }
        return instance;
    }

    private QuestionList(){listQuestions = new ArrayList<>();}

    public int size(){
        return listQuestions.size();
    }

    public Question get(int pos){
        return listQuestions.get(pos);
    }

    public void construireListe(Context context){
        // Création de la liste des questions
        try {

            // Récupération du fichier json
            JSONArray jsonArray = new JSONArray(getJSONFromAsset(context));

            // Récupération des questions
            for(int i = 0; i < jsonArray.length(); i++) {
                listQuestions.add(getQuestionFromJSONObject(jsonArray.getJSONObject(i),context));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("code.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private  Question getQuestionFromJSONObject(JSONObject jsonObject, Context context) throws JSONException {

        //intitule de la question
        String question = jsonObject.getString("question");

        // 4 propositions possibles de la question
        JSONArray propositions = jsonObject.getJSONArray("propositions");
        List<String> propositionsList = new ArrayList<>();
        for(int i = 0 ; i< propositions.length();i++){
            propositionsList.add(propositions.getString(i));
        }
        JSONArray reponses = jsonObject.getJSONArray("responses");
        List<Boolean> responsesList = new ArrayList<>();
        for(int i = 0 ; i< reponses.length();i++){
            responsesList.add(reponses.getBoolean(i));
        }

        Question q = new Question(question,propositionsList,responsesList);

        return q;
    }

}
