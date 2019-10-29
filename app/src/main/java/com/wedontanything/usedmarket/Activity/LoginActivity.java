package com.wedontanything.usedmarket.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wedontanything.usedmarket.DataBase.TokenManager;
import com.wedontanything.usedmarket.Interface.UserService;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Response.Response;
import com.wedontanything.usedmarket.User.LoginData;
import com.wedontanything.usedmarket.User.User;
import com.wedontanything.usedmarket.Utils;

import retrofit2.Call;
import retrofit2.Callback;

import static android.widget.Toast.LENGTH_LONG;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button signupButton;
    private Button passwordFindButton;

    private EditText id;
    private EditText pw;

    private EditText name;
    private ImageView loginImage;

    private CheckBox loginCheckBox;

    UserService service = Utils.RETROFIT.create(UserService.class);

    private TokenManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        manager = TokenManager.getInstance(this);

        loginButton = findViewById(R.id.loginButtonLogin);
        signupButton = findViewById(R.id.loginButtonSignUp);
        passwordFindButton = findViewById(R.id.passwordFind);
        loginImage = findViewById(R.id.loginImage);

        loginImage.setImageResource(R.drawable.name_logo_cute);
//            if (manager.getToken().getToken() != "") {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//            }


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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TEST", "로그인 끝");
    }

    private void login() {
        id = findViewById(R.id.loginEditId);
        pw = findViewById(R.id.loginEditPassword);

        name = findViewById(R.id.myPageEditUserName);

        if (id.getText().toString().equals("") || pw.getText().toString().equals("")) {
            Toast.makeText(this, "아이디나 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<Response<LoginData>> postLogin = service.postLogin(id.getText().toString(),
                pw.getText().toString());

        postLogin.enqueue(new Callback<Response<LoginData>>() {
            @Override
            public void onResponse(Call<Response<LoginData>> call, retrofit2.Response<Response<LoginData>> response) {
                // Todo: 에러 메시지 뛰우기
                // TODO : 회원가입 하고 새로 만든 아이디의 토큰이 반환되지 않은 경우 처리
                manager.setToken(response.body().getData().getToken().getToken());

//                name.setText(response.body().getData().getUser().getName());
                Log.d("성공", "onResponse: " + response.message() + response.body().getData().getToken().getToken() + " " + manager.getToken().getToken());

                if (response.code() == 200) {
                    Toast.makeText(LoginActivity.this, "로그인에 성공하였습니다.", LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else if(response.code() == 401) {
                    Toast.makeText(LoginActivity.this, "아이디나 비밀번호를 확인해 주세요.", LENGTH_LONG).show();
                    // TODO : 로그인 실패 request 값
                } else {
                    Toast.makeText(LoginActivity. this, "서버 에러", LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Response<LoginData>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "서버와 연결이 끊겼습니다.\n네트워크 연결을 확인해주세요.", LENGTH_LONG).show();
            }
        });
    }
}
