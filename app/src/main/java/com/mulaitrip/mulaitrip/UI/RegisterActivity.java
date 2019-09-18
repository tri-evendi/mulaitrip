package com.mulaitrip.mulaitrip.UI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mulaitrip.mulaitrip.API.model.ApiError;
import com.mulaitrip.mulaitrip.API.model.User;
import com.mulaitrip.mulaitrip.API.model.UserResponse;
import com.mulaitrip.mulaitrip.API.services.ApiClient;
import com.mulaitrip.mulaitrip.API.services.ApiInterface;
import com.mulaitrip.mulaitrip.R;
import com.mulaitrip.mulaitrip.UTILS.AppPrefences;
import com.mulaitrip.mulaitrip.UTILS.Validation;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    ProgressBar progressBar;
    Button btn_register;
    private static String token;
    private EditText setfirstname, setlastname, setusername, setemail,setpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        token = AppPrefences.get(this).getString("token", "token");

        progressBar = (ProgressBar) findViewById(R.id.progressBar) ;
        progressBar.setVisibility(View.GONE);
        setfirstname = (EditText) findViewById(R.id.firstname);
        setlastname = (EditText) findViewById(R.id.lastname);
        setusername = (EditText) findViewById(R.id.username);
        setemail = (EditText) findViewById(R.id.email);
        setpassword = (EditText) findViewById(R.id.password);

        btn_register = (Button) findViewById(R.id.RegisterButton);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(setfirstname.getText().toString(), setlastname.getText().toString(),setusername.getText().toString(), setemail.getText().toString(),  setpassword.getText().toString()
                        );
                progressBar.setVisibility(View.VISIBLE);
                intentRegister(user);
            }
        });
        changeStatusBarColor();
    }

    private void intentRegister(User user) {
        setusername.setError(null);
        setfirstname.setError(null);
        setlastname.setError(null);
        setemail.setError(null);
        setpassword.setError(null);

        //tomo el contenido de los campos
        String username = setusername.getText().toString();
        String firstname = setfirstname.getText().toString();
        String lastname = setlastname.getText().toString();
        String email = setemail.getText().toString();
        String password = setpassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Valida campo Email.
        if (!Validation.tamEmail(email)) {
            setemail.setError(getString(R.string.error_tam_email_invalido));
            focusView = setemail;
            cancel = true;
        } else if (!Validation.formatoEmail(email)) {
            setemail.setError(getString(R.string.error_formato_email_invalido));
            focusView = setemail;
            cancel = true;
        }

        // Valida campo firstname
        if(!Validation.tamNombre(firstname)) {
            setfirstname.setError(getString(R.string.error_tam_apellidos_invalido));
            focusView = setfirstname;
            cancel = true;
        }

        // Valida campo lastname
        if(!Validation.tamNombre(lastname)) {
            setlastname.setError(getString(R.string.error_tam_nombre_invalido));
            focusView = setlastname;
            cancel = true;
        }

        // Valida campo username
        if(!Validation.tamNombre(username)) {
            setusername.setError(getString(R.string.error_tam_nombre_invalido));
            focusView = setusername;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            register(user);
        }
    }

    private void register(User user) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<UserResponse> call = apiInterface.register(user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Create user " + response.body().getEmail(), Toast.LENGTH_LONG).show();
                } else {
                    if (response.errorBody().contentType().subtype().equals("json")) {
                        ApiError apiError = ApiError.fromResponseBody(response.errorBody());
                        Toast.makeText(RegisterActivity.this, apiError.getMessage(), Toast.LENGTH_LONG).show();
                        Log.d(TAG, apiError.getPath() + " " + apiError.getMessage());
                    } else {
                        try {
                            Log.d(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}
