package com.davidofffarchik.addnewshop.autorization;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import com.davidofffarchik.R;
import com.davidofffarchik.constans.Constans;

public class SignInRegister extends FragmentActivity implements View.OnClickListener{

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
            case R.id.signInBtn :
            RegistrationDialog dialogFragment = new RegistrationDialog();
            dialogFragment.show(getSupportFragmentManager(), Constans.FRG_LOG);
                break;
            case R.id.loginBtn :
                AutorizationDialog autorizationDialog = new AutorizationDialog();
                autorizationDialog.show(getSupportFragmentManager(), Constans.FRG_LOG);
        }
    }
}
