package com.koen.exam.views.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.koen.exam.DataSingleton;
import com.koen.exam.R;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.OneAnsInfo;
import com.koen.exam.model.QuestionData;
import com.koen.exam.model.TypeQuestion;
import com.koen.exam.presenter.Impl.PassingTestFragmentPresenter;
import com.koen.exam.recycleAdapter.adapter.PagerAdapter;
import com.koen.exam.views.PassingTestFragmentMethods;

import java.util.ArrayList;
import java.util.List;

public class FragmentPassingTest extends Fragment implements PassingTestFragmentMethods.View {
    TabLayout tabLayout;
    ViewPager viewPager2;
    Long idExam;
    List<QuestionData> questionDataList;
    public FragmentPassingTest(Long idExam){
        this.idExam = idExam;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_passing_test,container,false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager2 = view.findViewById(R.id.pagerQ);

        PassingTestFragmentPresenter presenter = new PassingTestFragmentPresenter(this);
        presenter.postRequestGetQuestionsExam(idExam);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

         return view;
    }

    @Override
    public void onSuccessLoadQuestions(GenericResponse<List<QuestionData>> questions) {
        questionDataList = questions.getResponseData();
        questionDataList.add(new QuestionData("","END",(float)0.0,null,null));
        DataSingleton.getInstance().questionDataList = questionDataList;

        for(int i = 0;i<questionDataList.size()-1;i++){
            tabLayout.addTab(tabLayout.newTab().setText("Вопрос " + String.valueOf(i+1)));
        }
        tabLayout.addTab(tabLayout.newTab().setText("Завершить"));
        PagerAdapter pagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(),DataSingleton.getInstance().questionDataList);
        viewPager2.setAdapter(pagerAdapter);
    }

    @Override
    public void onFailLoadQuestions() {
        Toast.makeText(getActivity(),"Ошибка загрузки вопросов", Toast.LENGTH_SHORT).show();
    }
}
