package com.galangaji.fuzzylogicsrinklercontrolsystem;

public class SetGrafikTrapesium {

    double a,b,c,d;
    public SetGrafikTrapesium(double a,double b,double c,double d){
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
    }
    double Membership(double x)
    {
        if(x>a&&x<=b)
        {
            return((x-a)/(b-a));
        }
        else if(x>b&&x<=c)
        {
            return 1.0;
        }
        else if(x>c&&x<=d)
        {
            return ((d-x)/(d-c));
        }
        else{
            return 0.0;
        }
    }
}
