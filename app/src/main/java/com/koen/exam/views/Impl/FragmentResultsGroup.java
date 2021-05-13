package com.koen.exam.views.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koen.exam.DataSingleton;
import com.koen.exam.R;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.GroupInfo;
import com.koen.exam.net.NetworkService;
import com.koen.exam.recycleAdapter.adapter.AdapterGroupsResults;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentResultsGroup extends Fragment {
    String courseId;
    int idExam;
    public FragmentResultsGroup(String courseId, int idExam){
        this.courseId = courseId;
        this.idExam = idExam;
    }
    View v;
    RecyclerView recyclerView;
    AdapterGroupsResults adapterGroupsResults;
    RecyclerView.LayoutManager layoutManager;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_groups_results,container,false);
        recyclerView = v.findViewById(R.id.recycleGroupView);
        getGroups();
        return v;
    }
    void getGroups(){
        NetworkService.getInstance()
                .getJSONApi()
                .getGroupByCourse(courseId, DataSingleton.getInstance().jwtToken)
                .enqueue(new Callback<GenericResponse<List<GroupInfo>>>() {
                    @Override
                    public void onResponse(Call<GenericResponse<List<GroupInfo>>> call, Response<GenericResponse<List<GroupInfo>>> response) {
                       if(response.isSuccessful()){
                           initRecycle(response.body().getResponseData());
                       }
                       else{
                           Toast.makeText(getActivity(),"ОЩТБКА",Toast.LENGTH_LONG).show();
                       }
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<List<GroupInfo>>> call, Throwable t) {

                    }
                });
    }
    void initRecycle(List<GroupInfo> list){
        adapterGroupsResults = new AdapterGroupsResults(list,getActivity(),courseId,idExam);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGroupsResults);
    }
}
