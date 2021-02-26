package com.koen.exam.views.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.koen.exam.R;
import com.koen.exam.model.AnswerData;
import com.koen.exam.model.OneAnsInfo;
import com.koen.exam.model.QuestionData;
import com.koen.exam.presenter.Impl.SaveQuestionPresenter;
import com.koen.exam.recycleAdapter.adapter.AnswersAdapter;
import com.koen.exam.views.CreateExamMethods;
import com.koen.exam.views.CreateQuestionsMethods;
import com.koen.exam.views.SaveQuestionMethods;

import java.util.ArrayList;
import java.util.List;

public class FragmentOneAns extends Fragment implements View.OnClickListener, SaveQuestionMethods.View {
    FloatingActionButton floatingActionButton;
    AnswersAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    EditText textQuestion;
    NavigationActivity navigationActivity;
    int prevPosition, typeQues;
    SaveQuestionPresenter presenter;
    List<OneAnsInfo> infoList;
    int examId;

    public FragmentOneAns(int type, int examId){
        this.examId = examId;
        this.typeQues = type;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        navigationActivity = (NavigationActivity)getActivity();
        navigationActivity.setVisibleSaveBtn(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        navigationActivity.setVisibleSaveBtn(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_ans,container,false);
        infoList = new ArrayList<>();


        floatingActionButton = navigationActivity.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
        recyclerView = view.findViewById(R.id.oneAnswerRec);
        manager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(manager);
        prevPosition = -1;
        textQuestion = view.findViewById(R.id.questionTxt);

        presenter = new SaveQuestionPresenter(this);


        adapter = new AnswersAdapter(infoList,getActivity());
        recyclerView.setAdapter(adapter);
        adapter.setInItemChangedListener(new AnswersAdapter.OnItemChangeListener() {
            @Override
            public void onEditTextChangeListener(int position, String text) {
                infoList.get(position).setAnswer(text);
            }
        });
        adapter.setOnItemClickListener(new AnswersAdapter.OnItemClickListener() {

            @Override
            public void onCheckBoxClick(int position) {
                if(typeQues == 1) {
                    infoList.get(position).setTrueAns(!infoList.get(position).getTrueAns());
                    if (prevPosition != -1 && prevPosition != position) {
                        infoList.get(prevPosition).setTrueAns(!infoList.get(prevPosition).getTrueAns());
                    }
                    adapter.notifyItemChanged(position);
                    adapter.notifyItemChanged(prevPosition);
                    prevPosition = position;
                }
                else{
                    infoList.get(position).setTrueAns(!infoList.get(position).getTrueAns());
                }
            }
        });
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String asda = "fds";
        String type = "";
        if(item.getItemId()==R.id.save_ques){

            if(typeQues == 1){
                 type = "SINGLE";
            }
            else{
                type = "MULTIPLE";
            }
            presenter.postSaveQuestion(
                    new QuestionData(textQuestion.getText().toString(),
                    type,
                            (float)1.0,
                    infoList,
                    examId
            ));
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fab){

            infoList.add(new OneAnsInfo("",false));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getActivity(),"Вопрос сохранен",Toast.LENGTH_SHORT).show();
        getFragmentManager().beginTransaction().replace(R.id.scrim,new FragmentCreateQuestions(examId,null)).commit();
    }

    @Override
    public void onFailure() {
        Toast.makeText(getActivity(),"Ошибка при создании вопроса",Toast.LENGTH_SHORT).show();
    }
}
