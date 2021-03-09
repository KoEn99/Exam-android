package com.koen.exam.views.Impl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        String email, password, name, secondName, middleName;
        email = loginEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        name = nameEditText.getText().toString();
        secondName = secondNameEditText.getText().toString().trim();
        middleName = middleNameEditText.getText().toString().trim();
        switch (v.getId()){
            case R.id.registerButton:{
                if(!email.equals("")&&!password.equals("")&&!name.equals("")&&!secondName.equals("")&&!middleName.equals("")){
                    AuthDto authDto = new AuthDto(
                            Objects.requireNonNull(email),
                            Objects.requireNonNull(password),
                            Objects.requireNonNull(name),
                            Objects.requireNonNull(secondName),
                            Objects.requireNonNull(middleName),
                            "USER");
                    registerPresenter.registerUser(authDto);
                }
                else{
                    Toast.makeText(this,"Все поля должны быть заполнены",Toast.LENGTH_SHORT).show();
                }

                break;
            }
        }
    }

    @Override
    public void createToast(String toastMessage) {
        Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailMessage() {
        Toast.makeText(getApplicationContext(),"Непредвиденная ошибка",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(getApplicationContext(),"Вы успешно зарегистрировались!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}