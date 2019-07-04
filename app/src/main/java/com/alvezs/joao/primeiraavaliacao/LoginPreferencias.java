package com.alvezs.joao.primeiraavaliacao;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginPreferencias {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private int tentativas = 0;

    private final String NOME_ARQUIVO = "login.preferencias";

    private final String CHAVE_USUARIO = "usuario";
    private final String CHAVE_SENHA = "senha";
    private final String CHAVE_TENTATIVAS = "tentativas";

    public LoginPreferencias(Context c) {
        this.context = c;
        preferences = context.getSharedPreferences(NOME_ARQUIVO, 0);
        editor = preferences.edit();
    }

    public void salvarCredenciais(String usuario, String senha) {
        editor.putString(CHAVE_USUARIO, usuario );
        editor.putString(CHAVE_SENHA, senha );
        editor.commit();
    }

    public int verificaTentativas(){
        tentativas = preferences.getInt(CHAVE_TENTATIVAS, 0);
        return tentativas;
    }

    public void adicionaTentativasFalhas(){
        tentativas = verificaTentativas();
        tentativas += 1;
        editor.putInt(CHAVE_TENTATIVAS, tentativas);
        editor.commit();
    }

    public void resetaTentativas(){
        tentativas = 0;
        editor.putInt(CHAVE_TENTATIVAS, tentativas);
        editor.commit();
    }

    public boolean validarUsuario(String usuario, String senha) {

        String usuarioSalvo = preferences.getString(CHAVE_USUARIO, "admin");
        String senhaSalva = preferences.getString(CHAVE_SENHA, "admin");

        return (usuario.equals(usuarioSalvo) && (senha.equals(senhaSalva)));


    }


    public String recuperarCredenciais() {
        return "";
    }

}
