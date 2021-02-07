package com.studenttomsk.upYourParty.Views.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.studenttomsk.upYourParty.Classes.EmailValidator;
import com.studenttomsk.upYourParty.Classes.FIOValidator;
import com.studenttomsk.upYourParty.Classes.PasswordValidator;
import com.studenttomsk.upYourParty.Classes.PhoneNumberValidator;
import com.studenttomsk.upYourParty.Classes.ProfileClass;
import com.studenttomsk.upYourParty.Classes.RegistrationClass;
import com.studenttomsk.upYourParty.Classes.SingletonCity;
import com.studenttomsk.upYourParty.Network.PostRegisterModel;
import com.studenttomsk.upYourParty.R;
import com.studenttomsk.upYourParty.Views.Login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RegisterMethods.View{
    EditText email, pass,  city, name,surname, otchestvo,numberPhone;
    TextView tologin;
    Button registerButton;
    ProgressDialog pd;
    Toast toast;
    RegisterPresenter presenter;
    String cityS, emailS, passS, confpassS, nameS,surnameS, otchestvoS,numberPhoneS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        else {
            getWindow().setStatusBarColor(Color.parseColor("#CFCFCF"));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.editTextEmail);
        pass = findViewById(R.id.Pass);
        toast = Toast.makeText(this,"Пароль имеет неверный формат",Toast.LENGTH_LONG);
        city = findViewById(R.id.edit_text_city);
        name = findViewById(R.id.text_name);
        surname = findViewById(R.id.text_surname);
        otchestvo = findViewById(R.id.text_otchestv);
        numberPhone = findViewById(R.id.text_phone);
        registerButton = findViewById(R.id.buttonRegist);
        registerButton.setOnClickListener(this);

        presenter = new RegisterPresenter(this,new PostRegisterModel());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonRegist: {
                PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
                EmailValidator emailValidator = new EmailValidator();
                FIOValidator fioValidator = new FIOValidator();
                PasswordValidator passwordValidator = new PasswordValidator();
                cityS = city.getText().toString();
                emailS = email.getText().toString();
                passS = pass.getText().toString();

                nameS = name.getText().toString();
                surnameS = surname.getText().toString();
                otchestvoS = otchestvo.getText().toString();
                numberPhoneS = numberPhone.getText().toString();

                if(!cityS.equals("")  && !emailS.equals("") && !passS.equals("")
                && !nameS.equals("") && !surnameS.equals("")
                && !otchestvoS.equals("") && !numberPhoneS.equals("")) {
                    if (emailValidator.validate(emailS)) {
                        if(phoneNumberValidator.validate(numberPhoneS)){
                        if (passS.length() >= 8) {
                            if(fioValidator.validate(name.getText().toString()) && fioValidator.validate(surname.getText().toString()) && fioValidator.validate(otchestvo.getText().toString())) {
                                if(fioValidator.validate(city.getText().toString())) {
                                    if(passwordValidator.validate(pass.getText().toString())) {
                                        registerButton.setClickable(false);
                                        RegistrationClass user = new RegistrationClass(
                                                email.getText().toString(), pass.getText().toString(), new ProfileClass(name.getText().toString()
                                                , surname.getText().toString(), otchestvo.getText().toString(), city.getText().toString(), numberPhone.getText().toString()));
                                        showPd();
                                        SingletonCity.getInstance().setCity(city.getText().toString());
                                        presenter.sendPostToRegisterUser(user);
                                    }
                                    else{
                                        toast.cancel();
                                        toast = Toast.makeText(this,"Пароль имеет неверный формат",Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                }
                                else {
                                    toast.cancel();
                                    toast = Toast.makeText(this,"Поле город имеет неверный формат",Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }
                            else{
                                toast.cancel();
                                toast = Toast.makeText(this,"Поле Фамилия или Имя или Отчество имеет неверный формат",Toast.LENGTH_SHORT);
                                toast.show();
                            }

                        }
                        else {
                            toast.cancel();
                            toast = Toast.makeText(this, "Пароль слишком короткий!", Toast.LENGTH_SHORT);
                            toast.show();

                        }

                        }
                        else{
                            toast.cancel();
                            toast = Toast.makeText(this, "Некорректный номер телефона", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                    else{
                        toast.cancel();
                        toast = Toast.makeText(this, "Некорректный e-mail!", Toast.LENGTH_SHORT);
                        toast.show();

                    }
                }
                else{
                    toast.cancel();
                    toast =  Toast.makeText(this,"Заполните все поля!",Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            }

        }
    }

    @Override
    public void toLoginActivity() {
        hidePd();
        Toast.makeText(this,"Вы успешно зарегистрированы ",Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void showPd() {
        pd = new ProgressDialog(this);
        pd.setTitle("Подождите");
        pd.setMessage("Регистрация...");
        pd.show();
    }

    @Override
    public void hidePd() {
        pd.dismiss();
    }

    @Override
    public void onResponseFailRule(String t) {
        hidePd();
        if(t.equals("409")){
            toast.cancel();
            toast = Toast.makeText(this,"Данный e-mail адресс уже используется",Toast.LENGTH_LONG);
            toast.show();
            registerButton.setClickable(true);
        }

    }
}
