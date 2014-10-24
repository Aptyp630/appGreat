package com.davidofffarchik.dialogfragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.davidofffarchik.R;
import com.davidofffarchik.addnewshop.AddNewShop;
import com.davidofffarchik.query.QueryToEnter;

public class RegistrationDialog extends DialogFragment implements View.OnClickListener{

    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private EditText userName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_sign_in, null);
        getDialog().setTitle(R.string.registerForm);

        email = (EditText) view.findViewById(R.id.email_edit);
        password = (EditText) view.findViewById(R.id.password_edit);
        confirmPassword = (EditText) view.findViewById(R.id.password_confirm);
        userName = (EditText) view.findViewById(R.id.user_name);

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
                String getEmail = email.getText().toString();
                String getPassword = password.getText().toString();
                String getConfirmedPassword = confirmPassword.getText().toString();
                String getUserName = userName.getText().toString();
                QueryToEnter.OnCreateProductFromRegistration listener = new QueryToEnter.OnCreateProductFromRegistration() {
                    @Override
                    public void createNewShopRegistration() {
                        Intent intent = new Intent(getActivity(), AddNewShop.class);
                        startActivity(intent);
                    }
                    @Override
                    public void errorInternetConnectionRegistration() {
                        Toast.makeText(getActivity(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                };
                QueryToEnter queryToEnter = new QueryToEnter();
                queryToEnter.queryRegistration(this.getActivity(), getEmail, getPassword, getConfirmedPassword, getUserName, listener);
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
