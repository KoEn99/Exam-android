package com.koen.exam.views.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.koen.exam.R;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.QuestionData;
import com.koen.exam.presenter.Impl.QuestionsListPresenter;
import com.koen.exam.recycleAdapter.adapter.QuestionsAdapter;
import com.koen.exam.views.FragmentCreateQuestionsMethods;
import com.koen.exam.views.dialogs.SheetCreateQuestion;

import java.util.ArrayList;
import java.util.List;

public class FragmentCreateQuestions extends Fragment implements View.OnClickListener, FragmentCreateQuestionsMethods.View {
    FloatingActionButton fab;
    String groupId;
    RecyclerView recyclerView;
    QuestionsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<QuestionData> questionList;
    QuestionsListPresenter presenter;

    MaterialTimePicker materialTimePicker;

    int idExam;
    NavigationActivity navigationActivity;
    public FragmentCreateQuestions(int idExam, String groupId){
        this.idExam = idExam;
        this.groupId = groupId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        ((NavigationActivity) getActivity()).setVisibleEditBtn(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.edit_test){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.scrim,new FragmentEditTest()).addToBackStack(null)
                    .commit();
                    return true;
        }
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_questions,container,false);

        questionList = new ArrayList<>();
        navigationActivity = (NavigationActivity)getActivity();
        navigationActivity.getSupportActionBar().setTitle("Создание вопросов");
        fab = navigationActivity.findViewById(R.id.fab);

        fab.setOnClickListener(this);
        fab.show();
        recyclerView = view.findViewById(R.id.recycleQuestions);
        navigationActivity.bottomAppBar.performShow();
        presenter = new QuestionsListPresenter(this);
        presenter.postGetQuestions((long)idExam);


        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fab){
            SheetCreateQuestion sheetCreateQuestion = new SheetCreateQuestion(groupId, idExam);
            sheetCreateQuestion.show(navigationActivity.getSupportFragmentManager(),"createQuesTag");
            navigationActivity.backgroundNavigation.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onSuccessResponse(GenericResponse<List<QuestionData>> data) {
        questionList = data.getResponseData();
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new QuestionsAdapter(questionList,getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailResponse() {
        Toast.makeText(getActivity(),"Ошибка получения вопросов теста",Toast.LENGTH_SHORT).show();
    }
}
