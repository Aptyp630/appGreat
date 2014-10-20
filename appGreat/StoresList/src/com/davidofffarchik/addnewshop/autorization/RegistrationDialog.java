package com.davidofffarchik.addnewshop.autorization;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.davidofffarchik.R;

public class RegistrationDialog extends DialogFragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_sign_in, null);
        getDialog().setTitle(R.string.registerForm);

        EditText email = (EditText) view.findViewById(R.id.email_edit);
        EditText password = (EditText) view.findViewById(R.id.password_edit);
        EditText confirmPassword = (EditText) view.findViewById(R.id.password_confirm);
        Button registerBtn = (Button) view.findViewById(R.id.registerBtn);
        Button cancelBtn = (Button) view.findViewById(R.id.cancelBtn);
        registerBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerBtn :
                Toast.makeText(getActivity(), "Registered", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancelBtn :
                this.dismiss();
                Log.v("dismiss", "dismissed");
                break;
        }
    }

    public void onDismiss(DialogInterface dialog){
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog){
        super.onCancel(dialog);
    }


}
