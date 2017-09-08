package com.andrew.JunitTest.test;

public class MathImpl implements IMath {

    @Override
    public int getSum(int num1, int num2) {
        return num1 + num2;
    }

    @Override
    public int divide(int num1, int num2) {
        return num1 / num2;
    }
}
