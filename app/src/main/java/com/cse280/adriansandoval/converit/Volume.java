package com.cse280.adriansandoval.converit;

/**
 * Created by AdrianSandoval on 1/25/17.
 */

public class Volume {


    double cf;
    double cm;
    double li;
    double gal;
    double mili;

    public double cfTocm(double cf){

        cm = (cf/35.315);

        return cm;
    }

    public double cfToli(double cf){

        li = (cf/28.3168);

        return li;

    }

    public double cfTogal(double cf){

        gal = (cf/7.48052);

        return gal;

    }

    public double cfTomili(double cf){

        mili = (28316.8/cf);

        return mili;

    }


    public double cmTocf(double cm){

        cf = (cm * 35.315);

        return cf;
    }

    public double cmToli(double cm){

        li = (cm/1000);

        return li;

    }

    public double cmTogal(double cm){

        gal = (cm/264.172);

        return gal;

    }

    public double cmTomili(double cm){

        mili = (cm/1000000);

        return mili;

    }

    public double liTocf(double li){

        cf = (28.3168 * li);

        return cf;
    }

    public double liTocm(double li){

        cm = (1000*li);

        return cm;


    }

    public double liTogal(double li){

        gal = (li/0.264172);
        return gal;

    }

    public double liTomili(double li){

        mili = (li/1000);

        return mili;

    }

    public double galTocm(double gal){

        cm = (264.172*gal);

        return cm;


    }

    public double galToli(double gal){

        li = (0.264172*gal);

        return li;

    }

    public double galTocf(double gal){

        cf = (7.48052*gal);

        return cf;
    }

    public double galTomili(double gal){

        mili = (gal/3785.41);
        return mili;

    }

    public double miliTocm(double mili){

        cm = (1000000*mili);
        return cm;

    }

    public double miliToli(double mili){

        li = (1000*mili);

        return li;


    }

    public double miliTogal(double mili){

        gal = (3785.41*mili);

        return gal;

    }

    public double miliTocf(double mili){
        cf = (28316.8*mili);

        return cf;
    }

}
