package com.wedontanything.usedmarket.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wedontanything.usedmarket.Interface.UserService;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Response.Response;
import com.wedontanything.usedmarket.Utils;

import retrofit2.Call;
import retrofit2.Callback;

public class PasswordFindActivity extends AppCompatActivity {

    private EditText id;
    private EditText new_password;

    private Button passwordChangeButton;

    UserService service = Utils.RETROFIT.create(UserService.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passwordfind_activity);

        id = findViewById(R.id.findPasswordId);
        new_password = findViewById(R.id.newPassword);
        passwordChangeButton = findViewById(R.id.findPasswordCommit);

        passwordChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    private void changePassword() {
        Call<Response> postFindPassword = service.postFindPassword(id.getText().toString(), new_password.getText().toString());

        postFindPassword.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.code() == 200) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 변경되었습니다.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PasswordFindActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else if(response.code() == 403) {
                    Toast.makeText(getApplicationContext(), "아이디가 올바르지 않습니다.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("실패", t.toString());
            }
        });
    }
}
