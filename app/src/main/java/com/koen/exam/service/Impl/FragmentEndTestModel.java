package com.koen.exam.service.Impl;

import com.koen.exam.DataSingleton;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.QuestionAnswerModel;
import com.koen.exam.model.ScoreModel;
import com.koen.exam.model.SendQuestionAnswersModel;
import com.koen.exam.net.NetworkService;
import com.koen.exam.views.FragmentEndTestMethods;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentEndTestModel implements FragmentEndTestMethods.Model {
    FragmentEndTestMethods.Presenter presenter;
    public FragmentEndTestModel(FragmentEndTestMethods.Presenter presenter){
        this.presenter = presenter;
    }
    @Override
    public void sendRequestResults(SendQuestionAnswersModel sendQuestionAnswersModel) {
        NetworkService.getInstance()
                .getJSONApi()
                .sendMyResults(sendQuestionAnswersModel, DataSingleton.getInstance().jwtToken)
                .enqueue(new Callback<GenericResponse<ScoreModel>>() {
                    @Override
                    public void onResponse(Call<GenericResponse<ScoreModel>> call, Response<GenericResponse<ScoreModel>> response) {
                        if(response.isSuccessful()){
                            presenter.onSuccess(response.body().getResponseData());
                        }
                        else{
                            presenter.onFail();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<ScoreModel>> call, Throwable t) {
                        presenter.onFail();
                    }
                });
    }
}
