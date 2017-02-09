package com.cse280.adriansandoval.converit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Volume
    Volume volume = new Volume();

    String[] volumeUnits = new String[]{
            "Cubic Feet",
            "Cubic Meter",
            "Liters",
            "Gallons",
            "MilliLiter"
    };

    // A String array that holds all currencies
      String [] currencyTypes={"Us Dollar",
            "Euro",
            "GB Pound",
            "Yen",
            "Canadian Dollar"
    };

    //ImageView
    ImageView VolumeImageView;
    ImageView showInImageView;
    ImageView showOutImageView;
    ImageView convert;



    //List View
    ListView inListView;
    ListView outListView;


    // Unit
    String inUnit;
    String outUnit;

    //editText
    EditText input;

    //TextView
    TextView output;

    //conversion variables
    public double inValue;
    public String outValue;

    //option check
    boolean check = false;
    boolean isCurrency=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        check = false;
        isCurrency=false;
        //register contents
        VolumeImageView = (ImageView) findViewById(R.id.volumeImage);
        showInImageView= (ImageView) findViewById(R.id.showInListView);
        showOutImageView= (ImageView) findViewById(R.id.showOutListView);
        convert = (ImageView) findViewById(R.id.convert);

        inListView = (ListView) findViewById(R.id.inListView);
        outListView = (ListView) findViewById(R.id.outListView);

        input = (EditText) findViewById(R.id.input);

        output = (TextView) findViewById(R.id.outPut);


        inListView.setVisibility(View.INVISIBLE);
        outListView.setVisibility(View.INVISIBLE);


    }


    private void setListView(String[] inString){

        String [] string;
        string = inString;

        //creates a list from string elements
        final List<String> Units = new ArrayList<String>(Arrays.asList(string));

        //creates and array adapter from list
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Units);

        //sets adapter
        inListView.setAdapter(arrayAdapter);
        outListView.setAdapter(arrayAdapter);

        //input option handler
        inListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String selectedFromList = (inListView.getItemAtPosition(position).toString());

                inUnit = selectedFromList;

                inListView.setVisibility(View.INVISIBLE);
                showInImageView.setVisibility(View.VISIBLE);
                showOutImageView.setVisibility(View.VISIBLE);

            }
        });

        //output option handler
        outListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                String selectedFromList = (outListView.getItemAtPosition(position).toString());

                outUnit = selectedFromList;

                outListView.setVisibility(View.INVISIBLE);
                showInImageView.setVisibility(View.VISIBLE);
                showOutImageView.setVisibility(View.VISIBLE);
            }


        });



    }


    public void setVolumeListView(View view){

        check = true;


        setListView(volumeUnits);

    }


    // OnClick Listener for currency image button

    public void setCurrencyListView(View view){

        isCurrency = true;


        setListView(currencyTypes);

    }

    public void openInListView(View view){

        inListView.setVisibility(View.VISIBLE);
        showInImageView.setVisibility(View.INVISIBLE);
        showOutImageView.setVisibility(View.INVISIBLE);
    }

    public void openOutListView(View view){

        outListView.setVisibility(View.VISIBLE);
        showInImageView.setVisibility(View.INVISIBLE);
        showOutImageView.setVisibility(View.INVISIBLE);
    }


    public void conversion(View view){

        RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(700);

// Start animating the image
        final ImageView spin = (ImageView) findViewById(R.id.convert);
        spin.startAnimation(anim);

        anim.setRepeatCount(0);

// Later.. stop the animation
        //spin.setAnimation(null);

        if(inUnit == volumeUnits[0].toString() && outUnit== volumeUnits[1].toString()){
            outValue = input.getText().toString();

            inValue = Double.parseDouble(outValue);

            volume.cfTocm(inValue);

            inValue = volume.cm;

            outValue = String.format("%.5f m\u00B3", inValue);


            output.setText(outValue);

        }
        else{

        }


    }


}
