package com.cse280.adriansandoval.converit;

/**
 * Created by AdrianSandoval on 1/25/17.
 */

public class Length {


    double meters;
    double kilometers;
    double centimeters;
    double feet;
    double yards;
    double miles;

    public double metersToMeters( double meters){

        return meters;
    }

    public double metersToKilometers( double meters){

        kilometers = (meters / 1000.0);

        return kilometers;
    }

    public double metersToCentimeters( double meters){

        centimeters = (meters * 100);

        return centimeters;
    }

    public double metersToFeet( double meters){

        feet = (meters * 3.28);

        return feet;
    }

    public double metersToYards( double meters){

        yards = (meters * 1.09);

        return yards;
    }

    public double metersToMiles( double meters){

        miles = (meters * 0.000621371);

        return miles;
    }

    public double kilometersToMeters( double kilometers){

        meters = (kilometers * 1000);

        return meters;
    }

    public double kilometersToKilometers( double kilometers){

        return kilometers;
    }

    public double kilometersToCentimeters( double kilometers){

        centimeters = (kilometers * 100000);

        return centimeters;
    }

    public double kilometersToFeet( double kilometers){

        feet = (kilometers * 3280.84);

        return feet;
    }

    public double kilometersToYards( double kilometers){

        yards = (kilometers * 1093.61);

        return yards;
    }

    public double kilometersToMiles( double kilometers){

        miles = (kilometers * 0.621371);

        return miles;
    }

    public double centimetersToMeters( double centimeters){

        meters = (centimeters * 0.01);

        return meters;
    }

    public double centimetersToKilometers( double centimeters){

        kilometers = (centimeters * 0.00001);

        return kilometers;
    }

    public double centimetersToCentimeters( double centimeters){

        return centimeters;
    }

    public double centimetersToFeet( double centimeters){

        feet = (centimeters * 0.0328084);

        return feet;
    }

    public double centimetersToYards( double centimeters){

        yards = (centimeters * 0.010936133333333);

        return yards;
    }

    public double centimetersToMiles( double centimeters){

        miles = (centimeters * .0000062137);

        return miles;
    }

    public double feetToMeters( double feet){

        meters = (feet * 0.3048);

        return meters;
    }

    public double feetToKilometers( double feet){

        kilometers = (feet * 0.0003048);

        return kilometers;
    }

    public double feetToCentimeters( double feet){

        centimeters = (feet * 30.48);

        return centimeters;
    }

    public double feetToFeet( double feet){

        return feet;
    }

    public double feetToYards( double feet){

        yards = (feet * 0.333333);

        return yards;
    }

    public double feetToMiles( double feet){

        miles = (feet * 0.000189394);

        return miles;
    }

    public double yardsToMeters( double yards){

        meters = (yards * 0.9144);

        return meters;
    }

    public double yardsToKilometers( double yards){

        kilometers = (yards * 0.0009144);

        return kilometers;
    }

    public double yardsToCentimeters( double yards){

        centimeters = (yards * 91.44);

        return centimeters;
    }

    public double yardsToFeet( double yards){

        feet = (yards * 3);

        return feet;
    }

    public double yardsToYards( double yards){

        return yards;
    }

    public double yardsToMiles( double yards){

        miles = (yards * 0.000568182);

        return miles;
    }

    public double milesToMeters( double miles){

        meters = (miles * 1609.34);

        return meters;
    }

    public double milesToKilometers( double miles){

        kilometers = (miles * 1.60934);

        return kilometers;
    }

    public double milesToCentimeters( double miles){

        centimeters = (miles * 160934);

        return centimeters;
    }

    public double milesToFeet( double miles){

        feet = (miles * 5280);

        return feet;
    }

    public double milesToYards( double miles){

        yards = (miles * 1760);

        return yards;
    }

    public double milesToMiles( double miles){

        return miles;
    }
}
