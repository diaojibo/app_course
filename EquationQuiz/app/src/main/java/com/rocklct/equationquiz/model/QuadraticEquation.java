package com.rocklct.equationquiz.model;

/**
 * Created by rocklct on 2017/10/11.
 */

public class QuadraticEquation {
    int a, b, c;
    double root1, root2;

    public QuadraticEquation(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public double getRoot1() {
        return root1;
    }

    public void setRoot1(double root1) {
        this.root1 = root1;
    }

    public double getRoot2() {
        return root2;
    }

    public void setRoot2(double root2) {
        this.root2 = root2;
    }
}
