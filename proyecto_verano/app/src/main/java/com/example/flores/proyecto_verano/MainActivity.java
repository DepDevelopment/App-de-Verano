package com.example.flores.proyecto_verano;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //Atributos de clase
    private final static int PLAYERS_MAX = 6;
    // Componentes de activity_main.xml

    //No cambia
    private TextView txtTitulo;
    private TextView txtJugadores;
    private Button btnStart;
    private Button btnFillme;
    private Spinner svNumPlayers;
    private TableLayout tlPlayers;
    private ArrayList<String> playerNames = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();

        txtTitulo = (TextView)findViewById(R.id.txtTitulo);
        txtTitulo.setText(intent.getStringExtra("name"));

        txtJugadores = (TextView)findViewById(R.id.txtNumPlayers);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnFillme = (Button)findViewById(R.id.btnFill);
        svNumPlayers = (Spinner)findViewById(R.id.svNumPlayers);
        tlPlayers = (TableLayout) findViewById(R.id.tlPlayers);

        SpannableString spanString = new SpannableString(getResources().getString(R.string.numPlayers));
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
        spanString.setSpan(new StyleSpan(Typeface.ITALIC), 0, spanString.length(), 0);


        txtJugadores.setText(spanString);
        txtJugadores.setTextSize(20);
        txtJugadores.setTextColor(getColor(R.color.colorText));


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        for (int i = 2; i <= PLAYERS_MAX;i++){
            categories.add("" + i + " jugadores");
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        svNumPlayers.setAdapter(dataAdapter);

        svNumPlayers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int pos = i+2;
                Context context = view.getContext();
                tlPlayers.removeAllViews();
                for(int j= 0; j< pos; j++){
                    TableRow rowN = new TableRow(context) ;
                    TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    rowN.setLayoutParams(layoutParams);
                    // Add a TextView in the first column.
                    TextView textView = new TextView(context);
                    textView.setTextSize(20);
                    String tempString="Jugador numero " + (j+1) + ":      ";
                    SpannableString spanString = new SpannableString(tempString);
                 //   spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);
                    spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
                    spanString.setSpan(new StyleSpan(Typeface.ITALIC), 0, spanString.length(), 0);
                    textView.setText(spanString);
                    rowN.addView(textView, 0);

                    // Add a button in the second column
                    EditText eNombreJugador = new EditText(context);
                    eNombreJugador.setWidth(400);
                    eNombreJugador.setText("");
                    eNombreJugador.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    rowN.addView(eNombreJugador, 1);

                    tlPlayers.addView(rowN);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // We have to state that are intention is to open another Activity. We do so
                // by passing a Context and the Activity that we want to open
                Intent getNameScreenIntent = new Intent(view.getContext(), Fragment_Container.class);

                // Data stored in playerNames array list
                for (int i = 0 ;i <tlPlayers.getChildCount(); i++){
                    TableRow h = (TableRow)tlPlayers.getChildAt(i);
                    EditText name = (EditText)h.getChildAt(1);
                    String nm = name.getText().toString();
                    playerNames.add(nm);
                }
                // To send data use putExtra with a String name followed by its value
                getNameScreenIntent.putExtra("players",playerNames);
                //We start the activity
                startActivity(getNameScreenIntent);
                finish();


            }
        });

        btnFillme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] namesAux = {"Hector","Spectro","Bedep","Maelyon","Tarko","Zhalorack"};
                for (int i = 0; i < tlPlayers.getChildCount();i++){
                    TableRow h = (TableRow)tlPlayers.getChildAt(i);
                    EditText name = (EditText)h.getChildAt(1);
                    name.setText(namesAux[i]);
                }
            }
        });



    }
}
