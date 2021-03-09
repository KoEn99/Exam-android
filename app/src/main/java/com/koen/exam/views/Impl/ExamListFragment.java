package com.koen.exam.views.Impl;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.koen.exam.DataSingleton;
import com.koen.exam.R;
import com.koen.exam.model.ExamModel;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.presenter.Impl.ExamListPresenter;
import com.koen.exam.recycleAdapter.adapter.ExamListAdapter;
import com.koen.exam.views.CreateExamMethods;
import com.koen.exam.views.FragmentExamListMethods;
import com.koen.exam.views.dialogs.SheetCreateExam;

import java.util.ArrayList;
import java.util.List;

public class ExamListFragment extends Fragment implements CreateExamMethods.View, FragmentExamListMethods.View {
    FloatingActionButton floatingActionButton;
    String courseId;
    NavigationActivity navigationActivity;
    List<ExamModel> examModelList;
    RecyclerView recyclerView;
    ExamListAdapter adapter;
    RecyclerView.LayoutManager manager;

    public ExamListFragment(String courseId){
        this.courseId = courseId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam_list, container, false);
        examModelList = new ArrayList<>();
        navigationActivity = (NavigationActivity)getActivity();
        navigationActivity.visibleArrow();
        navigationActivity.getSupportActionBar().setTitle("Редактирование курса");
        floatingActionButton = (FloatingActionButton)navigationActivity.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.recycleExamView);
        manager = new LinearLayoutManager(view.getContext());
        adapter = new ExamListAdapter(examModelList,getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        ExamListPresenter presenter = new ExamListPresenter(this);
        presenter.postGetExams(courseId);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SheetCreateExam sheetCreateExam = new SheetCreateExam(ExamListFragment.this,courseId);
                sheetCreateExam.show(getFragmentManager(),"examCreateSheet");
            }
        });
        return view;
    }

    @Override
    public void onSuccess(GenericResponse<ExamModel> model) {
        int id = model.getResponseData().getId();
        Toast.makeText(getContext(),"Тест успешно создан",Toast.LENGTH_SHORT).show();

        getActivity().getSupportFragmentManager().
                beginTransaction().addToBackStack("ExamListFragment").
                replace(R.id.scrim, new FragmentCreateQuestions(id,courseId))
                .commit();
    }

    @Override
    public void onFail() {
        Toast.makeText(getContext(),"Ошибка при создании теста",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessGetExams(GenericResponse<List<ExamModel>> model) {
        examModelList = model.getResponseData();
        adapter = new ExamListAdapter(examModelList,getActivity());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailureGetExams() {
        Toast.makeText(getContext(),"Ошибка получения экзаменов",Toast.LENGTH_SHORT).show();
    }
}