package ru.netcrackeredu.SivaschenkoPavel.consoleCalc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BinaryOperator;

import static ru.netcrackeredu.SivaschenkoPavel.consoleCalc.ScaleConstants.DIVISION_SCALE;

class Division implements BinaryOperator<BigDecimal> {

    @Override
    public BigDecimal apply(BigDecimal aDouble, BigDecimal aDouble2) throws ArithmeticException {
        return aDouble.divide(aDouble2, ScaleConstants.DIVISION_SCALE, RoundingMode.HALF_UP);
    }
}