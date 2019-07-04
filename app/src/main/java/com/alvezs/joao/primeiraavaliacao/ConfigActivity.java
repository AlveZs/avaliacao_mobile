package com.alvezs.joao.primeiraavaliacao;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConfigActivity extends AppCompatActivity {

    private LoginPreferencias preferencias;

    private EditText editNewUser;
    private EditText editNewPass;
    private EditText editConfPass;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        editNewUser = findViewById(R.id.editNewUsr);
        editNewPass = findViewById(R.id.editNewPass);
        editConfPass = findViewById(R.id.editConfNewPass);

        preferencias = new LoginPreferencias( getApplicationContext() );

        Button btnConfig = findViewById(R.id.btnConf);
        Button btnCancel = findViewById(R.id.btnCancel);

        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String novoUsuario = editNewUser.getText().toString();
                String novaSenha = editNewPass.getText().toString();
                String confSenha = editConfPass.getText().toString();

                if(novoUsuario.equals("") || novaSenha.equals("") || confSenha.equals("")){
                    Snackbar.make(v, getResources().getString(R.string.tst_campos_config), Snackbar.LENGTH_SHORT).show();
                } else {

                    if(!novaSenha.equals(confSenha)) {
                        Snackbar.make(v,getResources().getString(R.string.tst_erro_igual), Snackbar.LENGTH_SHORT).show();
                    } else {
                        preferencias.salvarCredenciais(novoUsuario,novaSenha);
                        //Snackbar.make(v,getResources().getString(R.string.tst_sucesso_cadastro), Snackbar.LENGTH_SHORT).show();
                        finish();
                    }

                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
