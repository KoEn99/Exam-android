package com.koen.exam.net;

import com.koen.exam.model.AnswerResponse;
import com.koen.exam.model.AnswersToSendData;
import com.koen.exam.model.AuthDto;
import com.koen.exam.model.CourseInfo;
import com.koen.exam.model.ExamModel;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.GroupInfo;
import com.koen.exam.model.OneAnsInfo;
import com.koen.exam.model.QuestionAnswerModel;
import com.koen.exam.model.QuestionData;
import com.koen.exam.model.ScoreModel;
import com.koen.exam.model.SendQuestionAnswersModel;
import com.koen.exam.model.SubCoursesModel;
import com.koen.exam.model.Token;
import com.koen.exam.model.UserGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {

    @POST("/try/submit")
    Call<GenericResponse<ScoreModel>> sendMyResults(@Body SendQuestionAnswersModel sendQuestionAnswersModel, @Header("Authorization") String token);

    @GET("/exam/{id}")
    Call<GenericResponse<List<QuestionData>>> getQuestionsExam(@Path("id")Long id, @Header("Authorization") String token);

    @GET("/course/{id}")
    Call<GenericResponse<SubCoursesModel>> getSubscribedExam(@Path("id") String id, @Header("Authorization") String token);

    @GET("/question/page/{questionId}")
    Call<GenericResponse<QuestionData>> getAnswers(@Path("questionId")Long id, @Header("Authorization") String token);

    @GET("/exam/get/list/{courseId}")
    Call<GenericResponse<List<ExamModel>>> getExams(@Path("courseId")String id, @Header("Authorization") String token);

    @GET("/question/get/list/{examId}")
    Call<GenericResponse<List<QuestionData>>> getQuestions(@Path("examId")Long examId, @Header("Authorization") String token);

    @POST("/question/create")
    Call<GenericResponse<CourseInfo>> createQuestion(@Header("Authorization")String token, @Body QuestionData question);

    @POST("/exam/create")
    Call<GenericResponse<ExamModel>> createExam(@Body ExamModel model, @Header("Authorization") String token);

    @POST("/course/create")
    Call<GenericResponse<CourseInfo>> createMyCourse(@Body CourseInfo courseInfo, @Header("Authorization") String authToken);

    @GET("/course/my")
    Call<GenericResponse<List<CourseInfo>>> getMyCourses(@Header("Authorization") String authToken);

    @GET("/group/find/{name}")
    Call<GenericResponse<GroupInfo>> findByName(@Path("name") String name, @Header("Authorization") String authToken);

    @GET("/group/get/my")
    Call<GenericResponse<List<GroupInfo>>> getMyGroup(@Header("Authorization") String authToken);

    @GET("/group/by/{courseId}")
    Call<GenericResponse<List<GroupInfo>>> getGroupByCourse(@Path("courseId") String courseId, @Header("Authorization") String authToken);

    @GET("/group/user/{groupName}")
    Call<GenericResponse<List<UserGroup>>> getUserGroup(@Path("groupName") String groupName, @Header("Authorization") String authToken);

    @POST("/group/join/{name}")
    Call<GenericResponse<GroupInfo>> joinInGroup(@Path("name") String name, @Header("Authorization") String authToken);

    @POST("/group/add")
    Call<GenericResponse<GroupInfo>> addGroup(@Body GroupInfo groupInfo, @Header("Authorization") String authToken);

    @POST("/login")
    Call<GenericResponse<Token>> getTokenAuth(@Header("Authorization") String authBasic);

    @POST("/register")
    Call<GenericResponse<AnswerResponse>> createUser(@Body AuthDto authDto);

}
