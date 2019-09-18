package com.mulaitrip.mulaitrip.UI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mulaitrip.mulaitrip.API.model.Login;
import com.mulaitrip.mulaitrip.API.model.ProfileResponse;
import com.mulaitrip.mulaitrip.API.model.TokenResponse;
import com.mulaitrip.mulaitrip.API.services.ApiClient;
import com.mulaitrip.mulaitrip.API.services.ApiInterface;
import com.mulaitrip.mulaitrip.R;
import com.mulaitrip.mulaitrip.UTILS.AppPrefences;
import com.mulaitrip.mulaitrip.UTILS.Validation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity  extends AppCompatActivity{

    private EditText setemail;
    private EditText setpassword;
    private static String token;
    private  static  String id;
    private CheckBox chk_recordar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.progressBar) ;
        progressBar.setVisibility(View.GONE);
        setemail = (EditText) findViewById(R.id.email);
        setpassword = (EditText) findViewById(R.id.password);
        setpassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    signintoLogin();
                    return true;
                }
                return false;
            }
        });

        Button btn_login = (Button) findViewById(R.id.LoginButton);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                signintoLogin();
            }
        });

        chk_recordar = (CheckBox) findViewById(R.id.chk_recordar);
        Boolean recordarMail = AppPrefences.get(this).getBoolean("recordar", false);
        chk_recordar.setChecked(recordarMail);

        if(recordarMail) {
            setemail.setText(AppPrefences.get(this).getString("email", "email"));
        }

        //for changing status bar icon colors
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void signintoLogin() {
        setemail.setError(null);
        setpassword.setError(null);

        String email = setemail.getText().toString();
        String password = setpassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!Validation.tamPassword(password)) {
            setpassword.setError(getString(R.string.error_tam_password_invalida));
            focusView = setpassword;
            cancel = true;
        }

        // Valida campo Email.
        if (!Validation.tamEmail(email)) {
            setemail.setError(getString(R.string.error_tam_email_invalido));
            focusView = setemail;
            cancel = true;

        }
        else if (!Validation.formatoEmail(email)) {
            setemail.setError(getString(R.string.error_formato_email_invalido));
            focusView = setemail;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            login(email, password);
        }
    }

    private boolean esPasswordValida(String password) {
        return password.length() >= 4;
    }

    private void login(String email, String password) {
        Login login= new Login(email,password);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<TokenResponse> call = apiInterface.login(login);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if(response.isSuccessful()) {
                    token = "Token " + response.body().getToken();
                    id = response.body().getId();
                    savePreferences(token,id);
                    getUser(id, token);
                    toMain(token);
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "I'm sorry you can't login, something wrong :(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void savePreferences(String token, String id) {
        AppPrefences.getEditor(this).putString("token", token).commit();
        AppPrefences.getEditor(this).putString("id", id).commit();
        AppPrefences.getEditor(this).putString("email", setemail.getText().toString()).commit();
        AppPrefences.getEditor(this).putBoolean("recordar", chk_recordar.isChecked()).commit();
    }

    private void getUser(String id, String token) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ProfileResponse> call = apiInterface.getProfile(id, token);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse>call, Response<ProfileResponse> response) {
                if(response.isSuccessful()) {
                    ProfileResponse user = response.body();
                    saveUser(user);
                } else {
                    Toast.makeText(LoginActivity.this, "Token invalid !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
            }
        });
    }

    private void saveUser(ProfileResponse user) {
        AppPrefences.getEditor(this).putString("first_name", user.getFirstname()).commit();
        AppPrefences.getEditor(this).putString("last_name", user.getLastname()).commit();
        AppPrefences.getEditor(this).putString("email", user.getEmail()).commit();
        AppPrefences.getEditor(this).putString("username", user.getUsername()).commit();
    }

    private void toMain(String token) {
        Intent intent = new Intent(this, SplashScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

    public void onRegisterClick(View View){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }
}
