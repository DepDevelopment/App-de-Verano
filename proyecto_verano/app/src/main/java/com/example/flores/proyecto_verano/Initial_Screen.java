package com.example.flores.proyecto_verano;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Initial_Screen extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);




        btn1 = (Button) findViewById(R.id.btnFacil);
        btn2 = (Button) findViewById(R.id.btnMedio);
        btn3 = (Button) findViewById(R.id.btnDificil);
        btn4 = (Button) findViewById(R.id.btnHardcore);

        final Intent intent = new Intent(this, MainActivity.class);
        final Intent intent2 = new Intent(this,MainActivityDos.class);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("name", btn1.getText());
                startActivity(intent);
               // finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("name", btn2.getText());
                startActivity(intent);
                //finish();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent2.putExtra("name", btn3.getText());
                startActivity(intent2);
                //finish();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent2.putExtra("name", btn4.getText());
                startActivity(intent2);
               // finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu); //.xml file name
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_USE_LOGO);
        getSupportActionBar().setIcon(R.mipmap.ic_hot);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_about_us:
                showAlertAboutUs();
                return true;
            case R.id.action_exit:
                Toast toast =  Toast.makeText(this, "Gracias por jugar a HotterThanHot!", Toast.LENGTH_LONG);
                toast.show();
                if (toast.getView().isShown()) {
                    finish();
                }
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    protected void showAlertAboutUs(){
        String aboutUs = "Hola! \n\tSomos Jose y Hector, actualmente estamos en primero de Ingenieria" +
                " informatica pero nos apasiona poder crear entretenimientos. \n\nPara contactar con " +
                "nosotros utilitza este correo correo@gmail.com \n\n\rGracias";
        new AlertDialog.Builder(this).setMessage(aboutUs).setPositiveButton("Aceptar",null).show();
    }
}
