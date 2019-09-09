package ru.skillbench.tasks.basics.math;

import java.util.*;

public class ArrayVectorImpl implements ArrayVector {

    private double[] vector = new double[0];

    @Override
    public void set(double... elements) {
        vector = new double[elements.length];
        vector = elements;
    }

    @Override
    public double[] get() {
        return vector;
    }

    @Override
    public ArrayVector clone() {
        ArrayVector other = new ArrayVectorImpl();
        double[] array = get().clone();
        other.set(array);
        return other;
    }

    @Override
    public int getSize() {
        return vector.length;
    }

    @Override
    public void set(int index, double value) {

        if ((index >= getSize()) || ((getSize() == 0) && (index > 0))){
            double[] temp = Arrays.copyOf(vector,index+1);
            temp[index] = value;
            vector = temp;
        }
        else if (index > 0) vector[index] = value;
    }

    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        if ((index >= getSize()) || (index < 0 )) throw new ArrayIndexOutOfBoundsException();
        else return vector[index];
    }

    @Override
    public double getMax() {
        double max = vector[0];
        for (double elem: vector){
            if (elem > max) max = elem;
        }
        return max;
    }

    @Override
    public double getMin() {
        double min = vector[0];
        for (double elem: vector){
            if (elem < min) min = elem;
        }
        return min;
    }

    @Override
    public void sortAscending() {
        Arrays.sort(vector);
    }

    @Override
    public void mult(double factor) {
        for (int i = 0;i<getSize();++i){
            vector[i]*=factor;
        }
    }

    @Override
    public ArrayVector sum(ArrayVector anotherVector) {
        double[] temp = anotherVector.get();
        for(int i = 0;i<getSize();++i){
            vector[i]+=temp[i];
        }
        return this;
    }

    @Override
    public double scalarMult(ArrayVector anotherVector) {
        double res = 0;
        double[] temp = anotherVector.get();
        for(int i = 0;i < minInt(getSize(),anotherVector.getSize());++i){
            res += vector[i]*temp[i];
        }
        return res;
    }

    @Override
    public double getNorm() {
        double eNorm = 0;
        for(int i = 0;i<getSize();++i){
            eNorm += vector[i]*vector[i];
        }
        return Math.sqrt(eNorm);
    }

    public String toString(){
        String temp = "";
        int a = 5;
        for(double elem: vector){
            temp += Double.toString(elem) + " ";
        }
        return temp;
    }

    private int minInt(int a,int b){
        if (a >= b) return b;
        else return a;
    }

    public static void main(String[] args) {
        ArrayVector obj = new ArrayVectorImpl();
        obj.set(0,12313);
        StringBuffer str = new StringBuffer("qwe");
        str.insert(0,"23");
        System.out.println(str);
        ArrayVectorImpl test = new ArrayVectorImpl();
        Calendar date = Calendar.getInstance();
        System.out.println(date.getTime());
        Calendar prevDate = Calendar.getInstance();
        date.set(1,Calendar.JANUARY,4);
    }
}
