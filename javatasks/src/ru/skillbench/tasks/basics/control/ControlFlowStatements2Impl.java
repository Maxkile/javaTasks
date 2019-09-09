package ru.skillbench.tasks.basics.control;

import java.util.HashMap;
import java.util.Map;

public class ControlFlowStatements2Impl implements ControlFlowStatements2 {

    public static int N = 5;
    public static int M = 8;

    @Override
    public int getFunctionValue(int x) {
        if ((x<-2) || (x>2)){
            return 2*x;
        }
        else return -3*x;
    }

    @Override
    public String decodeMark(int mark) {
        if ((mark > 5) || (mark < 1)) return "Error";
        else{
            Map<Integer,String> dict = new HashMap<Integer,String>();
            dict.put(1,"Fail");
            dict.put(2,"Poor");
            dict.put(3,"Satisfactory");
            dict.put(4,"Good");
            dict.put(5,"Excellent");
            return dict.get((Integer)mark);
        }

    }

    @Override
    public double[][] initArray() {
        double[][] matrix = new double[N][M];
        for(int i = 0;i<N;++i){
            for (int j = 0;j<M;++j){
                matrix[i][j] = (Math.pow(i,4) - Math.sqrt(j));
            }
        }
        return matrix;
    }

    @Override
    public double getMaxValue(double[][] array) {
        double max = array[0][0];
        for(int i = 0;i<array.length;++i){
            for(int j = 0;j<array[i].length;++j){
                if (array[i][j] > max) max = array[i][j];
            }
        }
        return max;
    }

    @Override
    public Sportsman calculateSportsman(float P) {
        float dist = 10;
        Sportsman man = new Sportsman();
        while(man.getTotalDistance() <= 200){
            man.addDay(dist);
            dist = dist*(1 + P/100);
        }
        return man;
    }

    public static void main(String[] args) {
        ControlFlowStatements2Impl obj = new ControlFlowStatements2Impl();
        System.out.print(obj.calculateSportsman(100));
    }
}
