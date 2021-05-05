package com.koen.exam.service.Impl;

import android.provider.ContactsContract;

import com.koen.exam.DataSingleton;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.SubCoursesModel;
import com.koen.exam.net.NetworkService;
import com.koen.exam.views.SubsExamsFragmentMethods;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubsExamsFragmentModel implements SubsExamsFragmentMethods.Model {
    SubsExamsFragmentMethods.Presenter presenter;
    public SubsExamsFragmentModel(SubsExamsFragmentMethods.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void getSubsExam(String id) {
       NetworkService.getInstance()
               .getJSONApi()
               .getSubscribedExam(id, DataSingleton.getInstance().jwtToken)
               .enqueue(new Callback<GenericResponse<SubCoursesModel>>() {
                   @Override
                   public void onResponse(Call<GenericResponse<SubCoursesModel>> call, Response<GenericResponse<SubCoursesModel>> response) {
                       if(response.isSuccessful()){
                           presenter.onSuccess(response.body().getResponseData());
                       }
                       else{
                           presenter.onFail();
                       }
                   }

                   @Override
                   public void onFailure(Call<GenericResponse<SubCoursesModel>> call, Throwable t) {
                       presenter.onFail();
                   }
               });
    }
}
