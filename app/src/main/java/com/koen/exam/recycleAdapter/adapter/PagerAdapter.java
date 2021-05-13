package com.koen.exam.recycleAdapter.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.koen.exam.model.QuestionData;
import com.koen.exam.model.TypeQuestion;
import com.koen.exam.views.Impl.FragmentEndTest;
import com.koen.exam.views.Impl.FragmentFreeAns;
import com.koen.exam.views.Impl.FragmentFreeAnswerAns;
import com.koen.exam.views.Impl.FragmentManyAnswersAns;
import com.koen.exam.views.Impl.FragmentOneAns;

import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {
    List<QuestionData> questionDataList;

    public PagerAdapter(FragmentManager fragmentManager, List<QuestionData> listQuestions){
        super(fragmentManager);
        this.questionDataList = listQuestions;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(questionDataList.get(position).getQuestionType().equals(TypeQuestion.MULTIPLE.toString())){
            return new FragmentManyAnswersAns(2,position);
        }
        else if(questionDataList.get(position).getQuestionType().equals(TypeQuestion.SINGLE.toString())){
            return new FragmentManyAnswersAns(1,position);
        }
        else if(questionDataList.get(position).getQuestionType().equals(TypeQuestion.FREE_ANSWER.toString())) {
            return new FragmentFreeAnswerAns(position);
        }
        else if(questionDataList.get(position).getQuestionType().equals("END")){
            return new FragmentEndTest();
        }
        return null;
    }

    @Override
    public int getCount() {
        return questionDataList.size();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == questionDataList.size()-1){
            return "Завершить";
        }
        return "Вопрос " + (position+1);
    }
}
