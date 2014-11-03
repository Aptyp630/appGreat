package com.davidofffarchik.addnewshop.autorization;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.davidofffarchik.R;
import com.davidofffarchik.addnewshop.AddNewShop;
import com.davidofffarchik.models.RegistrationResponse;
import com.davidofffarchik.models.User;
import com.davidofffarchik.webclient.WebClient;
import com.davidofffarchik.webclient.WebClientListener;

public class Registration extends Activity implements View.OnClickListener{

    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_fragment_sign_in);

        email = (EditText) findViewById(R.id.email_edit);
        password = (EditText) findViewById(R.id.password_edit);
        confirmPassword = (EditText) findViewById(R.id.password_confirm);
        userName = (EditText) findViewById(R.id.user_name);

        Button registerBtn = (Button) findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerBtn :
                String getUserEmail = email.getText().toString();
                String getUserPassword = password.getText().toString();
                String getUserConfirmedPassword = confirmPassword.getText().toString();
                String getUserName = userName.getText().toString();
                User user = new User(getUserEmail, getUserPassword, getUserConfirmedPassword, getUserName);
                WebClientListener<RegistrationResponse> webClientListener = new WebClientListener<RegistrationResponse>() {
                    @Override
                    public void onResponseSuccess(RegistrationResponse result) {
                        if(result.getUser() != null) {
                            Intent intent = new Intent(getApplicationContext(), AddNewShop.class);
                            intent.putExtra("token", result.getUser().getToken());
                            startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onResponseError() {
                        Toast.makeText(getApplicationContext(), "Проверьте интернет соединение", Toast.LENGTH_SHORT).show();
                    }
                };
                WebClient.getInstance().callRegistration(user, webClientListener);
        }
    }
}
