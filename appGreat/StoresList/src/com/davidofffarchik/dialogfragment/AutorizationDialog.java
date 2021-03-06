package com.davidofffarchik.dialogfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.davidofffarchik.R;
import com.davidofffarchik.addnewshop.AddNewShop;
import com.davidofffarchik.models.RegistrationResponse;
import com.davidofffarchik.models.User;
import com.davidofffarchik.webclient.WebClient;
import com.davidofffarchik.webclient.WebClientListener;

public class AutorizationDialog extends DialogFragment implements View.OnClickListener{

    private  EditText loginEmail;
    private  EditText loginPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_login, null);
        getDialog().setTitle(R.string.loginBtn);
        loginEmail = (EditText) view.findViewById(R.id.email_login);
        loginPassword = (EditText) view.findViewById(R.id.password_login);
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
                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();
                User user = new User(email, password);

                WebClientListener<RegistrationResponse> webClientListener = new WebClientListener<RegistrationResponse>() {
                    @Override
                    public void onResponseSuccess(RegistrationResponse result) {
                            Intent intent = new Intent(getActivity(), AddNewShop.class);
                            intent.putExtra("token", result.getUser().getToken());
                            startActivity(intent);
                    }
                    @Override
                    public void onResponseError() {
                        Toast.makeText(getActivity(), "Проверьте корректность ввода данных и наличие Интернета!", Toast.LENGTH_SHORT).show();
                    }
                };
                WebClient.getInstance().callLogin(user, webClientListener);
/*
                QueryToEnter.OnCreateProductFromLogin listener = new QueryToEnter.OnCreateProductFromLogin() {

                    @Override
                    public void createNewShopLogin(String token) {
                        Intent intent = new Intent(getActivity(), AddNewShop.class);
                        intent.putExtra("token", token);
                        startActivity(intent);
                    }
                    @Override
                    public void responseError() {
                            Toast.makeText(getActivity(), "Нет такого E-mail в базе", Toast.LENGTH_SHORT).show();
                    }
                };
                QueryToEnter queryToEnter = new QueryToEnter();
                queryToEnter.queryLogin(this.getActivity(), email, password, listener);
                */
                break;
            case R.id.cancelBtn : dismiss();
                break;
        }
    }
}
