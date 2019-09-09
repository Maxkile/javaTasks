package ru.netcrackeredu.SivaschenkoPavel.consoleCalc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.BinaryOperator;

class Sum implements BinaryOperator<BigDecimal> {

    @Override
    public BigDecimal apply(BigDecimal aDouble, BigDecimal aDouble2) {
        return aDouble.add(aDouble2, MathContext.UNLIMITED);
    }
}


