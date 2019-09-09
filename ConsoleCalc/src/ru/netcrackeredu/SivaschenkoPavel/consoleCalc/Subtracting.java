package ru.netcrackeredu.SivaschenkoPavel.consoleCalc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.BinaryOperator;

class Subtracting implements BinaryOperator<BigDecimal> {

    @Override
    public BigDecimal apply(BigDecimal aDouble, BigDecimal aDouble2) {
        return aDouble.subtract(aDouble2, MathContext.UNLIMITED);
    }
}
