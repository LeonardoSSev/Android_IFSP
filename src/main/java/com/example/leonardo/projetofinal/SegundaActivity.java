package com.example.leonardo.projetofinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

public class SegundaActivity extends AppCompatActivity {

    private DBHelper db;
    EditText etNome, etEmail, etIdade, etTelefone, etCpf;
    Button btCadastrar, btListar, btVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        etNome = (EditText) findViewById(R.id.etNome);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etCpf = (EditText) findViewById(R.id.etCpf);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etIdade = (EditText) findViewById(R.id.etIdade);


        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        btListar = (Button) findViewById(R.id.btListar);
        btVoltar = (Button) findViewById(R.id.btVoltar);


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNome.getText().length() > 0 && etEmail.getText().length() > 0 && etCpf.getText().length() > 0 && etIdade.getText().length() > 0 && etTelefone.getText().length() > 0) {
                    db.insert(etNome.getText().toString(), etEmail.getText().toString(), etCpf.getText().toString(), etIdade.getText().toString(), etTelefone.getText().toString());
                    AlertDialog.Builder adb = new AlertDialog.Builder(SegundaActivity.this);
                    adb.setTitle("Sucesso!");
                    adb.setMessage("Cadastro realizado!");
                    adb.show();

                    etNome.setText("");
                    etEmail.setText("");
                    etCpf.setText("");
                    etIdade.setText("");
                    etTelefone.setText("");

                } else{
                    AlertDialog.Builder adb = new AlertDialog.Builder(SegundaActivity.this);
                    adb.setTitle("Mensagem");
                    adb.setMessage("Você precisa preencher todos os campos para realizar o cadastro");
                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                }
            }
        });

        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Contato> contatos = db.queryGetAll();
                if (contatos == null) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(SegundaActivity.this);
                    adb.setTitle("Mensagem");
                    adb.setMessage("Não há registros");
                    adb.show();

                    etNome.setText("");
                    etEmail.setText("");
                    etCpf.setText("");
                    etIdade.setText("");
                    etTelefone.setText("");

                    return;
                } else {

                    for (int i = 0; i < contatos.size(); i++) {
                        Contato contato = (Contato) contatos.get(i);
                        AlertDialog.Builder adb = new AlertDialog.Builder(SegundaActivity.this);
                        adb.setTitle("Registro " + i);
                        adb.setMessage("Nome: " + contato.getNome() + "\nE-mail: " + contato.getEmail() + "\nCPF: " + contato.getCpf() + "\nIdade: " + contato.getIdade() + "\nTelefone: " + contato.getTelefone());
                        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        adb.show();
                    }
                }
            }
        });


        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamarPrimeiraActivity();
            }
        });

    }

    void chamarPrimeiraActivity() {
        Intent intent = new Intent();
        intent.setClass(SegundaActivity.this, PrimeiraActivity.class);
        startActivity(intent);
        finish();
    }


}
