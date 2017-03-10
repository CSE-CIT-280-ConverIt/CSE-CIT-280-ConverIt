package com.cse280.adriansandoval.converit;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
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

import org.w3c.dom.Text;

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
    String[] currencyTypes = {"Us Dollar",
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
    boolean isCurrency = false;
    boolean isLength = false;
    boolean isMass = false;
    boolean isTemperature = false;
    boolean isBlinking = false;


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
    private void noBlink(TextView itv){
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
        if(isVolume==true){
            blink(vol);
            noBlink(cur);
            noBlink(len);
            noBlink(mass);
            noBlink(temp);
        }else noBlink(vol);

        len.setVisibility(View.INVISIBLE);
        mass.setVisibility(View.INVISIBLE);
        temp.setVisibility(View.INVISIBLE);
        cur.setVisibility(View.INVISIBLE);

        setListView(volumeUnits);

    }


    // OnClick Listener for currency image button

    public void setCurrencyListView(View view) {
        isCurrency = true;
        if(isCurrency == true){
            blink(cur);
            noBlink(vol);
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

        boolean digitsOnly = TextUtils.isDigitsOnly(input.getText());

        if (noSelection()) {
            Toast.makeText(this, "Choose Conversion Type", Toast.LENGTH_SHORT).show();
            return;
        } else if (digitsOnly) {

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


        } else if (!digitsOnly) {
            Toast.makeText(this, "YOU MUST ENTER DIGITS ONLY!", Toast.LENGTH_SHORT).show();
            return;

        }


    }


    private void VolumeConvert() {
        boolean digitsOnly = TextUtils.isDigitsOnly(input.getText());

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

                outValue = String.format("%.3f mL", inValue);
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
                Toast.makeText(this, "Choose A Unit Of Conversion", Toast.LENGTH_SHORT).show();
                return;

            }

        } catch (NumberFormatException ex) {

            Toast.makeText(this, "Please enter a value to convert", Toast.LENGTH_SHORT).show();
            return;


        }


    }




}
