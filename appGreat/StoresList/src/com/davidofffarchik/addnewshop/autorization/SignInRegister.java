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
        signIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AutorizationDialigFragment dialogFragment = new AutorizationDialigFragment();
        dialogFragment.show(getSupportFragmentManager(), Constans.FRG_LOG);
    }
}
