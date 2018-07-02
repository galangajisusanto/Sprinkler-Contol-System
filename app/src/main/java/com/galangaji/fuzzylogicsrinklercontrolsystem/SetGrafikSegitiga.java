package com.galangaji.fuzzylogicsrinklercontrolsystem;

public class SetGrafikSegitiga {
    double a,b,c,d;
    public SetGrafikSegitiga(double a,double b,double c){
        this.a=a;
        this.b=b;
        this.c=c;
    }
    double Membership(double x)
    {
        if(x==b)
        {
            return 1.0;
        }
        else if(x>a&&x<b)
        {
            return ((x-a)/(b-a));
        }
        else if(x>b&&x<c)
        {
            return ((c-x)/(c-b));
        }
        else{
            return 0.0;
        }
    }
}
