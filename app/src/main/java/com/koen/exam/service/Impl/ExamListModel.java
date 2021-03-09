package com.koen.exam.service.Impl;

import com.koen.exam.DataSingleton;
import com.koen.exam.model.ExamModel;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.net.NetworkService;
import com.koen.exam.presenter.Impl.ExamListPresenter;
import com.koen.exam.views.FragmentExamListMethods;

import java.nio.file.Path;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamListModel implements FragmentExamListMethods.Model {
    FragmentExamListMethods.Presenter presenter;
    public ExamListModel(FragmentExamListMethods.Presenter presenter){
        this.presenter = presenter;
    }
    @Override
    public void getExams(String id) {
        NetworkService.getInstance()
                .getJSONApi()
                .getExams(id, DataSingleton.getInstance().jwtToken)
                .enqueue(new Callback<GenericResponse<List<ExamModel>>>() {
                    @Override
                    public void onResponse(Call<GenericResponse<List<ExamModel>>> call, Response<GenericResponse<List<ExamModel>>> response) {
                        if(response.isSuccessful()){
                            presenter.onSuccess(response.body());
                        }
                        else{
                            presenter.onFail();
                        }
                    }
                    @Override
                    public void onFailure(Call<GenericResponse<List<ExamModel>>> call, Throwable t) {
                        presenter.onFail();
                    }
                });
    }
}
