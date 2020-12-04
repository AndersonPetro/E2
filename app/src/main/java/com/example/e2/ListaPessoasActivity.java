package com.example.e2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListaPessoasActivity extends AppCompatActivity {

    private List<String> listaPessoa = new ArrayList<String>();
    private int ACTIVITY_CADASTRA_ListaPessoas = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);////
        setContentView(R.layout.activity_pessoa);

        listaPessoa.add("william");
        listaPessoa.add("leda");
        listaPessoa.add("anderson");

        Spinner spinnerListaPessoas =  findViewById(R.id.spinnerListaPessoas);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item,
                        listaPessoa);

        spinnerListaPessoas.setAdapter(adapter);

    }

    public void cadastarNome(View view) {
        // aqui..vou abirr a activie filha, que vai cadastrar o novo nome.
        Intent intent = new Intent(this,ListaPessoasActivity.class);
        // abrindo uma active, que VAI RETORNAR UM VALOR
        startActivityForResult(intent,ACTIVITY_CADASTRA_ListaPessoas);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       super.onActivityResult(requestCode, resultCode, data);

       if(requestCode == ACTIVITY_CADASTRA_ListaPessoas){
            if(resultCode == 2){ // filha esta retornando uma String
                String novoNome = data.getExtras().getString("novo_nome");
                listaPessoa.add(novoNome);

                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(this,
                                android.R.layout.simple_spinner_item,
                                listaPessoa);
                Spinner spinnerNome =  findViewById(R.id.spinnerListaPessoas);
                spinnerNome.setAdapter(adapter);

            }
       }

    }
}