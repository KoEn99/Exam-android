package com.koen.exam.service.Impl;

import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.GroupInfo;
import com.koen.exam.model.Token;
import com.koen.exam.net.NetworkService;
import com.koen.exam.presenter.CoursesPresenter;
import com.koen.exam.service.CoursesService;

import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Header;

public class CoursesServiceImpl implements CoursesService {
    CoursesPresenter coursesPresenter;
    public CoursesServiceImpl(CoursesPresenter coursesPresenter){
        this.coursesPresenter = coursesPresenter;
    }
    @Override
    public void getMyCourses(String authToken) {
        NetworkService.getInstance()
                .coursesApi()
                .getMyCourses(authToken)
                .enqueue(new Callback<GenericResponse<List<GroupInfo>>>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<GenericResponse<List<GroupInfo>>> call,
                                           Response<GenericResponse<List<GroupInfo>>> response) {
                        if (response.isSuccessful()) coursesPresenter.listenerFinish(response.body());
                        else coursesPresenter.listenerFinishError(response.errorBody() != null ? response.errorBody().string() : null);
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<List<GroupInfo>>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void crateMyCourse(GroupInfo groupInfo ,String authToken) {
        NetworkService.getInstance()
                .coursesApi()
                .createMyCourse(groupInfo,authToken)
                .enqueue(new Callback<GenericResponse<GroupInfo>>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<GenericResponse<GroupInfo>> call, Response<GenericResponse<GroupInfo>> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            coursesPresenter.finishCreateMyCourse(response.body().getResponseData());
                        }
                        else coursesPresenter.listenerFinishError(response.errorBody() != null ? response.errorBody().string() : null);
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<GroupInfo>> call, Throwable t) {

                    }
                });
    }
}
