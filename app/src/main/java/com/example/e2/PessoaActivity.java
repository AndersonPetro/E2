package com.example.e2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

public class PessoaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);

    }

    public void salvarPessoa(View view) {
        EditText nomePessoa = findViewById(R.id.editTextNomePessoa);
        EditText cidadePessoa = findViewById(R.id.editTextCidadePessoa);
        try {
            String endPoint = "http://192.168.0.105:9090/salva_pessoa?nome=" + nomePessoa.getText().toString() + "&cidade" + cidadePessoa.getText().toString();
            Log.i("E2", "EndPoint salva_Pessoa:" + endPoint);
            new ClientHttp().executeOnExecutor(Executors.newSingleThreadExecutor(),
                    new String[]{endPoint});


            nomePessoa.setText("");
            cidadePessoa.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public void listaPessoa(View view){
            String json = null;
            try {
                json = new ClientHttp().executeOnExecutor(Executors.newSingleThreadExecutor(),
                        new String[]{"http://192.168.0.105:9090/lista_pessoas"}).get();
                Pessoa[] listapessoas = new Gson().fromJson(json, Pessoa[].class);
                List<Pessoa> arrayListpessoa = Arrays.asList(listapessoas);
                EditText editText = findViewById(R.id.editTextListaPessoas);

                editText.setText("");
                for (int i = 0; i < listapessoas.length; i++) {
                    Pessoa pessoa = listapessoas[i];
                    editText.append(pessoa.getId() + " - " + pessoa.getNomePessoa() + " - " + pessoa.getNomeCidade() );
                    editText.append("\n");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }



