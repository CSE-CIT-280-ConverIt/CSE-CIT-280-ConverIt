package com.cse280.adriansandoval.converit;

import android.content.Intent;
import android.icu.util.Output;
import android.os.AsyncTask;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.jsoup.nodes.Document;
import org.w3c.dom.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jsoup.*;
import org.jsoup.nodes.*;

public class MainActivity extends AppCompatActivity {

    //Volume
    Volume volume = new Volume();
    Currency currency = new Currency();
    Length length = new Length();
    Mass mass2 = new Mass();

    String[] volumeUnits = new String[]{
            "Cubic Feet",
            "Cubic Meter",
            "Liters",
            "Gallons",
            "MilliLiter"
    };

    // A String array that holds all currencies
    String[] currencyTypes = {"Us Dollar",
            "Euro",
            "GB Pound",
            "Yen"
    };
    // String array for length units
    String [] lengthUnits = {"Meters",
            "Kilometers",
            "Centimeters",
            "Feet",
            "Yards",
            "Miles"
    };
    //String array for mass units
    String[] massUnits= new String[]{
            "Kilogram",
            "Gram",
            "USTon",
            "Pound",
            "Ounce",
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

    //extra
    TextView len;
    TextView mass;
    TextView temp;
    TextView vol;
    TextView cur;

    //conversion variables
    public double inValue;
    public String outValue;

    //option check
    boolean isVolume = false;
    public boolean isCurrency = false;
    boolean isLength = false;
    boolean isMass = false;
    boolean isTemperature = false;
    boolean isBlinking = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //register contents
        VolumeImageView = (ImageView) findViewById(R.id.volumeImage);
        // showInImageView= (ImageView) findViewById(R.id.showInListView);
        //showOutImageView= (ImageView) findViewById(R.id.showOutListView);
        convert = (ImageView) findViewById(R.id.convert);

        inListView = (ListView) findViewById(R.id.inListView);
        outListView = (ListView) findViewById(R.id.outListView);

        input = (EditText) findViewById(R.id.input);

        output = (TextView) findViewById(R.id.outPut);

        len = (TextView) findViewById(R.id.len);
        mass = (TextView) findViewById(R.id.mass);
        temp = (TextView) findViewById(R.id.temp);
        vol = (TextView) findViewById(R.id.vol);
        cur = (TextView) findViewById(R.id.cur);

        inListView.setVisibility(View.VISIBLE);
        outListView.setVisibility(View.VISIBLE);
        len.setVisibility(View.INVISIBLE);
        mass.setVisibility(View.INVISIBLE);
        temp.setVisibility(View.INVISIBLE);
        vol.setVisibility(View.INVISIBLE);
        cur.setVisibility(View.INVISIBLE);

        // create an instance of the parsing class
        new parseRates().execute();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private void setListView(String[] inString) {

        String[] string;
        string = inString;

        //creates a list from string elements
        final List<String> Units = new ArrayList<String>(Arrays.asList(string));

        //creates and array adapter from list
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Units);

        //sets adapter
        inListView.setAdapter(arrayAdapter);
        outListView.setAdapter(arrayAdapter);

        //input option handler
        inListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = (inListView.getItemAtPosition(position).toString());

                inUnit = selectedFromList;

                //inListView.setVisibility(View.INVISIBLE);
                // showInImageView.setVisibility(View.VISIBLE);
                // showOutImageView.setVisibility(View.VISIBLE);

            }
        });

        //output option handler
        outListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedFromList = (outListView.getItemAtPosition(position).toString());

                outUnit = selectedFromList;

                //outListView.setVisibility(View.INVISIBLE);
                //showInImageView.setVisibility(View.VISIBLE);
                //showOutImageView.setVisibility(View.VISIBLE);
            }


        });


    }

    private void blink(TextView itv) {
        final TextView tv = itv;
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        tv.startAnimation(anim);

        /*if (isBlinking) {
            tv.startAnimation(anim);
            isBlinking = true;
        } else {
            tv.clearAnimation(); // cancel blink animation
            tv.setAlpha(1.0f); // restore original alpha
            isBlinking = false;
        }*/

    }

    private void noBlink(TextView itv) {
        final TextView tv = itv;
        tv.clearAnimation();

    }

    public void setVolumeListView(View view) {

        isVolume = true;
        isCurrency = false;
        isLength = false;
        isMass = false;
        isTemperature = false;
        vol.setVisibility(View.VISIBLE);
        if (isVolume) {
            blink(vol);
            noBlink(cur);
            noBlink(len);
            noBlink(mass);
            noBlink(temp);
        } else noBlink(vol);

        len.setVisibility(View.INVISIBLE);
        mass.setVisibility(View.INVISIBLE);
        temp.setVisibility(View.INVISIBLE);
        cur.setVisibility(View.INVISIBLE);

        setListView(volumeUnits);

    }


    // OnClick Listener for currency image button

    public void setCurrencyListView(View view) {
        isCurrency = true;
        if (isCurrency) {
            blink(cur);
            noBlink(vol);
            noBlink(len);
            noBlink(mass);
            noBlink(temp);
        }

        isVolume = false;
        isLength = false;
        isMass = false;
        isTemperature = false;

        cur.setVisibility(View.VISIBLE);
        len.setVisibility(View.INVISIBLE);
        mass.setVisibility(View.INVISIBLE);
        temp.setVisibility(View.INVISIBLE);
        vol.setVisibility(View.INVISIBLE);

        setListView(currencyTypes);

    }


    // OnClick listener for length image button

    public void setLengthListView(View view) {

        isLength = true;
        isVolume = false;
        isCurrency = false;

        isMass = false;
        isTemperature = false;
        len.setVisibility(View.VISIBLE);
        if (isLength) {
            blink(len);
            noBlink(cur);
            noBlink(vol);
            noBlink(mass);
            noBlink(temp);
        } else noBlink(len);

        vol.setVisibility(View.INVISIBLE);
        mass.setVisibility(View.INVISIBLE);
        temp.setVisibility(View.INVISIBLE);
        cur.setVisibility(View.INVISIBLE);

        setListView(lengthUnits);

    }

    public void setMassListView(View view) {
        isMass = true;
        isVolume = false;
        isCurrency = false;
        isLength = false;
        isTemperature = false;
        mass.setVisibility(View.VISIBLE);
        if (isMass) {
            blink(mass);
            noBlink(vol);
            noBlink(len);
            noBlink(cur);
            noBlink(temp);
        }else noBlink(mass);


        len.setVisibility(View.INVISIBLE);
        cur.setVisibility(View.INVISIBLE);
        temp.setVisibility(View.INVISIBLE);
        vol.setVisibility(View.INVISIBLE);

        setListView(massUnits);

    }

    public void openInListView(View view) {

        inListView.setVisibility(View.VISIBLE);
        // showInImageView.setVisibility(View.VISIBLE);
        // showOutImageView.setVisibility(View.VISIBLE);
    }

    public void openOutListView(View view) {

        outListView.setVisibility(View.VISIBLE);
        //showInImageView.setVisibility(View.VISIBLE);
        // showOutImageView.setVisibility(View.VISIBLE);
    }

    public boolean noSelection() {
        if (isVolume == false && isCurrency == false && isLength == false && isMass == false && isTemperature == false)
            return true;

        else return false;

    }

    public void conversion(View view) {

       // boolean digitsOnly = TextUtils.isDigitsOnly(input.getText());

        if (noSelection()) {
            Toast.makeText(this, "Choose Conversion Type", Toast.LENGTH_SHORT).show();
            return;
        } else {

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

            if (isVolume == true) {
                //isCurrency = false;
                //isLength = false;
                ///isMass = false;
                //isTemperature = false;
                VolumeConvert();
            }
            if (isCurrency == true) {
                currencyConversion();
            }
            if (isLength == true){
                lengthConversion();
            }
            if (isMass == true){
                massConversion();
            }



        }


    }


    private void VolumeConvert() {
        //boolean digitsOnly = TextUtils.isDigitsOnly(input.getText());

        try {
            if (inUnit == volumeUnits[0].toString() && outUnit == volumeUnits[0].toString()) {
                if (input.getText().toString().matches("")) {

                    Toast.makeText(this, "Enter Value To Convert", Toast.LENGTH_SHORT).show();
                    return;
                } else output.setText(input.getText().toString() + " ft\u00B3");

            } else if (inUnit == volumeUnits[0].toString() && outUnit == volumeUnits[1].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.cfTocm(inValue);

                inValue = volume.cm;

                outValue = String.format("%.3f m\u00B3", inValue);


                output.setText(outValue);

            } else if (inUnit == volumeUnits[0].toString() && outUnit == volumeUnits[2].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.cfToli(inValue);

                inValue = volume.li;

                outValue = String.format("%.3f Li", inValue);
                output.setText(outValue);

            } else if (inUnit == volumeUnits[0].toString() && outUnit == volumeUnits[3].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.cfTogal(inValue);

                inValue = volume.gal;

                outValue = String.format("%.3f gal", inValue);
                output.setText(outValue);

            } else if (inUnit == volumeUnits[0].toString() && outUnit == volumeUnits[4].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.cfTomili(inValue);

                inValue = volume.mili;

               // outValue = String.format("%.3f mL", inValue);
                outValue = String.format( inValue + " mL");
                output.setText(outValue);

            } else if (inUnit == volumeUnits[1].toString() && outUnit == volumeUnits[0].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.cmTocf(inValue);

                inValue = volume.cf;

                outValue = String.format("%.3f ft\u00B3", inValue);
                output.setText(outValue);


            } else if (inUnit == volumeUnits[1].toString() && outUnit == volumeUnits[1].toString()) {
                if (input.getText().toString().matches("")) {

                    Toast.makeText(this, "Enter Value To Convert", Toast.LENGTH_SHORT).show();
                    return;
                } else output.setText(input.getText().toString() + " m\u00B3");
            } else if (inUnit == volumeUnits[1].toString() && outUnit == volumeUnits[2].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.cmToli(inValue);

                inValue = volume.li;

                outValue = String.format("%.3f li", inValue);
                output.setText(outValue);

            } else if (inUnit == volumeUnits[1].toString() && outUnit == volumeUnits[3].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.cmTogal(inValue);

                inValue = volume.gal;

                outValue = String.format("%.3f gal", inValue);
                output.setText(outValue);


            } else if (inUnit == volumeUnits[1].toString() && outUnit == volumeUnits[4].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.cmTomili(inValue);

                inValue = volume.mili;

                outValue = String.format("%.3f mL", inValue);
                output.setText(outValue);


            } else if (inUnit == volumeUnits[2].toString() && outUnit == volumeUnits[0].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.liTocf(inValue);

                inValue = volume.cf;

                outValue = String.format("%.3f ft\u00B3", inValue);
                output.setText(outValue);


            } else if (inUnit == volumeUnits[2].toString() && outUnit == volumeUnits[1].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.liTocm(inValue);

                inValue = volume.cm;

                outValue = String.format("%.3f m\u00B3", inValue);
                output.setText(outValue);
            } else if (inUnit == volumeUnits[2].toString() && outUnit == volumeUnits[2].toString()) {
                if (input.getText().toString().matches("")) {

                    Toast.makeText(this, "Enter Value To Convert", Toast.LENGTH_SHORT).show();
                    return;
                } else output.setText(input.getText().toString() + " li");
            } else if (inUnit == volumeUnits[2].toString() && outUnit == volumeUnits[3].toString()) {
                outValue = input.getText().toString();


                inValue = Double.parseDouble(outValue);

                volume.liTogal(inValue);

                inValue = volume.gal;

                outValue = String.format("%.3f gal", inValue);
                output.setText(outValue);

            } else if (inUnit == volumeUnits[2].toString() && outUnit == volumeUnits[4].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.liTomili(inValue);

                inValue = volume.mili;

                outValue = String.format("%.3f mL", inValue);
                output.setText(outValue);
            } else if (inUnit == volumeUnits[3].toString() && outUnit == volumeUnits[0].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.galTocf(inValue);

                inValue = volume.cf;

                outValue = String.format("%.3f ft\u00B3", inValue);
                output.setText(outValue);

            } else if (inUnit == volumeUnits[3].toString() && outUnit == volumeUnits[1].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.galTocm(inValue);

                inValue = volume.cm;

                outValue = String.format("%.3f m\u00B3", inValue);
                output.setText(outValue);
            } else if (inUnit == volumeUnits[3].toString() && outUnit == volumeUnits[2].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.galToli(inValue);

                inValue = volume.li;

                outValue = String.format("%.3f li", inValue);
                output.setText(outValue);

            } else if (inUnit == volumeUnits[3].toString() && outUnit == volumeUnits[3].toString()) {
                if (input.getText().toString().matches("")) {

                    Toast.makeText(this, "Enter Value To Convert", Toast.LENGTH_SHORT).show();
                    return;
                } else output.setText(input.getText().toString() + " gal");
            } else if (inUnit == volumeUnits[3].toString() && outUnit == volumeUnits[4].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.galTomili(inValue);

                inValue = volume.mili;

                outValue = String.format("%.3f mL", inValue);
                output.setText(outValue);
            } else if (inUnit == volumeUnits[4].toString() && outUnit == volumeUnits[0].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.miliTocf(inValue);

                inValue = volume.cf;

                outValue = String.format("%.3f ft\u00B3", inValue);
                output.setText(outValue);
            } else if (inUnit == volumeUnits[4].toString() && outUnit == volumeUnits[1].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);
                volume.miliTocm(inValue);

                inValue = volume.cm;

                outValue = String.format("%.3f m\u00B3", inValue);
                output.setText(outValue);
            } else if (inUnit == volumeUnits[4].toString() && outUnit == volumeUnits[2].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                volume.miliToli(inValue);

                inValue = volume.li;

                outValue = String.format("%.3f li", inValue);
                output.setText(outValue);

            } else if (inUnit == volumeUnits[4].toString() && outUnit == volumeUnits[3].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);


                volume.miliTogal(inValue);

                inValue = volume.gal;

                outValue = String.format("%.3f gal", inValue);
                output.setText(outValue);
            } else if (inUnit == volumeUnits[4].toString() && outUnit == volumeUnits[4].toString()) {
                if (input.getText().toString().matches("")) {

                    Toast.makeText(this, "Enter Value To Convert", Toast.LENGTH_SHORT).show();
                    return;
                }

                output.setText(input.getText().toString() + " mL");
            } else if (inUnit == null) {
                Toast.makeText(this, "Choose \"From\" Conversion", Toast.LENGTH_SHORT).show();
                return;
            } else if (outUnit == null) {
                Toast.makeText(this, "Choose \"To\" Conversion", Toast.LENGTH_SHORT).show();
                return;

            } else {
                if(isVolume == false){
                    Toast.makeText(this, "Choose A Unit Of Conversion", Toast.LENGTH_SHORT).show();
                    return;
                }



            }
        } catch (NumberFormatException ex) {

            Toast.makeText(this, "Please enter a value to convert", Toast.LENGTH_SHORT).show();
            return;

        }


    }

    private void lengthConversion() {
        boolean digitsOnly = TextUtils.isDigitsOnly(input.getText());

        try {

            if (inUnit == lengthUnits[0].toString() && outUnit == lengthUnits[0].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.metersToMeters(inValue);

                inValue = length.meters;

                outValue = String.format("%.3f", inValue);


                output.setText(outValue);
            } else if (inUnit == lengthUnits[0].toString() && outUnit == lengthUnits[1].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.metersToKilometers(inValue);

                inValue = length.kilometers;

                outValue = String.format("%.3f", inValue);


                output.setText(outValue);

            } else if (inUnit == lengthUnits[0].toString() && outUnit == lengthUnits[2].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.metersToCentimeters(inValue);

                inValue = length.centimeters;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[0].toString() && outUnit == lengthUnits[3].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.metersToFeet(inValue);

                inValue = length.feet;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[0].toString() && outUnit == lengthUnits[4].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.metersToYards(inValue);

                inValue = length.yards;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[0].toString() && outUnit == lengthUnits[5].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.metersToMiles(inValue);

                inValue = length.miles;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[1].toString() && outUnit == lengthUnits[0].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.kilometersToMeters(inValue);

                inValue = length.meters;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[1].toString() && outUnit == lengthUnits[1].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.kilometersToKilometers(inValue);

                inValue = length.kilometers;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[1].toString() && outUnit == lengthUnits[2].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.kilometersToCentimeters(inValue);

                inValue = length.centimeters;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[1].toString() && outUnit == lengthUnits[3].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.kilometersToFeet(inValue);

                inValue = length.feet;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[1].toString() && outUnit == lengthUnits[4].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.kilometersToYards(inValue);

                inValue = length.yards;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[1].toString() && outUnit == lengthUnits[5].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.kilometersToMiles(inValue);

                inValue = length.miles;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[2].toString() && outUnit == lengthUnits[0].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.centimetersToMeters(inValue);

                inValue = length.meters;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[2].toString() && outUnit == lengthUnits[1].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.centimetersToKilometers(inValue);

                inValue = length.kilometers;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[2].toString() && outUnit == lengthUnits[2].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.centimetersToCentimeters(inValue);

                inValue = length.centimeters;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[2].toString() && outUnit == lengthUnits[3].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.centimetersToFeet(inValue);

                inValue = length.feet;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[2].toString() && outUnit == lengthUnits[4].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.centimetersToYards(inValue);

                inValue = length.yards;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[2].toString() && outUnit == lengthUnits[5].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.centimetersToMiles(inValue);

                inValue = length.miles;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[3].toString() && outUnit == lengthUnits[0].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.feetToMeters(inValue);

                inValue = length.meters;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[3].toString() && outUnit == lengthUnits[1].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.feetToKilometers(inValue);

                inValue = length.kilometers;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[3].toString() && outUnit == lengthUnits[2].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.feetToCentimeters(inValue);

                inValue = length.centimeters;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[3].toString() && outUnit == lengthUnits[3].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.feetToFeet(inValue);

                inValue = length.feet;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[3].toString() && outUnit == lengthUnits[4].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.feetToYards(inValue);

                inValue = length.yards;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[3].toString() && outUnit == lengthUnits[5].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.feetToMiles(inValue);

                inValue = length.miles;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[4].toString() && outUnit == lengthUnits[0].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.yardsToMeters(inValue);

                inValue = length.meters;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[4].toString() && outUnit == lengthUnits[1].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.yardsToKilometers(inValue);

                inValue = length.kilometers;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[4].toString() && outUnit == lengthUnits[2].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.yardsToCentimeters(inValue);

                inValue = length.centimeters;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[4].toString() && outUnit == lengthUnits[3].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.yardsToFeet(inValue);

                inValue = length.feet;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[4].toString() && outUnit == lengthUnits[4].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.yardsToYards(inValue);

                inValue = length.yards;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[4].toString() && outUnit == lengthUnits[5].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.yardsToMiles(inValue);

                inValue = length.miles;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[5].toString() && outUnit == lengthUnits[0].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.milesToMeters(inValue);

                inValue = length.meters;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[5].toString() && outUnit == lengthUnits[1].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.milesToKilometers(inValue);

                inValue = length.kilometers;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[5].toString() && outUnit == lengthUnits[2].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.milesToCentimeters(inValue);

                inValue = length.centimeters;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[5].toString() && outUnit == lengthUnits[3].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.milesToFeet(inValue);

                inValue = length.feet;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[5].toString() && outUnit == lengthUnits[4].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.milesToYards(inValue);

                inValue = length.yards;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == lengthUnits[5].toString() && outUnit == lengthUnits[5].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                length.milesToMiles(inValue);

                inValue = length.miles;

                outValue = String.format("%.3f", inValue);
                output.setText(outValue);

            } else if (inUnit == null) {
                Toast.makeText(this, "Choose \"From\" Conversion", Toast.LENGTH_SHORT).show();
                return;
            } else if (outUnit == null) {
                Toast.makeText(this, "Choose \"To\" Conversion", Toast.LENGTH_SHORT).show();
                return;

            } else {
                Toast.makeText(this, "Choose A Unit Of Conversion", Toast.LENGTH_SHORT).show();
                return;


            }
        } catch (NumberFormatException ex) {

            Toast.makeText(this, "Please enter a value to convert", Toast.LENGTH_SHORT).show();
            return;

        }
    }

    //Mass Conversion If Statements




    public void massConversion() {
        boolean digitsOnly = TextUtils.isDigitsOnly(input.getText());
        try {
            //Kilograms to Grams
            if (inUnit == massUnits[0].toString() && outUnit == massUnits[1].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.kgTogr(inValue);
                inValue = mass2.gr;
                outValue = String.format("%.0f g", inValue);
                output.setText(outValue);
            }
            //Kilograms to US Tons
            else if (inUnit == massUnits[0].toString() && outUnit == massUnits[2].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.kgTousTon(inValue);
                inValue = mass2.usTon;
                outValue = String.format("%.5f ton(s)", inValue);
                output.setText(outValue);
            }
            //Kilograms to Pounds
            else if (inUnit == massUnits[0].toString() && outUnit == massUnits[3].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.kgTolb(inValue);
                inValue = mass2.lb;
                outValue = String.format("%.2f lb", inValue);
                output.setText(outValue);
            }
            //Kilograms to Ounces
            else if (inUnit == massUnits[0].toString() && outUnit == massUnits[4].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.kgTooz(inValue);
                inValue = mass2.oz;
                outValue = String.format("%.2f oz", inValue);
                output.setText(outValue);
            }
            //Kilograms to Kilograms
            else if (inUnit == massUnits[0].toString() && outUnit == massUnits[0].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                outValue = String.format("%.0f kg", inValue);
                output.setText(outValue);
            }
            //Grams to Kilograms
            else if (inUnit == massUnits[1].toString() && outUnit == massUnits[0].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.grTokg(inValue);
                inValue = mass2.kg;
                outValue = String.format("%.3f kg", inValue);
                output.setText(outValue);
            }
            //Grams to US Tons
            else if (inUnit == massUnits[1].toString() && outUnit == massUnits[2].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.grTousTon(inValue);
                inValue = mass2.usTon;
                outValue = String.format("%.5f ton(s)", inValue);
                output.setText(outValue);
            }
            //Grams to Pounds
            else if (inUnit == massUnits[1].toString() && outUnit == massUnits[3].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.grTolb(inValue);
                inValue = mass2.lb;
                outValue = String.format("%.3f lb", inValue);
                output.setText(outValue);
            }
            //Grams to Ounces
            else if (inUnit == massUnits[1].toString() && outUnit == massUnits[4].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.grTooz(inValue);
                inValue = mass2.oz;
                outValue = String.format("%.3f oz", inValue);
                output.setText(outValue);
            }
            //Grams to Grams
            else if (inUnit == massUnits[1].toString() && outUnit == massUnits[1].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                outValue = String.format("%.0f g", inValue);
                output.setText(outValue);
            }
            //US Tons to Kilograms
            else if (inUnit == massUnits[2].toString() && outUnit == massUnits[0].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.usTonTokg(inValue);
                inValue = mass2.kg;
                outValue = String.format("%.2f kg", inValue);
                output.setText(outValue);
            }
            //US Tons to Grams
            else if (inUnit == massUnits[2].toString() && outUnit == massUnits[1].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.usTonTogr(inValue);
                inValue = mass2.gr;
                outValue = String.format("%.0f g", inValue);
                output.setText(outValue);
            }
            //US Tons to Pounds
            else if (inUnit == massUnits[2].toString() && outUnit == massUnits[3].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.usTonTolb(inValue);
                inValue = mass2.lb;
                outValue = String.format("%.0f lb", inValue);
                output.setText(outValue);
            }
            //US Tons to Ounces
            else if (inUnit == massUnits[2].toString() && outUnit == massUnits[4].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.usTonTooz(inValue);
                inValue = mass2.oz;
                outValue = String.format("%.0f oz", inValue);
                output.setText(outValue);
            }
            //US Tons to US Tons
            else if (inUnit == massUnits[2].toString() && outUnit == massUnits[2].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                outValue = String.format("%.0f ton(s)", inValue);
                output.setText(outValue);
            }

            //Pounds to Kilograms
            else if (inUnit == massUnits[3].toString() && outUnit == massUnits[0].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.lbTokg(inValue);
                inValue = mass2.kg;
                outValue = String.format("%.2f kg", inValue);
                output.setText(outValue);
            }
            //Pounds to Grams
            else if (inUnit == massUnits[3].toString() && outUnit == massUnits[1].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.lbTogr(inValue);
                inValue = mass2.gr;
                outValue = String.format("%.3f g", inValue);
                output.setText(outValue);
            }
            //Pounds to US Tons
            else if (inUnit == massUnits[3].toString() && outUnit == massUnits[2].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.lbTousTon(inValue);
                inValue = mass2.usTon;
                outValue = String.format("%.5f ton(s)", inValue);
                output.setText(outValue);
            }
            //Pounds to Ounces
            else if (inUnit == massUnits[3].toString() && outUnit == massUnits[4].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.lbTooz(inValue);
                inValue = mass2.oz;
                outValue = String.format("%.0f oz", inValue);
                output.setText(outValue);
            }
            //Pounds to Pounds
            else if (inUnit == massUnits[3].toString() && outUnit == massUnits[3].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                outValue = String.format("%.0f lb", inValue);
                output.setText(outValue);
            }

            //Ounces to Kilograms
            else if (inUnit == massUnits[4].toString() && outUnit == massUnits[0].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.ozTokg(inValue);
                inValue = mass2.kg;
                outValue = String.format("%.5f kg", inValue);
                output.setText(outValue);
            }
            //Ounces to Grams
            else if (inUnit == massUnits[4].toString() && outUnit == massUnits[1].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.ozTogr(inValue);
                inValue = mass2.gr;
                outValue = String.format("%.3f g", inValue);
                output.setText(outValue);
            }
            //Ounces to US Tons
            else if (inUnit == massUnits[4].toString() && outUnit == massUnits[2].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.ozTousTon(inValue);
                inValue = mass2.usTon;
                outValue = String.format("%.5f ton(s)", inValue);
                output.setText(outValue);
            }
            //Ounces to Pounds
            else if (inUnit == massUnits[4].toString() && outUnit == massUnits[3].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                mass2.ozTolb(inValue);
                inValue = mass2.lb;
                outValue = String.format("%.2f lb", inValue);
                output.setText(outValue);
            }
            //Ounces to Ounces
            else if (inUnit == massUnits[4].toString() && outUnit == massUnits[4].toString()) {
                outValue = input.getText().toString();
                inValue = Double.parseDouble(outValue);
                outValue = String.format("%.0f oz", inValue);
                output.setText(outValue);
            }
        }
        catch (Exception e) {
            output.setText("Enter Input Value");
        }
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }




    // A method that will extract the rates from the webpage
    public class parseRates extends AsyncTask<Void, Void, Void> {
        String getRate;
        String eurToUSD;
        String gbpToUSD;
        String eurToGBP;
        String eurToJPY;
        String usdToJPY;
        String gbpToJPY;



        @Override
        protected Void doInBackground(Void... params) {
            Document doc = null;


                try {
                    doc = Jsoup.connect("http://webrates.truefx.com/rates/connect.html?f=html").get();

                    // sends the value to a method to check if it contains spaces and removes spaces if they exist
                    getRate = doc.text();
                    usdToJPY = getRate.substring(95, 102);
                    usdToJPY = removeSpaces(usdToJPY);

                    eurToUSD = getRate.substring(22, 26);
                    eurToUSD = removeSpaces(eurToUSD);

                    gbpToUSD = getRate.substring(150, 154);
                    gbpToUSD = removeSpaces(gbpToUSD);

                    eurToGBP = getRate.substring(214, 218);
                    eurToGBP = removeSpaces(eurToGBP);

                    eurToJPY = getRate.substring(342, 350);
                    eurToJPY = removeSpaces(eurToJPY);


                    gbpToJPY = getRate.substring(598, 606);
                    gbpToJPY = removeSpaces(gbpToJPY);

                } catch (NullPointerException npe) {
                    System.out.println(doc.text());
                } catch (IOException e) {
                    e.printStackTrace();
                }





            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            currency.eurToUsd = Double.parseDouble(eurToUSD);
            currency.gbpToUsd = Double.parseDouble(gbpToUSD);
            currency.eurToGbp = Double.parseDouble(eurToGBP);
            currency.eurToJpy = Double.parseDouble(eurToJPY);
            currency.usdToJpy = Double.parseDouble(usdToJPY);
            currency.gbpToJpy = Double.parseDouble(gbpToJPY);


        }

        // A method that removes spaces from numbers
        public String removeSpaces(String s) {
            String concat = "";
            for (int i = 0; i <= s.length() - 1; i++) {
                if (s.charAt(i) != ' ') {
                    concat = concat + s.charAt(i);
                }

            }

            return concat;
        }

    }

    public void currencyConversion() {

        // CURRENCY CONVERSION IF STATEMENTS

        // CHECK FOR NULL VALUES

        try {


            // USD TO EURO
            if (inUnit == currencyTypes[0].toString() && outUnit == currencyTypes[1].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                currency.usDollarToEur(inValue);


                outValue = String.format("\u20ac%.2f", currency.result);


                output.setText(outValue);

            }

            // USD TO GBPOUND

            if (inUnit == currencyTypes[0].toString() && outUnit == currencyTypes[2].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                currency.usDollarToGbpound(inValue);


                outValue = String.format("\u00a3%.2f", currency.result);


                output.setText(outValue);

            }
            // USD TO YEN

            if (inUnit == currencyTypes[0].toString() && outUnit == currencyTypes[3].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                currency.usDollarToJPYen(inValue);


                outValue = String.format("\u00A5%.2f", currency.result);


                output.setText(outValue);

            }
            if (inUnit == currencyTypes[0].toString() && outUnit == currencyTypes[0].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);


                outValue = String.format("\u0024" +
                        "%.2f", inValue);


                output.setText(outValue);

            }


            // EURO TO US DOLLAR


            if (inUnit == currencyTypes[1].toString() && outUnit == currencyTypes[0].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                currency.euroTousDollar(inValue);


                outValue = String.format("\u0024%.2f", currency.result);


                output.setText(outValue);

            }

            // EURO TO EURO
            if (inUnit == currencyTypes[1].toString() && outUnit == currencyTypes[1].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);


                outValue = String.format("\u20ac" +
                        "%.2f", inValue);


                output.setText(outValue);

            }

            // EURO TO GB POUND
            if (inUnit == currencyTypes[1].toString() && outUnit == currencyTypes[2].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                currency.euroToGbPound(inValue);


                outValue = String.format("\u00A3%.2f", currency.result);


                output.setText(outValue);

            }


            // EURO TO YEN

            if (inUnit == currencyTypes[1].toString() && outUnit == currencyTypes[3].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                currency.euroTouJPYen(inValue);


                outValue = String.format("\u00a5%.2f", currency.result);


                output.setText(outValue);

            }


            // GB POUND TO USD DOLLAR


            if (inUnit == currencyTypes[2].toString() && outUnit == currencyTypes[0].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                currency.gbpTousDollar(inValue);


                outValue = String.format("\u0024%.2f", currency.result);


                output.setText(outValue);

            }

            // GB POUND TO EURO


            if (inUnit == currencyTypes[2].toString() && outUnit == currencyTypes[1].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                currency.gbpTousEuro(inValue);


                outValue = String.format("\u20ac%.2f", currency.result);


                output.setText(outValue);

            }
// GBP TO GBP


            if (inUnit == currencyTypes[2].toString() && outUnit == currencyTypes[2].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);


                outValue = String.format("\u00A3" +
                        "%.2f", inValue);


                output.setText(outValue);

            }
// GBP TO YEN


            if (inUnit == currencyTypes[2].toString() && outUnit == currencyTypes[3].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                currency.gbpToYen(inValue);


                outValue = String.format("\u00a5%.2f", currency.result);


                output.setText(outValue);

            }


            // YEN TO USD DOLLAR
            if (inUnit == currencyTypes[3].toString() && outUnit == currencyTypes[0].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                currency.JPYentousDollar(inValue);


                outValue = String.format("\u0024%.2f", currency.result);


                output.setText(outValue);

            }

            // JPY TO EURO

            if (inUnit == currencyTypes[3].toString() && outUnit == currencyTypes[1].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                currency.JPYenntoEuro(inValue);


                outValue = String.format("\u20ac%.2f", currency.result);


                output.setText(outValue);

            }


            //YEN TO GB POUND
            if (inUnit == currencyTypes[3].toString() && outUnit == currencyTypes[2].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);

                currency.JPYenntoGBPound(inValue);


                outValue = String.format("\u00a3%.2f", currency.result);


                output.setText(outValue);

            }


            // YEN TO YEN


            if (inUnit == currencyTypes[3].toString() && outUnit == currencyTypes[3].toString()) {
                outValue = input.getText().toString();

                inValue = Double.parseDouble(outValue);


                outValue = String.format("\u00A5%.2f", inValue);


                output.setText(outValue);

            }


        } catch (Exception e) {
            output.setText("Enter Input Value");
        }


    }

}


