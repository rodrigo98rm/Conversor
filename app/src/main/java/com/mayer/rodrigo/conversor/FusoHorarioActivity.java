package com.mayer.rodrigo.conversor;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FusoHorarioActivity extends AppCompatActivity {

    private EditText horaAtualEditText;
    private ListView listView;
    private FloatingActionButton refreshFab;
    private Date horaAtual;
    private int NEW_YORK = -1, LONDON = 4, CAIRO = 5, HONG_KONG = 11, TOKYO = 12; //Fusos horarios
    private int[] fusos = {NEW_YORK, LONDON, CAIRO, HONG_KONG, TOKYO};
    private String[] fusosCidades = {"New York", "London", "Cairo", "Hong Kong", "Tokyo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuso_horario);

        getSupportActionBar().setTitle("Fuso Horário");

        horaAtualEditText = (EditText) findViewById(R.id.hora_atual);
        listView = (ListView) findViewById(R.id.horarios_list_view);
        refreshFab = (FloatingActionButton) findViewById(R.id.refresh_fab);

        refreshFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizar();
            }
        });

        atualizar();
    }

    private void atualizar(){
        ArrayAdapter<String> horariosAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, obterHorarios());
        listView.setAdapter(horariosAdapter);
    }

    private ArrayList<String> obterHorarios(){
        DateFormat dateFormat = new SimpleDateFormat("h:mm a");
        ArrayList<String> horas = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        horaAtual = calendar.getTime();
        horaAtualEditText.setText(dateFormat.format(horaAtual));

        for (int i = 0; i < fusos.length; i++ ){
            calendar.setTime(new Date());
            calendar.add(Calendar.HOUR_OF_DAY, fusos[i]);
            horaAtual = calendar.getTime();
            String date = fusosCidades[i] + ": " + dateFormat.format(horaAtual);
            horas.add(date);
        }

        return horas;
    }
}
