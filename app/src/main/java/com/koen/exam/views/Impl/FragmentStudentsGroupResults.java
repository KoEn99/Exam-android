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
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.ResultsOfStudents;
import com.koen.exam.net.NetworkService;
import com.koen.exam.recycleAdapter.adapter.StudentsGroupResultsAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentStudentsGroupResults extends Fragment {
    String groupId;
    int examId;
    public FragmentStudentsGroupResults(String groupId, int examId){
        this.groupId = groupId;
        this.examId = examId;
    }
    View v;
    RecyclerView recyclerView;
    StudentsGroupResultsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_group_results,container,false);
        recyclerView = v.findViewById(R.id.recycleStudentsResults);
        getStudentsResults();
        return v;
    }
    void initRecycle(List<ResultsOfStudents> list){
        layoutManager = new LinearLayoutManager(v.getContext());
        adapter = new StudentsGroupResultsAdapter(list,getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    void getStudentsResults(){
        NetworkService.getInstance().getJSONApi().getStudentResults(DataSingleton.getInstance().jwtToken,groupId,examId)
                .enqueue(new Callback<GenericResponse<List<ResultsOfStudents>>>() {
                    @Override
                    public void onResponse(Call<GenericResponse<List<ResultsOfStudents>>> call, Response<GenericResponse<List<ResultsOfStudents>>> response) {
                        if(response.isSuccessful()){
                            initRecycle(response.body().getResponseData());
                        }
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<List<ResultsOfStudents>>> call, Throwable t) {

                    }
                });
    }
}
