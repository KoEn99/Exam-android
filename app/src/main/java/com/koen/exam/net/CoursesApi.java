package com.koen.exam.net;

import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.CourseInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CoursesApi {
    @POST("/course/create")
    Call<GenericResponse<CourseInfo>> createMyCourse(@Body CourseInfo courseInfo, @Header("Authorization") String authToken);

    @GET("/course/my")
    Call<GenericResponse<List<CourseInfo>>> getMyCourses(@Header("Authorization") String authToken);
}
