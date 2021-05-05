package com.koen.exam.views.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koen.exam.R;
import com.koen.exam.model.ExamModel;
import com.koen.exam.model.SubCoursesModel;
import com.koen.exam.presenter.Impl.SubsExamsFragmentPresenter;
import com.koen.exam.recycleAdapter.adapter.SubsExamsAdapter;
import com.koen.exam.views.SubsExamsFragmentMethods;

import java.util.ArrayList;
import java.util.List;

public class FragmentSubsExams extends Fragment implements SubsExamsFragmentMethods.View {
    RecyclerView subsExamsRec;
    SubsExamsAdapter adapter;
    List<ExamModel> examList;
    LinearLayoutManager layoutManager;
    String idCourse;
    public FragmentSubsExams(String id){
        this.idCourse = id;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_subscribed_exams,container,false);
        examList = new ArrayList<>();
        SubsExamsFragmentPresenter presenter = new SubsExamsFragmentPresenter(this);
        presenter.postRequestSubsExams(idCourse);
        subsExamsRec = v.findViewById(R.id.recycleSubsExams);
        layoutManager = new LinearLayoutManager(v.getContext());
        return v;
    }

    @Override
    public void onSuccessLoadExams(SubCoursesModel subCoursesModel) {
        subsExamsRec.setLayoutManager(layoutManager);
        examList = subCoursesModel.getExamDtoList();
        adapter = new SubsExamsAdapter(examList,getActivity());
        subsExamsRec.setAdapter(adapter);
    }

    @Override
    public void onFailLoadExams() {
        Toast.makeText(getActivity(),"Ошибка загрузки тестов",Toast.LENGTH_SHORT).show();
    }
}
