package ru.netcrackeredu.SivaschenkoPavel.consoleCalc;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

class Pow implements BinaryOperator<BigDecimal> {

    @Override
    public BigDecimal apply(BigDecimal arg1, BigDecimal arg2) {
        return BigDecimal.valueOf(Math.pow(arg1.doubleValue(), arg2.doubleValue()));
    }

}
