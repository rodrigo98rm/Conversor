package com.mayer.rodrigo.conversor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button moedaButton, fusoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moedaButton = (Button) findViewById(R.id.moeda_button);
        fusoButton = (Button) findViewById(R.id.fuso_button);

        moedaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MoedaActivity.class);
                startActivity(intent);
            }
        });
        fusoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FusoHorarioActivity.class);
                startActivity(intent);
            }
        });
    }
}
