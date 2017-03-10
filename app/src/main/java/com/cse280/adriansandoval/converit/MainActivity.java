package com.cse280.adriansandoval.converit;

import android.content.Intent;
import android.icu.util.Output;
import android.os.AsyncTask;
<<<<<<< HEAD
import android.graphics.Color;
import android.net.Uri;
=======
>>>>>>> master
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
<<<<<<< HEAD
import android.widget.Toast;

import org.w3c.dom.Text;
=======
>>>>>>> master

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jsoup.*;
import org.jsoup.nodes.*;
<<<<<<< HEAD
=======

public class MainActivity extends AppCompatActivity  {

    static boolean isCurrency=false;
    boolean isLength=false;
    boolean isMass=false;
    boolean isTemperature=false;
    boolean isVolume=false;

>>>>>>> master


    //Volume
    Volume volume = new Volume();
<<<<<<< HEAD
    Currency currency = new Currency();
=======
    Currency currency= new Currency();
>>>>>>> master
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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


<<<<<<< HEAD
=======

>>>>>>> master
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

<<<<<<< HEAD
        // create an instance of the parsing class
        new parseRates().execute();
=======

           // create an instance of the parsing class
         new parseRates().execute();
>>>>>>> master
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

<<<<<<< HEAD
    }

    private void noBlink(TextView itv) {
        final TextView tv = itv;
        tv.clearAnimation();
=======
>>>>>>> master


<<<<<<< HEAD
    public void setVolumeListView(View view) {

        isVolume = true;
        isCurrency = false;
        isLength = false;
        isMass = false;
        isTemperature = false;
        vol.setVisibility(View.VISIBLE);
        if (isVolume == true) {
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
=======
>>>>>>> master

        setListView(volumeUnits);

    }


    // OnClick Listener for currency image button

<<<<<<< HEAD
    public void setCurrencyListView(View view) {
        isCurrency = true;
        if (isCurrency == true) {
            blink(cur);
            noBlink(vol);
        }

        isVolume = false;
        isLength = false;
        isMass = false;
        isTemperature = false;
        currencyConversion();
        cur.setVisibility(View.VISIBLE);
        len.setVisibility(View.INVISIBLE);
        mass.setVisibility(View.INVISIBLE);
        temp.setVisibility(View.INVISIBLE);
        vol.setVisibility(View.INVISIBLE);
=======
    public void setCurrencyListView(View view){





        isCurrency = true;
        boolean isLength=false;
        boolean isMass=false;
        boolean isTemperature=false;
        boolean isVolume=false;
        currencyConversion();

>>>>>>> master
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

<<<<<<< HEAD
    public boolean noSelection() {
        return isVolume == false && isCurrency == false && isLength == false && isMass == false && isTemperature == false;
=======

    public void conversion(View view){
>>>>>>> master

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


        }
        catch (Exception e){
            output.setText("Enter Input Value");
        }



    }



// A method that will extract the rates from the webpage


public class parseRates extends AsyncTask<Void,Void,Void>{
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        getRate = doc.text();
// sends the value to a method to check if it contains spaces and removes spaces if they exist
        usdToJPY = getRate.substring(95, 102);
        usdToJPY=removeSpaces(usdToJPY);

         eurToUSD = getRate.substring(22, 26);
        eurToUSD =removeSpaces(eurToUSD );

        gbpToUSD = getRate.substring(150, 154);
        gbpToUSD=removeSpaces(gbpToUSD);

        eurToGBP=getRate.substring(214, 218);
        eurToGBP=removeSpaces(eurToGBP);

        eurToJPY = getRate.substring(342,350);
        eurToJPY=removeSpaces(eurToJPY);


        gbpToJPY= getRate.substring(598,606);
        gbpToJPY=removeSpaces(gbpToJPY);

        return null;
    }

<<<<<<< HEAD

            }
        }catch(NumberFormatException ex){

                Toast.makeText(this, "Please enter a value to convert", Toast.LENGTH_SHORT).show();
                return;

            }


    }

    public void currencyConversion(){

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
=======
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
currency.eurToUsd=Double.parseDouble(eurToUSD);
currency.gbpToUsd=Double.parseDouble(gbpToUSD);
currency.eurToGbp=Double.parseDouble(eurToGBP);
currency.eurToJpy=Double.parseDouble(eurToJPY);
currency.usdToJpy=Double.parseDouble(usdToJPY);
currency.gbpToJpy=Double.parseDouble(gbpToJPY);


    }

    // A method that removes spaces from numbers
    public  String removeSpaces(String s){
        String concat="";
        for (int i = 0; i <= s.length()-1 ; i++) {
            if (s.charAt(i) != ' ') {
                concat = concat + s.charAt(i);
            }
>>>>>>> master


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


        }
        catch (Exception e){
            output.setText("Enter Input Value");
        }



    }
}


// A method that will extract the rates from the webpage


class parseRates extends AsyncTask<Void,Void,Void>{
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        getRate = doc.text();
// sends the value to a method to check if it contains spaces and removes spaces if they exist
        usdToJPY = getRate.substring(95, 102);
        usdToJPY=removeSpaces(usdToJPY);

        eurToUSD = getRate.substring(22, 26);
        eurToUSD =removeSpaces(eurToUSD );

        gbpToUSD = getRate.substring(150, 154);
        gbpToUSD=removeSpaces(gbpToUSD);

        eurToGBP=getRate.substring(214, 218);
        eurToGBP=removeSpaces(eurToGBP);

        eurToJPY = getRate.substring(342,350);
        eurToJPY=removeSpaces(eurToJPY);


        gbpToJPY= getRate.substring(598,606);
        gbpToJPY=removeSpaces(gbpToJPY);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Currency currency = new Currency();
        currency.eurToUsd=Double.parseDouble(eurToUSD);
        currency.gbpToUsd=Double.parseDouble(gbpToUSD);
        currency.eurToGbp=Double.parseDouble(eurToGBP);
        currency.eurToJpy=Double.parseDouble(eurToJPY);
        currency.usdToJpy=Double.parseDouble(usdToJPY);
        currency.gbpToJpy=Double.parseDouble(gbpToJPY);

        return concat;
    }

<<<<<<< HEAD
    // A method that removes spaces from numbers
    public  String removeSpaces(String s){
        String concat="";
        for (int i = 0; i <= s.length()-1 ; i++) {
            if (s.charAt(i) != ' ') {
                concat = concat + s.charAt(i);
            }
=======
}






    public void currencyConversion(){




    }
>>>>>>> master

        }

        return concat;
    }

}


