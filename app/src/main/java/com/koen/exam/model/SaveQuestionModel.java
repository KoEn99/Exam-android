package com.koen.exam.model;

import com.koen.exam.DataSingleton;
import com.koen.exam.net.NetworkService;
import com.koen.exam.views.SaveQuestionMethods;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveQuestionModel implements SaveQuestionMethods.Model {
    SaveQuestionMethods.Presenter presenter;
    public SaveQuestionModel(SaveQuestionMethods.Presenter presenter){
        this.presenter = presenter;
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
