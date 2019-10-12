package com.wedontanything.usedmarket.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wedontanything.usedmarket.Interface.UserService;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Response.Response;
import com.wedontanything.usedmarket.User.User;
import com.wedontanything.usedmarket.Utils;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button signupButton;
    private Button passwordFindButton;

    private EditText id;
    private EditText pw;

    UserService service = Utils.RETROFIT.create(UserService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        loginButton = findViewById(R.id.loginButtonLogin);
        signupButton = findViewById(R.id.loginButtonSignUp);
        passwordFindButton = findViewById(R.id.passwordFind);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        passwordFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, PasswordFindActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {

        id = findViewById(R.id.loginEditId);
        pw = findViewById(R.id.loginEditPassword);

        Call<retrofit2.Response<Response<User>>> postLogin = service.postLogin(id.getText().toString(),
                pw.getText().toString());

        postLogin.enqueue(new Callback<retrofit2.Response<Response<User>>>() {
            @Override
            public void onResponse(Call<retrofit2.Response<Response<User>>> call, retrofit2.Response<retrofit2.Response<Response<User>>> response) {
                //User user = response.body();

                Log.d("성공", "onResponse: " + response.message());

                if (response.code() == 200) {
                    Toast.makeText(LoginActivity.this, "로그인에 성공하였습니다.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else if(response.code() == 401) {

                }


//                if (user != null)
//                {
//                    Log.d("null", "onResponse: null ");
//                }
            }

            @Override
            public void onFailure(Call<retrofit2.Response<Response<User>>> call, Throwable t) {
                Log.d("실패", "onFailure: " + t.toString());
            }
        });


    }
}
