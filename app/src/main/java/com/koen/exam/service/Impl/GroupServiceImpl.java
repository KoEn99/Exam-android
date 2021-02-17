package com.koen.exam.service.Impl;

import com.koen.exam.model.CourseInfo;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.GroupInfo;
import com.koen.exam.model.UserGroup;
import com.koen.exam.net.NetworkService;
import com.koen.exam.presenter.CoursesPresenter;
import com.koen.exam.presenter.GroupPresenter;
import com.koen.exam.service.GroupService;

import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupServiceImpl implements GroupService {
    GroupPresenter groupPresenter;
    public GroupServiceImpl(GroupPresenter groupPresenter){
        this.groupPresenter = groupPresenter;
    }

    @Override
    public void findByName(String name, String authToken) {
        NetworkService.getInstance()
                .groupApi()
                .findByName(name, authToken)
                .enqueue(new Callback<GenericResponse<GroupInfo>>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<GenericResponse<GroupInfo>> call,
                                           Response<GenericResponse<GroupInfo>> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            groupPresenter.finishSearchGroup(response.body().getResponseData());
                        }
                        else groupPresenter.listenerFinishError(response.errorBody() != null ? response.errorBody().string() : null);
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<GroupInfo>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void joinInGroup(String name, String authToken) {
        NetworkService.getInstance()
                .groupApi()
                .joinInGroup(name, authToken)
                .enqueue(new Callback<GenericResponse<GroupInfo>>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<GenericResponse<GroupInfo>> call, Response<GenericResponse<GroupInfo>> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            groupPresenter.finishJoinInGroup(response.body().getResponseData());
                        }
                        else groupPresenter.listenerFinishError(response.errorBody() != null ? response.errorBody().string() : null);
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<GroupInfo>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void getMyGroup(String authToken) {
        NetworkService.getInstance()
                .groupApi()
                .getMyGroup(authToken)
                .enqueue(new Callback<GenericResponse<List<GroupInfo>>>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<GenericResponse<List<GroupInfo>>> call, Response<GenericResponse<List<GroupInfo>>> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            groupPresenter.finishGetMyGroup(response.body().getResponseData());
                        }
                        else groupPresenter.listenerFinishError(response.errorBody() != null ? response.errorBody().string() : null);
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<List<GroupInfo>>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void getGroupByCourse(String courseId, String authToken) {
        NetworkService.getInstance()
                .groupApi()
                .getGroupByCourse(courseId, authToken)
                .enqueue(new Callback<GenericResponse<List<GroupInfo>>>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<GenericResponse<List<GroupInfo>>> call,
                                           Response<GenericResponse<List<GroupInfo>>> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            groupPresenter.finishGetGroupByCourse(response.body().getResponseData());
                        }
                        else groupPresenter.listenerFinishError(response.errorBody() != null ? response.errorBody().string() : null);
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<List<GroupInfo>>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void addGroup(GroupInfo groupInfo, String authToken) {
        NetworkService.getInstance()
                .groupApi()
                .addGroup(groupInfo, authToken)
                .enqueue(new Callback<GenericResponse<GroupInfo>>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<GenericResponse<GroupInfo>> call, Response<GenericResponse<GroupInfo>> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            groupPresenter.finishAddGroup(response.body().getResponseData());
                        }
                        else groupPresenter.listenerFinishError(response.errorBody() != null ? response.errorBody().string() : null);
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<GroupInfo>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void getGroupUser(String groupName, String authToken) {
        NetworkService.getInstance()
                .groupApi()
                .getUserGroup(groupName, authToken)
                .enqueue(new Callback<GenericResponse<List<UserGroup>>>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<GenericResponse<List<UserGroup>>> call, Response<GenericResponse<List<UserGroup>>> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            groupPresenter.finishGetUserGroup(response.body().getResponseData());
                        }
                        else groupPresenter.listenerFinishError(response.errorBody() != null ? response.errorBody().string() : null);
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<List<UserGroup>>> call, Throwable t) {

                    }
                });
    }
}
