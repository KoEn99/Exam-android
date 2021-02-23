package com.koen.exam.service.Impl;

import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.CourseInfo;
import com.koen.exam.net.NetworkService;
import com.koen.exam.presenter.CoursesPresenter;
import com.koen.exam.service.CoursesService;

import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursesServiceImpl implements CoursesService {
    CoursesPresenter coursesPresenter;
    public CoursesServiceImpl(CoursesPresenter coursesPresenter){
        this.coursesPresenter = coursesPresenter;
    }
    @Override
    public void getMyCourses(String authToken) {
        NetworkService.getInstance()
                .getJSONApi()
                .getMyCourses(authToken)
                .enqueue(new Callback<GenericResponse<List<CourseInfo>>>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<GenericResponse<List<CourseInfo>>> call,
                                           Response<GenericResponse<List<CourseInfo>>> response) {
                        if (response.isSuccessful()) coursesPresenter.listenerFinish(response.body());
                        else coursesPresenter.listenerFinishError(response.errorBody() != null ? response.errorBody().string() : null);
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<List<CourseInfo>>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void crateMyCourse(CourseInfo courseInfo, String authToken) {
        NetworkService.getInstance()
                .getJSONApi()
                .createMyCourse(courseInfo,authToken)
                .enqueue(new Callback<GenericResponse<CourseInfo>>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<GenericResponse<CourseInfo>> call, Response<GenericResponse<CourseInfo>> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            coursesPresenter.finishCreateMyCourse(response.body().getResponseData());
                        }
                        else coursesPresenter.listenerFinishError(response.errorBody() != null ? response.errorBody().string() : null);
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<CourseInfo>> call, Throwable t) {

                    }
                });
    }
}
