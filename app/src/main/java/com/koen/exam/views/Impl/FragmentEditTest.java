package com.koen.exam.views.Impl;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.koen.exam.DataSingleton;
import com.koen.exam.R;
import com.koen.exam.model.ExamModel;
import com.koen.exam.views.FragmentCreateQuestionsMethods;

public class FragmentEditTest extends Fragment implements View.OnClickListener {

    EditText title, description;
    MaterialTimePicker materialTimePicker;
    FloatingActionButton fabSetTimer;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        ((NavigationActivity)getActivity()).setVisibleSaveBtn(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_test,container,false);
        title = view.findViewById(R.id.editTestName);
        description = view.findViewById(R.id.editTestDescription);
        String text = DataSingleton.getInstance().descTest;
        title.setText(DataSingleton.getInstance().titleTest);
        description.setText(DataSingleton.getInstance().descTest);
        FloatingActionButton floatingActionButton = getActivity().findViewById(R.id.fab);
        floatingActionButton.hide();
        fabSetTimer = view.findViewById(R.id.setTimeTest);
        fabSetTimer.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.setTimeTest){
            materialTimePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H).build();
            materialTimePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            materialTimePicker.show(getActivity().getSupportFragmentManager(),"tag");
        }
    }
}
