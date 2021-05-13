package com.koen.exam.views.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koen.exam.DataSingleton;
import com.koen.exam.R;
import com.koen.exam.model.ExamModel;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.net.NetworkService;
import com.koen.exam.recycleAdapter.adapter.AdapterExamListResults;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentExamsCourseResults extends Fragment {
    String courseId;
    public FragmentExamsCourseResults(String courseId){
        this.courseId = courseId;

    }
    RecyclerView recyclerView;
    AdapterExamListResults adapterExamListResults;
    RecyclerView.LayoutManager layoutManager;
    View v;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_results_exam_of_course,container,false);
        recyclerView = v.findViewById(R.id.recycleExamView);
        getMyExams();
        return v;
    }
    void initRecycle(List<ExamModel> examModels){
        adapterExamListResults = new AdapterExamListResults(examModels,getActivity(),courseId);
        layoutManager = new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterExamListResults);

    }
    void getMyExams(){
        NetworkService.getInstance()
                .getJSONApi()
                .getExams(courseId, DataSingleton.getInstance().jwtToken)
                .enqueue(new Callback<GenericResponse<List<ExamModel>>>() {
                    @Override
                    public void onResponse(Call<GenericResponse<List<ExamModel>>> call, Response<GenericResponse<List<ExamModel>>> response) {
                        initRecycle(response.body().getResponseData());
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<List<ExamModel>>> call, Throwable t) {

                    }
                });
    }
}
