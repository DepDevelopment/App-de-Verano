package com.example.flores.proyecto_verano;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/* Clase que representa cada una de las TARJETAS por separado.
*
*
*
*
* */
public class Fragment_Container extends AppCompatActivity{

    private SectionStatePagerAdapter mSectionStatePagerAdapter;
    private ViewPager mViewPager;
    private ArrayList<String> playerNames = new ArrayList<>();

    private final static int NUM_FRAGMENT = 50;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_layout);

        mSectionStatePagerAdapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.container_1);
        //Setup the pager

        Intent activityThatCalled = getIntent(); // Get the Intent that called for this Activitty to open
        playerNames = activityThatCalled.getExtras().getStringArrayList("players");

        setupViewPager(mViewPager);



    }
    private void setupViewPager(ViewPager viewPager){

        SectionStatePagerAdapter adapter = new SectionStatePagerAdapter(getSupportFragmentManager());

        for (int i = 0; i < NUM_FRAGMENT ; i++){
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("player names", playerNames);
            bundle.putInt("number",i);

            Card_Fragment fragment = new Card_Fragment();
            fragment.setArguments(bundle); // Pass the information of the names

            adapter.addFragment(fragment,"Fragment" + Integer.toString(i));
        }
        viewPager.setAdapter(adapter);
    }
    public void setViewPager (int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }
    public int getNumFragment(){
        return NUM_FRAGMENT;
    }
}
