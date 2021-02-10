package com.koen.exam.net;

import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.CourseInfo;
import com.koen.exam.model.GroupInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GroupApi {
    @GET("/group/find/{name}")
    Call<GenericResponse<GroupInfo>> findByName(@Path("name") String name, @Header("Authorization") String authToken);
    @GET("/group/get/my")
    Call<GenericResponse<List<GroupInfo>>> getMyGroup(@Header("Authorization") String authToken);
    @POST("/group/join/{name}")
    Call<GenericResponse<GroupInfo>> joinInGroup(@Path("name") String name, @Header("Authorization") String authToken);
}
