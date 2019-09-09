package ru.netcrackeredu.SivaschenkoPavel.consoleCalc;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

class Multiplying implements BinaryOperator<BigDecimal> {

    @Override
    public BigDecimal apply(BigDecimal aDouble, BigDecimal aDouble2) {
        return aDouble.multiply(aDouble2);
    }
}
