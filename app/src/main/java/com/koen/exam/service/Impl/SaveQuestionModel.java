package com.koen.exam.service.Impl;

import android.os.NetworkOnMainThreadException;

import com.koen.exam.DataSingleton;
import com.koen.exam.model.CourseInfo;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.OneAnsInfo;
import com.koen.exam.model.QuestionData;
import com.koen.exam.net.NetworkService;
import com.koen.exam.views.FragmentAnswersMethods;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveQuestionModel implements FragmentAnswersMethods.Model {
    FragmentAnswersMethods.Presenter presenter;
    public SaveQuestionModel(FragmentAnswersMethods.Presenter presenter){
        this.presenter = presenter;
    }


    @Override
    public void loadAnswers(long id) {
        NetworkService
                .getInstance()
                .getJSONApi()
                .getAnswers(id,DataSingleton.getInstance().jwtToken)
                .enqueue(new Callback<GenericResponse<QuestionData>>() {
                    @Override
                    public void onResponse(Call<GenericResponse<QuestionData>> call, Response<GenericResponse<QuestionData>> response) {
                        if(response.isSuccessful()){
                            presenter.onSuccessRequestAnswers(response.body());
                        }
                        else {
                            presenter.onFailRequestAnswers(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<QuestionData>> call, Throwable t) {
                        presenter.onFailRequest();
                    }
                });
    }

    @Override
    public void saveQuestion(QuestionData questionData) {
        NetworkService
                .getInstance()
                .getJSONApi()
                .createQuestion(DataSingleton.getInstance().jwtToken,questionData)
                .enqueue(new Callback<GenericResponse<CourseInfo>>() {
                    @Override
                    public void onResponse(Call<GenericResponse<CourseInfo>> call, Response<GenericResponse<CourseInfo>> response) {
                        if(response.isSuccessful()){
                            presenter.onSuccessRequest();
                        }
                        else{
                            presenter.onFailRequest();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<CourseInfo>> call, Throwable t) {
                        presenter.onFailRequest();
                    }
                });
    }
}
