package com.wedontanything.usedmarket.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wedontanything.usedmarket.Interface.UserService;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.User.User;
import com.wedontanything.usedmarket.Utils;

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



        Call<User> postSignup = service.postSignup(inputId.getText().toString(), inputName.getText().toString(),
                inputPassword.getText().toString(), inputemail.getText().toString(), inputPhoneNumber.getText().toString(),
                inputSchoolName.getText().toString());




        postSignup.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();

                Log.d("성공", "onResponse: " + response.message());
                if(user != null) {
                    Log.d("null", "onResponse: null");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("실패", "onFailure: " + t.toString());
            }
        });
    }
}
