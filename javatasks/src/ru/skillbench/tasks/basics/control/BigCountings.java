package ru.skillbench.tasks.basics.control;

import java.math.BigDecimal;

public class BigCountings {
    public static void main(String[] args) {
       java.math.BigDecimal i = new java.math.BigDecimal(2.00);
       java.math.BigDecimal j = new java.math.BigDecimal(1.10);
       java.math.BigDecimal res = i.add(j.negate());
       java.math.BigDecimal result = res.setScale(2, BigDecimal.ROUND_CEILING);
       System.out.println(result);
    }
}