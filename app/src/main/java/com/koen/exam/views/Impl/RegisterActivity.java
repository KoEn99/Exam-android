package com.koen.exam.views.Impl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.koen.exam.R;
import com.koen.exam.model.AuthDto;
import com.koen.exam.presenter.Impl.RegisterPresenterImpl;
import com.koen.exam.presenter.RegisterPresenter;
import com.koen.exam.views.RegisterView;

import org.w3c.dom.Text;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RegisterView {
    TextInputEditText loginEditText, passwordEditText, repeatPasswordEditText,
    nameEditText, secondNameEditText, middleNameEditText;
    Button registerButton;
    RegisterPresenter registerPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                onBackPressed();// возврат на предыдущий activity
            }
        });
        loginEditText = (TextInputEditText)findViewById(R.id.loginEditText);
        passwordEditText = (TextInputEditText)findViewById(R.id.passwordEditText);
        repeatPasswordEditText = (TextInputEditText)findViewById(R.id.repeatPasswordEditText);
        nameEditText = (TextInputEditText)findViewById(R.id.nameEditText);
        secondNameEditText = (TextInputEditText)findViewById(R.id.secondEditText);
        middleNameEditText = (TextInputEditText)findViewById(R.id.middleEditText);
        registerButton = (Button)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);
        registerPresenter = new RegisterPresenterImpl(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerButton:{
                AuthDto authDto = new AuthDto(
                        Objects.requireNonNull(loginEditText.getText()).toString(),
                        Objects.requireNonNull(passwordEditText.getText()).toString(),
                        Objects.requireNonNull(nameEditText.getText()).toString(),
                        Objects.requireNonNull(secondNameEditText.getText()).toString(),
                        Objects.requireNonNull(middleNameEditText.getText()).toString(),
                        "USER");
                registerPresenter.registerUser(authDto);
                break;
            }
        }
    }

    @Override
    public void createToast(String toastMessage) {
        Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
    }
}