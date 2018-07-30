package com.example.flores.proyecto_verano;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivityDos extends AppCompatActivity {

    private TextView txtTitulo;
    private Button btnStart;
    private Button btnFillme;

    private ImageButton btnDelete;
    private ImageButton btnAddPlayer;
    private TableLayout llJugadores;
    private ArrayList<String> playerNames = new ArrayList<>();
    private String[] nombresRelleno = {"Jose","Hector","Spectro","Aspect","Sunex","Tarko","ItharSubnormal"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dos);

        txtTitulo = (TextView)findViewById(R.id.txtTitulo);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnDelete = (ImageButton)findViewById(R.id.btnDelete);
        btnFillme = (Button)findViewById(R.id.btnFill);

        btnAddPlayer = (ImageButton) findViewById(R.id.btnAddPlayer);
        llJugadores = (TableLayout)findViewById(R.id.llJugadores);
        llJugadores.setColumnStretchable(0,true);
        llJugadores.setColumnStretchable(1,true);
        llJugadores.setShrinkAllColumns(true);


        Intent intent = getIntent();
        txtTitulo.setText(intent.getStringExtra("name"));

        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TableRow rowN = new TableRow(view.getContext()) ;
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                rowN.setLayoutParams(layoutParams);
                rowN.setGravity(Gravity.CENTER_HORIZONTAL);

                TextView textView = new TextView(view.getContext());
                textView.setText("Jugador " + Integer.toString(llJugadores.getChildCount()+1));
                textView.setTextSize(20);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                rowN.addView(textView,0);

                EditText playerName = new EditText(view.getContext());
                playerName.setTextSize(20);

                playerName.setWidth(300);
                playerName.setGravity(Gravity.CENTER_HORIZONTAL);
                rowN.addView(playerName,1);

                TextView invi = new TextView(view.getContext());
                invi.setText("aaaa");
                invi.setTextSize(20);
                invi.setGravity(Gravity.CENTER_HORIZONTAL);
                invi.setVisibility(TextView.INVISIBLE);
                rowN.addView(invi,2);
                llJugadores.addView(rowN);

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llJugadores.getChildCount() > 0 ){
                    llJugadores.removeViewAt(llJugadores.getChildCount() - 1);
                }

            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Add all player names into a variable
                for (int i = 0 ; i < llJugadores.getChildCount();i++){
                    TableRow aux = (TableRow)llJugadores.getChildAt(i);
                    EditText etNames = (EditText)aux.getChildAt(1);
                    String name = etNames.getText().toString();
                    playerNames.add(name);
                }

                Intent intent = new Intent(view.getContext(),Fragment_Container.class);
                intent.putExtra("players",playerNames);
                startActivity(intent);
                finish();
            }
        });
        btnFillme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0 ; i < llJugadores.getChildCount();i++) {
                    TableRow aux = (TableRow) llJugadores.getChildAt(i);
                    EditText etNames = (EditText) aux.getChildAt(1);
                    etNames.setText(nombresRelleno[(i%7)]);
                }
            }
        });

    }
}