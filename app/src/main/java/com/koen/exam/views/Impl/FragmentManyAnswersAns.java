package com.koen.exam.views.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koen.exam.DataSingleton;
import com.koen.exam.R;
import com.koen.exam.model.OneAnsInfo;
import com.koen.exam.model.QuestionData;
import com.koen.exam.recycleAdapter.adapter.ManyAnswAnsAdapter;
import com.koen.exam.views.FragmentCreateQuestionsMethods;

import java.util.ArrayList;
import java.util.List;

public class FragmentManyAnswersAns extends Fragment {
    RecyclerView recyclerView;
    ManyAnswAnsAdapter adapter;
    LinearLayoutManager layoutManager;
    QuestionData questionData;
    int prevPosition = -1;
    int dataPosition;
    TextView questionView;
    List<OneAnsInfo>listAns;
    int type;
    public FragmentManyAnswersAns(int type,int dataPosition){
        this.dataPosition = dataPosition;
        this.type = type;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_many_answ_ans,container,false);
        questionView = v.findViewById(R.id.textViewQuestion);

        questionView.setText(DataSingleton.getInstance().questionDataList.get(dataPosition).getQuestion());
        recyclerView = v.findViewById(R.id.manyAnswAns);
        adapter = new ManyAnswAnsAdapter(DataSingleton.getInstance().questionDataList.get(dataPosition).getAnswers(),getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(adapter);
        for(int i = 0;i<DataSingleton.getInstance().questionDataList.get(dataPosition).getAnswers().size();i++){
           if(DataSingleton.getInstance().questionDataList.get(dataPosition).getAnswers().get(i).getTrueAns()==null){
               DataSingleton.getInstance().questionDataList.get(dataPosition).getAnswers().get(i).setTrueAns(false);
           }
        }

        recyclerView.setLayoutManager(layoutManager);
        adapter.setOnItemClickListener(new ManyAnswAnsAdapter.OnItemClickListener() {
            @Override
            public void onCheckBoxClick(int position) {
                if(type == 1) {
                    DataSingleton.getInstance().
                            questionDataList.
                            get(dataPosition).
                            getAnswers().get(position).setTrueAns(!DataSingleton.getInstance().questionDataList.get(dataPosition).getAnswers().get(position).getTrueAns());
                    if (prevPosition != -1 && prevPosition != position) {
                        DataSingleton.getInstance().questionDataList.get(dataPosition).getAnswers().get(prevPosition)
                                .setTrueAns(!DataSingleton.getInstance().questionDataList.get(dataPosition).getAnswers()
                                        .get(prevPosition).getTrueAns());
                    }
                    adapter.notifyItemChanged(position);
                    adapter.notifyItemChanged(prevPosition);
                    prevPosition = position;
                }
                else{
                    DataSingleton.getInstance()
                            .questionDataList.
                            get(dataPosition).
                            getAnswers()
                            .get(position)
                            .setTrueAns(!DataSingleton.getInstance().questionDataList.get(dataPosition).getAnswers()
                                    .get(position)
                                    .getTrueAns());
                }

            }
        });
        return v;
    }
}
