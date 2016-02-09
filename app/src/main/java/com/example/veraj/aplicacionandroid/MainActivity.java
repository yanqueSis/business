package com.example.veraj.aplicacionandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     /*   RelativeLayout mainLayout = new RelativeLayout(this);
        TextView texto = new TextView(this);
        texto.setText("Hello World!");
        mainLayout.addView(texto);
        setContentView(mainLayout);*/


    }

    public void mostrarMensajeLogin(View v){
        final TextView texto = (TextView) findViewById(R.id.editText);
        Toast mensaje = Toast.makeText(getApplicationContext(),"Cargando Perfil de "+texto.getText(), Toast.LENGTH_SHORT);
        mensaje.show();

        Intent myIntent = new Intent(MainActivity.this, ContentActivity.class);
        myIntent.putExtra("key", texto.getText().toString()); //Optional parameters
        MainActivity.this.startActivity(myIntent);
    }
}
