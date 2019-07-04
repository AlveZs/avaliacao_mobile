package com.alvezs.joao.primeiraavaliacao;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.design.widget.Snackbar;

public class MainActivity extends AppCompatActivity {

    private LoginPreferencias preferencias;

    private EditText editUser;
    private EditText editPass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencias = new LoginPreferencias( getApplicationContext() );

        verMensagemBloqueio();


        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        editUser = findViewById(R.id.editUser);
        editPass = findViewById(R.id.editPass);



        Button btnEnter = findViewById(R.id.btnEnter);
        Button btnCancel = findViewById(R.id.btnCancel);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String usuarioDigitado = editUser.getText().toString();
                String senhaDigitada = editPass.getText().toString();
                if( usuarioDigitado.equals("") && senhaDigitada.equals("") ){
                    Snackbar.make(v, getResources().getString(R.string.tst_erro_login), Snackbar.LENGTH_LONG).show();
                    //Toast.makeText(MainActivity.this, getResources().getString(R.string.tst_erro_login), Toast.LENGTH_SHORT).show();
                } else {

                    if( preferencias.validarUsuario(usuarioDigitado, senhaDigitada) ) {

                        Intent intent = new Intent( getApplicationContext() , PrincipalActivity.class );

                        //finishAffinity();
                        preferencias.resetaTentativas();
                        startActivity(intent);

                    } else {

                        Snackbar.make(v, getResources().getString(R.string.login_erro), Snackbar.LENGTH_LONG).show();
                        //Toast.makeText(MainActivity.this, getResources().getString(R.string.login_erro), Toast.LENGTH_SHORT).show();
                        preferencias.adicionaTentativasFalhas();
                        verMensagemBloqueio();
                        editPass.setText("");

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

    public void verMensagemBloqueio() {

        if (preferencias.verificaTentativas() >= 3 ) {

            AlertDialog.Builder bloqDialog = new AlertDialog.Builder(this);

            bloqDialog.setTitle(getResources().getString(R.string.bloqueio_app));
            bloqDialog.setMessage(getResources().getString(R.string.bloq_msg ));

            bloqDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //finish();
                }
            });

            bloqDialog.create();
            bloqDialog.show();

        }

    }


}
