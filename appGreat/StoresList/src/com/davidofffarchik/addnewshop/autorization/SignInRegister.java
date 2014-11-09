package com.davidofffarchik.addnewshop.autorization;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.davidofffarchik.R;
import com.davidofffarchik.UserToken;
import com.davidofffarchik.main.Main;
import com.davidofffarchik.models.RegistrationResponse;
import com.davidofffarchik.models.User;
import com.davidofffarchik.webclient.WebClient;
import com.davidofffarchik.webclient.WebClientListener;

public class SignInRegister extends FragmentActivity implements View.OnClickListener{

    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_registration);

        Button signIn = (Button) findViewById(R.id.signInBtn);
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        signIn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn :
                email = (EditText) findViewById(R.id.email);
                password = (EditText) findViewById(R.id.password);
                String getEmail = email.getText().toString();
                String getPassword = password.getText().toString();
                User user = new User(getEmail, getPassword);
                WebClientListener<RegistrationResponse> webClientListener = new WebClientListener<RegistrationResponse>() {
                    @Override
                    public void onResponseSuccess(RegistrationResponse result) {
                            UserToken.getInstance().saveToken(result.getUser().getToken());
                            Intent intent = new Intent(getApplicationContext(), Main.class);
                            intent.putExtra("token", result.getUser().getToken());
                            startActivity(intent);
                    }
                    @Override
                    public void onResponseError() {
                        Toast.makeText(getApplicationContext(), "Проверьте корректность ввода данных и наличие Интернета!", Toast.LENGTH_SHORT).show();
                    }
                };
                WebClient.getInstance().callLogin(user, webClientListener);
                break;

            case R.id.signInBtn :
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
                break;
        }
    }
}