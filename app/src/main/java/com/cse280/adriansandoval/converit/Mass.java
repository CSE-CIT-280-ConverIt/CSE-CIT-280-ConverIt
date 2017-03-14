package com.cse280.adriansandoval.converit;

/**
 * Created by AdrianSandoval on 1/25/17.
 */

public class Mass {
    double kg, gr, usTon, lb, oz;

    public double kgTogr(double kg){
        gr = (kg/0.001);
        return gr;
    }
    public double kgTousTon(double kg){
        usTon = (kg/907.185);
        return usTon;
    }
    public double kgTolb(double kg){
        lb = (kg/0.453592);
        return lb;
    }
    public double kgTooz(double kg){
        oz = (kg/0.0283495);
        return oz;
    }

    public double grTokg(double gr){
        kg = (gr*0.001);
        return kg;
    }
    public double grTousTon(double gr){
        usTon = (gr/907185);
        return usTon;
    }
    public double grTolb(double gr){
        lb = (gr/453.592);
        return lb;
    }
    public double grTooz(double gr){
        oz = (gr/28.3495);
        return oz;
    }

    public double usTonTokg(double usTon){
        kg = (usTon*907.185);
        return kg;
    }
    public double usTonTogr(double usTon){
        gr = (usTon*907185);
        return gr;
    }
    public double usTonTolb(double usTon){
        lb = (usTon/0.0005);
        return lb;
    }
    public double usTonTooz(double usTon){
        oz = (usTon*32000);
        return oz;
    }

    public double lbTokg(double lb){
        kg = (lb*0.453592);
        return kg;
    }
    public double lbTogr(double lb){
        gr = (lb*453.592);
        return gr;
    }
    public double lbTousTon(double lb){
        usTon = (lb*0.0005);
        return usTon;
    }
    public double lbTooz(double lb){
        oz = (lb*16);
        return oz;
    }

    public double ozTokg(double oz){
        kg = (oz*0.0283495);
        return kg;
    }
    public double ozTogr(double oz){
        gr = (oz*28.3495);
        return gr;
    }
    public double ozTousTon(double oz){
        usTon = (oz/32000);
        return usTon;
    }
    public double ozTolb(double oz){
        lb = (oz/16);
        return lb;
    }

}
