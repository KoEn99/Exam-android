package com.koen.exam.service.Impl;

import com.koen.exam.DataSingleton;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.QuestionData;
import com.koen.exam.net.NetworkService;
import com.koen.exam.views.FragmentCreateQuestionsMethods;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsModel implements FragmentCreateQuestionsMethods.Model{
    FragmentCreateQuestionsMethods.Presenter presenter;
    public QuestionsModel(FragmentCreateQuestionsMethods.Presenter presenter){
        this.presenter = presenter;
    }


    @Override
    public void postGetMyQuestions(long id) {
        NetworkService.getInstance()
                .getJSONApi()
                .getQuestions(id,DataSingleton.getInstance().jwtToken)
                .enqueue(new Callback<GenericResponse<List<QuestionData>>>() {
                    @Override
                    public void onResponse(Call<GenericResponse<List<QuestionData>>> call, Response<GenericResponse<List<QuestionData>>> response) {
                        if(response.isSuccessful()){
                            presenter.onSuccessGetQuestions(response.body());
                        }
                        else{
                            presenter.onFailGetQuestions();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<List<QuestionData>>> call, Throwable t) {
                        presenter.onFailGetQuestions();
                    }
                });
    }
}
