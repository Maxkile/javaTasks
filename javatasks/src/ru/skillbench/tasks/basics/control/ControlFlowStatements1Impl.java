package ru.skillbench.tasks.basics.control;

public class ControlFlowStatements1Impl implements ControlFlowStatements1 {

    private static final int N = 8;
    private static final int M = 5;
    private static final int START_DEPOSIT = 1000;
    private static final int FINAl_DEPOSIT = 5000;
    @Override
    public float getFunctionValue(float x) {
        if (x > 0) return (float)(2.0*Math.sin(x));
        else return (6 - x);
    }

    @Override
    public String decodeWeekday(int weekday) {
        switch (weekday){
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
            default:
                return "No appropriate weekday found";
        }
    }

    @Override
    public int[][] initArray() {
        int[][] matrix = new int[N][M];
        for(int i = 0;i<N;++i){
            for(int j = 0;j<M;++j){
                matrix[i][j] = i*j;
            }
        }
        return matrix;
    }

    @Override
    public int getMinValue(int[][] array) {
        int min = array[0][0];
        for(int i = 0;i<array.length;++i){
            for(int j = 0;j<array[i].length;++j){
                if (array[i][j] < min) min = array[i][j];
            }
        }
        return min;
    }

    @Override
    public BankDeposit calculateBankDeposit(double P) {
        BankDeposit deposit = new BankDeposit();
        deposit.amount = START_DEPOSIT;
        while(deposit.amount <= 5000){
            deposit.years++;
            deposit.amount = deposit.amount*(1 + P/100);
        }
        return deposit;
    }

    public static void main(String[] args){
        ControlFlowStatements1Impl obj =  new ControlFlowStatements1Impl();
        int [][] arr = obj.initArray();
    }
}
