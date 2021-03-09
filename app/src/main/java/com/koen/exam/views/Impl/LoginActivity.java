package com.koen.exam.views.Impl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.koen.exam.DataSingleton;
import com.koen.exam.MainActivity;
import com.koen.exam.R;
import com.koen.exam.presenter.AuthPresenter;
import com.koen.exam.presenter.Impl.AuthPresenterImpl;
import com.koen.exam.service.AuthService;
import com.koen.exam.views.LoginView;

import org.w3c.dom.Text;



public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {
    Button postAuthButton;
    TextInputEditText emailEditText, passwordEditText;
    TextView registerTextView;
    AuthPresenter authPresenter;
    DataSingleton dataSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        postAuthButton = (Button) findViewById(R.id.postAuthButton);
        emailEditText = (TextInputEditText) findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = (TextInputEditText) findViewById(R.id.editTextTextPassword);
        registerTextView = (TextView)findViewById(R.id.registerTextView);
        postAuthButton.setOnClickListener(this);
        registerTextView.setOnClickListener(this);
        authPresenter = new AuthPresenterImpl(this);
        dataSingleton = DataSingleton.getInstance();
    }

    @Override
    public void createToast(String toastMessage) {
        Toast toast = Toast.makeText(getApplicationContext(),
                toastMessage, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void showFailMessage() {

    }

    @Override
    public void showSuccessMessage() {

    }

    @Override
    public void saveJwtToken(String jwtToken){
        dataSingleton.saveInSharedPreferencesString("jwtToken", "Bearer " + jwtToken);
        dataSingleton.jwtToken = "Bearer " + jwtToken;
    }

    @Override
    public void selectActivity(){
        Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
        startActivity(intent);
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.postAuthButton: {
                if(!emailEditText.getText().toString().trim().equals("") && !passwordEditText.getText().toString().trim().equals("")){
                    authPresenter.loginUser(emailEditText.getText().toString().trim() + ":" +
                            passwordEditText.getText().toString().trim());
                }
                else{
                    Toast.makeText(this,"Все поля должны быть заполнены",Toast.LENGTH_SHORT).show();
                }

                break;
            }
            case R.id.registerTextView:{
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        }
    }
}