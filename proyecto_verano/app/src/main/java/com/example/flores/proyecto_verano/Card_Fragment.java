package com.example.flores.proyecto_verano;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Card_Fragment extends Fragment {


    private RelativeLayout background;
    private Button btnPreviousFragment;
    private Button btnNextFragment;
    private Button btnNavMainActivity;
    private TextView txtNames;

    private TextView playerName;
    private TextView txtMessage;
    private ArrayList<Integer> colorsName = new ArrayList<>();
    private boolean hasNext;
    private boolean hasPrevious;
    private int FRAGMENT_NUMBER;
    private List<String> playerNames = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_layout, container, false);


        colorsName.add(ContextCompat.getColor(getContext(), R.color.c1));
        colorsName.add(ContextCompat.getColor(getContext(), R.color.c2));
        colorsName.add(ContextCompat.getColor(getContext(), R.color.c3));

        playerName = (TextView)view.findViewById(R.id.txtPlayer);
        txtMessage = (TextView)view.findViewById(R.id.txtMessage);


        String data = "";
        StringBuffer sbuffer = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.chistes);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        btnPreviousFragment = (Button) view.findViewById(R.id.btnPreviousFragment);
        background = (RelativeLayout) view.findViewById(R.id.constraintLayout);
        btnNextFragment = (Button) view.findViewById(R.id.btnNextFragment);
        btnNavMainActivity = (Button) view.findViewById(R.id.btnNavMainActivity);
        txtNames = (TextView) view.findViewById(R.id.txtPlayerNames);




        FRAGMENT_NUMBER = getArguments().getInt("number");
        hasNext = (FRAGMENT_NUMBER < ((Fragment_Container)getActivity()).getNumFragment()-1);
        hasPrevious = (FRAGMENT_NUMBER > 0);
        background.setBackgroundColor(colorsName.get(FRAGMENT_NUMBER%3));

//        btnPreviousFragment.setEnabled(hasPrevious);
//        btnNextFragment.setEnabled(hasNext);

        btnNavMainActivity.setVisibility(View.INVISIBLE);
        btnNavMainActivity.setEnabled(false);
        btnPreviousFragment.setVisibility(View.INVISIBLE);
        btnPreviousFragment.setEnabled(false);
        btnNextFragment.setVisibility(View.INVISIBLE);
        btnNextFragment.setEnabled(false);

        txtMessage.setTextSize(18);
        playerName.setTextSize(20);

        playerNames = getArguments().getStringArrayList("player names"); // Intent but because it is a Fragement we use BUNDLE instead
        playerName.setText(playerNames.get(generateRandomNumber(playerNames.size()-1)));
        try{
            InputStream in = getResources().openRawResource(R.raw.chistes);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(in,null);
            NodeList fileChistes = doc.getElementsByTagName("chiste");
            txtMessage.setText(((Element)fileChistes.item(generateRandomNumber(fileChistes.getLength()-1))).getTextContent());
            in.close();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        txtNames.setText(Integer.toString(FRAGMENT_NUMBER) + " : \n");
        for (int i =0; i < playerNames.size();i++){
            SpannableString spanString = new SpannableString(playerNames.get(i));
            spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
            spanString.setSpan(new StyleSpan(Typeface.ITALIC), 0, spanString.length(), 0);
            txtNames.append(spanString + "\n");
        }

        btnNavMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going to Main Activity", Toast.LENGTH_SHORT).show();
                //navigate to Main Activity
                Intent intent = new Intent(getActivity(),Initial_Screen.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        btnNextFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Fragment_Container)getActivity()).setViewPager(FRAGMENT_NUMBER+1);
            }
        });
        btnPreviousFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Fragment_Container)getActivity()).setViewPager(FRAGMENT_NUMBER-1);
            }
        });
        return view;
    }

    private int generateRandomNumber(int max){

        Random random = new Random();
        int number = random.nextInt(max)+1;

        return number;
    }
}
