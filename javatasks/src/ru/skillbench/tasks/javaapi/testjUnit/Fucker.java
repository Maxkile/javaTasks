package ru.skillbench.tasks.javaapi.testjUnit;

public class Fucker{
    private double fuck1;
    private static double fuck2;

    public Fucker(double a,double b){
        fuck1 = a;
        fuck2 = b;
    }


    public Fucker(){
        fuck1 = 1;
        fuck2 = 2;
    }

    public Fucker(Double fuck){
        fuck1 = fuck;
        fuck2 = fuck;
    }

    public double getFuck(String a){
        System.out.println(a);
        return fuck1 - fuck2;
    }

    public void qwe(Object b){

    }
}
