package com.koen.exam;

import android.content.SharedPreferences;

import com.koen.exam.model.QuestionData;

import java.util.List;

public class DataSingleton {
    private static DataSingleton INSTANCE;
    public String jwtToken;
    public SharedPreferences sharedPreferences;
    public String descTest;
    public String titleTest;
    public String fio;
    public String email;
    public Integer idTest;
    public List<QuestionData> questionDataList;

    private DataSingleton() {

    }

    public static DataSingleton getInstance() {
        if (INSTANCE == null)
            return INSTANCE = new DataSingleton();
        else return INSTANCE;
    }

    public void initDataSharedPreferences(){
        jwtToken = sharedPreferences.getString("jwtToken", "");
    }

    public void saveInSharedPreferencesString(String nameValue, String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nameValue, value);
        editor.apply();
    }
}
