package com.studenttomsk.upYourParty.Views.Login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.studenttomsk.upYourParty.Classes.AuthorizationRequestDto;
import com.studenttomsk.upYourParty.Classes.EmailValidator;
import com.studenttomsk.upYourParty.Classes.Location;
import com.studenttomsk.upYourParty.Classes.ProfileClass;
import com.studenttomsk.upYourParty.Classes.RegistrationClass;
import com.studenttomsk.upYourParty.Classes.Singleton;
import com.studenttomsk.upYourParty.Classes.SingletonForgPassEmail;
import com.studenttomsk.upYourParty.Classes.SingletonImageProfileSocial;
import com.studenttomsk.upYourParty.Network.NetworkService;
import com.studenttomsk.upYourParty.Network.PostLoginModel;
import com.studenttomsk.upYourParty.R;
import com.studenttomsk.upYourParty.Views.ForgPass.FogPassActivity;
import com.studenttomsk.upYourParty.Views.Main.MainActivity;
import com.studenttomsk.upYourParty.Views.Register.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.SharedPreferences.*;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginMethods.View {
    ProgressBar progressBar;
    ProgressDialog pd;
    EditText email, pass;
    Button loginBtn, refreshBtn, guest_btn;
    SharedPreferences savenToken;
    SharedPreferences.Editor editor;
    TextView registrationView, fogPass;
    final String SAVED_TOKEN = "Saved_token";
    final String SAVED_REFRESH_TOKEN = "Saved_refresh";
    Editor ed;
    Toast toast;
    LoginButton loginButtonFacebook;
    Map<String,String> map = new HashMap<>();
    LoginPresenter loginPresenter;
    CallbackManager callbackManager;
    String first_name;
    String last_name;
    String emailSocial;
    String passSocial;
    String image_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fogPass = findViewById(R.id.forgPass);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        loginButtonFacebook = findViewById(R.id.circleImageView3);
        loginBtn.setOnClickListener(this);
        fogPass.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        else {
            getWindow().setStatusBarColor(Color.parseColor("#CFCFCF"));
        }
        toast =  Toast.makeText(this,"Поля должны быть заполнены",Toast.LENGTH_SHORT);
        registrationView = findViewById(R.id.registration_view);
        registrationView.setOnClickListener(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loginPresenter = new LoginPresenter(this, new PostLoginModel());
        loginButtonFacebook.setLoginText("");
        loginButtonFacebook.setCompoundDrawables(null,null,null,null);
        loginButtonFacebook.setLogoutText("");
        loginButtonFacebook.setBackgroundResource(R.drawable.facebook);
        callbackManager = CallbackManager.Factory.create();
        loginButtonFacebook.setReadPermissions(Arrays.asList("email","public_profile","user_location"));
        loginButtonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);

        super.onActivityResult(requestCode, resultCode, data);
    }
    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken == null){

            }else
                loadUserProfile(currentAccessToken);

        }
    };
    void setImageSocial(){
        savenToken = PreferenceManager.getDefaultSharedPreferences(this);
        ed = savenToken.edit();
        ed.putString("socialAuthorization","yes");
        ed.putString("imgProfile",image_url);
        SingletonImageProfileSocial.getInstance().setDid(image_url);
    }
    private void loadUserProfile(AccessToken newAccessToken){
        GraphRequest graphRequest = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                     Singleton.getInstance().setSocialEnter(true);
                     first_name = object.getString("first_name");
                     last_name = object.getString("last_name");
                     emailSocial = object.getString("email");
                     passSocial = object.getString("id");
                     String city = object.getJSONObject("location").get("name").toString();
                     image_url = "https://graph.facebook.com/"+passSocial+"/picture?type=normal";
                    NetworkService.getInstance().getJSONApi().reg(new RegistrationClass(emailSocial,passSocial,new ProfileClass(first_name,last_name,"",city,"")))
                            .enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if(response.isSuccessful()){
                                        showPd();
                                        setImageSocial();
                                        loginPresenter.sendPostToLoginUser(new AuthorizationRequestDto(emailSocial,passSocial));
                                    }
                                    else{
                                        setImageSocial();
                                        showPd();
                                        loginPresenter.sendPostToLoginUser(new AuthorizationRequestDto(emailSocial,passSocial));
                                        SingletonImageProfileSocial.getInstance().setDid(image_url);
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {

                                }
                            });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle params = new Bundle();
        params.putString("fields","first_name,last_name,email,id,location,hometown");
        graphRequest.setParameters(params);
        graphRequest.executeAsync();

    }

    @Override
    protected void onResume() {
        super.onResume();
        fogPass.setClickable(true);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.forgPass){
            SingletonForgPassEmail.getInstance().setEmail(email.getText().toString());
            fogPass.setClickable(false);
            Intent intent = new Intent(this, FogPassActivity.class);
            startActivity(intent);
        }

        if(view.getId()==R.id.registration_view){
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }


        if(view.getId() == R.id.loginBtn){
            Singleton.getInstance().setSocialEnter(false);
            String emailS, passS;
            emailS = email.getText().toString();
            passS = pass.getText().toString();
            if(!emailS.equals("") && !passS.equals("")) {
                loginBtn.setClickable(false);
                AuthorizationRequestDto authorizationRequestDto = new AuthorizationRequestDto(email.getText().toString(), pass.getText().toString());
                showPd();
                loginPresenter.sendPostToLoginUser(authorizationRequestDto);
            }
            else {
                toast.cancel();
                toast = Toast.makeText(this, "Поля должны быть заполнены", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

    }

    void toMainActivity(){
        savenToken = PreferenceManager.getDefaultSharedPreferences(this);
        editor = savenToken.edit();
        Intent intent = new Intent(this,MainActivity.class);
        Singleton.getInstance().setGuest(false);
        intent.putExtra("type","User");
        startActivity(intent);
        finish();
    }


    @Override
    public void setToSharedPreferences(Map<String, String> map) {
        savenToken = PreferenceManager.getDefaultSharedPreferences(this);
        ed = savenToken.edit();
        ed.putString(SAVED_TOKEN,"Bearer_" +map.get("token"));
        ed.putString(SAVED_REFRESH_TOKEN,map.get("token_refresh"));
        ed.commit();
        hidePd();
        Singleton.getInstance().SetToken(savenToken.getString(SAVED_TOKEN,""));
        toMainActivity();
    }

    @Override
    public void showPd() {
        pd = new ProgressDialog(this);
        pd.setTitle("Подождите");
        pd.setMessage("Вход...");
        pd.show();
    }

    @Override
    public void hidePd() {
        pd.dismiss();
    }

    @Override
    public void onResponseFailRule(String s) {
        hidePd();
        if(s.equals("")){
            toast.cancel();
            toast =  Toast.makeText(this,"Неверный логин или пароль",Toast.LENGTH_SHORT);
            toast.show();
            loginBtn.setClickable(true);
        }
        else {
            toast.cancel();
            toast =  Toast.makeText(this,"Ошибка сервера: " + s,Toast.LENGTH_SHORT);
            toast.show();
            loginBtn.setClickable(true);
        }

    }

}
