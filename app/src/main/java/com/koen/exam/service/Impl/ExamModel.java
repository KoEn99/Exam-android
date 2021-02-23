package com.koen.exam.service.Impl;

import com.koen.exam.model.CourseInfo;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.net.NetworkService;
import com.koen.exam.presenter.Impl.ExamPresenterImpl;
import com.koen.exam.views.CreateExamMethods;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamModel implements CreateExamMethods.Model {
    CreateExamMethods.Presenter presenter;
    public ExamModel (CreateExamMethods.Presenter examPresenter){
        presenter = examPresenter;
    }

    @Override
    public void createExam(com.koen.exam.model.ExamModel modelExam, String token) {
        NetworkService.getInstance()
                .getJSONApi()
                .createExam(modelExam,token)
                .enqueue(new Callback<GenericResponse<CourseInfo>>() {
                    @Override
                    public void onResponse(Call<GenericResponse<CourseInfo>> call, Response<GenericResponse<CourseInfo>> response) {
                        if(response.isSuccessful()){
                            presenter.onSuccessResponse();
                        }
                        else{
                            presenter.onFailResponse();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<CourseInfo>> call, Throwable t) {
                        presenter.onFailResponse();
                    }
                });

    }
}
