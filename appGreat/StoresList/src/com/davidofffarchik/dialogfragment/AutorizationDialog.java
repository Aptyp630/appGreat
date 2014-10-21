package com.davidofffarchik.dialogfragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.davidofffarchik.R;

public class
        AutorizationDialog extends DialogFragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_login, null);
        getDialog().setTitle(R.string.loginBtn);
        EditText loginEmail = (EditText) view.findViewById(R.id.email_login);
        EditText loginPassword = (EditText) view.findViewById(R.id.password_login);
        Button entering = (Button) view.findViewById(R.id.enteringBtn);
        Button cancelBtn = (Button) view.findViewById(R.id.cancelBtn);
        entering.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.enteringBtn :
                Toast.makeText(getActivity(), "Login in", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancelBtn : dismiss();
                break;
        }
    }
}
