package com.mayer.rodrigo.conversor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MoedaActivity extends AppCompatActivity {

    private EditText quantidade;
    private Spinner spinner1;
    private Button calcularButton;
    private TextView brl, usd, eur;

    //Taxas de câmbio
    private double BRL_TO_USD = 0.30569, BRL_TO_EUR = 0.27252;
    private double USD_TO_BRL = 3.2819, USD_TO_EUR = 0.89055;
    private double EUR_TO_BRL = 3.6852, EUR_TO_USD = 1.1229;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moeda);

        getSupportActionBar().setTitle("Moeda");

        quantidade = (EditText) findViewById(R.id.moeda1);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        calcularButton = (Button) findViewById(R.id.calcular_button);
        brl = (TextView) findViewById(R.id.brl_textview);
        usd = (TextView) findViewById(R.id.usd_textview);
        eur = (TextView) findViewById(R.id.eur_textview);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.moedas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarValores();
            }
        });

        quantidade.setText(String.valueOf(1));
        atualizarValores();
    }

    private void atualizarValores(){
        String moedaSelecionada = spinner1.getSelectedItem().toString();
        String quantidateString = quantidade.getText().toString().trim();
        if(TextUtils.isEmpty(quantidateString)){
            Toast.makeText(getApplicationContext(), "Insira um valor", Toast.LENGTH_SHORT).show();
            return;
        }
        double valor = Double.parseDouble(quantidateString);
        double valorBRL, valorUSD, valorEUR;

        switch(moedaSelecionada){
            case "BRL":
                brl.setText(String.valueOf(valor) + " BRL");
                valorUSD = valor * BRL_TO_USD;
                valorEUR = valor * BRL_TO_EUR;

                usd.setText(arredondar(valorUSD) + " USD");
                eur.setText(arredondar(valorEUR) + " EUR");
                break;
            case "USD":
                usd.setText(String.valueOf(valor) + " USD");
                valorBRL = valor * USD_TO_BRL;
                valorEUR = valor * USD_TO_EUR;

                brl.setText(arredondar(valorBRL) + " BRL");
                eur.setText(arredondar(valorEUR) + " EUR");
                break;
            case "EUR":
                eur.setText(String.valueOf(valor) + " EUR");
                valorBRL = valor * EUR_TO_BRL;
                valorUSD = valor * EUR_TO_USD;

                brl.setText(arredondar(valorBRL) + " BRL");
                usd.setText(arredondar(valorUSD) + " USD");
                break;
        }
    }

    private String arredondar(Double valor){
        return String.valueOf(Math.round(valor * 100d)/100d);
    }
}