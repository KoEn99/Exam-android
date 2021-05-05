package com.koen.exam.service.Impl;

import com.koen.exam.DataSingleton;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.QuestionData;
import com.koen.exam.net.NetworkService;
import com.koen.exam.views.PassingTestFragmentMethods;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassingTestFragmentModel implements PassingTestFragmentMethods.Model{
    PassingTestFragmentMethods.Presenter presenter;
    public PassingTestFragmentModel(PassingTestFragmentMethods.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void getExamQuestions(Long id) {
        NetworkService
                .getInstance()
                .getJSONApi()
                .getQuestionsExam(id,DataSingleton.getInstance().jwtToken)
                .enqueue(new Callback<GenericResponse<List<QuestionData>>>() {
                    @Override
                    public void onResponse(Call<GenericResponse<List<QuestionData>>> call, Response<GenericResponse<List<QuestionData>>> response) {
                        if(response.isSuccessful()){
                            presenter.onSuccess(response.body());
                        }
                        else{
                            presenter.onFail();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<List<QuestionData>>> call, Throwable t) {
                        presenter.onFail();
                    }
                });
    }
}
