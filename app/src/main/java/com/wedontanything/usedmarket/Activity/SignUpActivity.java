package com.wedontanything.usedmarket.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wedontanything.usedmarket.Fragment.SignUp;
import com.wedontanything.usedmarket.Interface.UserService;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.User.User;
import com.wedontanything.usedmarket.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    UserService service = Utils.RETROFIT.create(UserService.class);

    EditText inputId;
    EditText inputName;
    EditText inputPassword;
    EditText inputemail;
    EditText inputPhoneNumber;
    EditText inputSchoolName;

    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up);

        signupButton = findViewById(R.id.signUpButtonCommit);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
                
            }
        });
    }

    private void signup() {
        inputId = findViewById(R.id.signUpEditId);
        inputName = findViewById(R.id.signUpEditName);
        inputPassword = findViewById(R.id.signUpEditPassword);
        inputemail = findViewById(R.id.signUpEditEmail);
        inputPhoneNumber = findViewById(R.id.signUpEditPhoneNumber);
        inputSchoolName = findViewById(R.id.signUpEditSchool);

        List<String> inputList = new ArrayList<>();
        inputList.add(inputId.getText().toString());
        inputList.add(inputName.getText().toString());
        inputList.add(inputPassword.getText().toString());
        inputList.add(inputemail.getText().toString());
        inputList.add(inputPhoneNumber.getText().toString());
        inputList.add(inputSchoolName.getText().toString());


        Call<User> postSignup = service.postSignup(inputId.getText().toString(), inputName.getText().toString(),
                inputPassword.getText().toString(), inputemail.getText().toString(), inputPhoneNumber.getText().toString(),
                inputSchoolName.getText().toString());

        postSignup.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                //TODO: 예외처리

                for (int i = 0; i < inputList.size(); i++) {
                    if (inputList.get(i).equals("")) {
                        Toast.makeText(SignUpActivity.this, "비어있는 항목이 있습니다. 빈 값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                Log.d("성공", "onResponse: " + response.message());
                Toast.makeText(SignUpActivity.this, "회원 가입이 되었습니다. ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                if(user != null) {
                    Log.d("null", "onResponse: null");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("실패", "onFailure: " + t.toString());
                Toast.makeText(SignUpActivity.this, "서버와 연결이 끊겼습니다.\n네트워크 연결을 확인해주세요. ", Toast.LENGTH_LONG).show();
            }
        });
    }
}
