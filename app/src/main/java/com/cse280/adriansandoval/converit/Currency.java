package com.cse280.adriansandoval.converit;

/**
 * Created by AdrianSandoval on 1/25/17.
 */

public class Currency {


    double usdToJpy;
    double gbpToUsd;
    double eurToUsd;
    double eurToGbp;
    double eurToJpy;
    double gbpToJpy;
    double result;
    // us dollar to Euro
    public double usDollarToEur(double us){
        result=0;
        result= us/eurToUsd;
        return result;
    }
    public double usDollarToGbpound(double us){

        // us dollar to GB Pounds
        result=0;
        result=us/gbpToUsd;
        return result;
    }

// us dollar to Japanese Yen
    public double usDollarToJPYen(double us){
result=0;
        result= us * usdToJpy;

        return result;
    }


    // Euro to us dollar
public double euroTousDollar(double eu) {
result=0;
    result=eu*eurToUsd;
    return result;
}


    // Euro to GB Pound
    public double euroToGbPound(double eu) {
result=0;
        result= eu*eurToGbp;
        return result;
    }


    // Euro to Japanese Yen
    public double euroTouJPYen(double eu) {
result=0;
        result= eu*eurToJpy;
        return result;
    }


    // GB Pound to US Dollar
    public double gbpTousDollar(double gp){
result=0;
        result= gp*gbpToUsd;
        return result;
    }


// GB Pound to Euro
    public double gbpTousEuro(double gp){
result= 0;
        result= gp/eurToGbp;
        return result;
    }


// GB Pound to Japanese Yen
    public double gbpToYen(double gp){
result=0;
        result= gp*gbpToJpy;
        return result;
    }



    // Japanese Yen to US Dollar
    public double JPYentousDollar(double jp){
result =0 ;
        result= jp /usdToJpy;
        return result;
    }


// Japanses Yen to Euro
    public double JPYenntoEuro(double jp){
result =0 ;
        result= jp/eurToJpy;
        return result;
    }


    //Japanses Yrn to GB Pounds
    public double JPYenntoGBPound(double jp){
result=0;
        result= jp/gbpToJpy;
        return result;
    }
}
