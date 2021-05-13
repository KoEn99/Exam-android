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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.koen.exam.DataSingleton;
import com.koen.exam.R;
import com.koen.exam.model.CourseInfo;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.GroupInfo;
import com.koen.exam.net.NetworkService;
import com.koen.exam.recycleAdapter.adapter.AdapterResultsCource;
import com.koen.exam.recycleAdapter.adapter.CourseAdapter;
import com.koen.exam.recycleAdapter.adapter.GroupFromEditAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentResultsTeacher extends Fragment {
    GroupFromEditAdapter groupAdapter;
    List<GroupInfo> groupInfoList;
    AdapterResultsCource courseAdapter;
    RecyclerView recyclerView;
    View v;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_test_results_teacher,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycleEditView);

        NavigationActivity navigationActivity = (NavigationActivity)getActivity();
        FloatingActionButton floatingActionButton = (FloatingActionButton)navigationActivity.findViewById(R.id.fab);
        floatingActionButton.setVisibility(View.GONE);
        getMyCourses();
        return v;
    }
    void initRecycle(List<CourseInfo> courseInfoList){
        courseAdapter = new AdapterResultsCource(courseInfoList, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(courseAdapter);
    }
    void getMyCourses(){
        NetworkService.getInstance()
                .getJSONApi()
                .getMyCourses(DataSingleton.getInstance().jwtToken)
                .enqueue(new Callback<GenericResponse<List<CourseInfo>>>() {
                    @Override
                    public void onResponse(Call<GenericResponse<List<CourseInfo>>> call, Response<GenericResponse<List<CourseInfo>>> response) {
                        initRecycle(response.body().getResponseData());
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<List<CourseInfo>>> call, Throwable t) {

                    }
                });
    }
}
