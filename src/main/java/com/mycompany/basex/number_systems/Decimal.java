/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.basex.number_systems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class Decimal {

    private String decimal;

    public Decimal(String decimal) {
        this.decimal = decimal;
    }

    public String getDecimal() {
        return decimal;
    }

    public void setDecimal(String decimal) {
        this.decimal = decimal;
    }

    public String decToBin() {
        BigInteger dec = new BigInteger(getDecimal());
        return dec.toString(2);
    }

    public Object[] decToBinSolution() {
        ArrayList<Object[]> rows = new ArrayList<>();
        BigInteger dec = new BigInteger(getDecimal());
        StringBuilder bin = new StringBuilder();
        while (dec.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] result = dec.divideAndRemainder(BigInteger.valueOf(2));
            BigInteger quotient = result[0];
            BigInteger remainder = result[1];
            bin.append(remainder);
            rows.add(new Object[]{dec + " / 2", quotient, remainder});
            dec = quotient;
        }
        return rows.toArray(new Object[rows.size()][]);
    }

}
