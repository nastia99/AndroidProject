package com.example.androidproject;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionAdapter implements ListAdapter {

    private QuestionList mListP;

    //Le contexte dans lequel est présent notre adapter
    private Context mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;


    public QuestionAdapter(Context context, QuestionList aListP) {
        mContext = context;
        mListP = aListP;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    public int getCount() {
        return mListP.size();
    }

    public Object getItem(int position) {
        return mListP.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout layoutItem;

        //(1) : Réutilisation des layouts
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML ""
            layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.question_layout, parent, false);
        }
        else {
            layoutItem = (ConstraintLayout) convertView;
        }

        //(2) : Récupération des TextView de notre layout
        TextView num = (TextView) layoutItem.findViewById(R.id.num);
        ImageView result = (ImageView) layoutItem.findViewById(R.id.result);

        //(3) : Renseignement des valeurs
        num.setText((position+1)+".");
        if(QcmActivity.isAnswerCorrect[position] == true){
            result.setImageResource(R.mipmap.smile);
        }
        else {
            result.setImageResource(R.mipmap.sad);
        }
        //On retourne l'item créé.
        return layoutItem;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    //abonnement pour click sur le nom...
    private ArrayList<QuestionAdapterListener> mListListener = new ArrayList<QuestionAdapterListener>();
    public void addListener(QuestionAdapterListener aListener) {
        mListListener.add(aListener);
    }

    private void sendListener(Question item, int position) {
        for(int i = mListListener.size()-1; i >= 0; i--) {
            mListListener.get(i).onClickNom(item, position);
        }
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    // Interface pour écouter les évènements sur le nom du diplome
    public interface QuestionAdapterListener {
        public void onClickNom(Question item, int position);
    }
}
