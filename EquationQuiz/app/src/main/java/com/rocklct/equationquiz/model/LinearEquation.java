package com.rocklct.equationquiz.model;

/**
 * Created by rocklct on 2017/10/11.
 */

public class LinearEquation {
    int a, b;
    double root;

    public int getA() {
        return a;
    }

    public LinearEquation(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public LinearEquation(int a,int b,int root){
        this.a = a;
        this.b = b;
        this.root = root;
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

    public double getRoot() {
        return root;
    }

    public void setRoot(double root) {
        this.root = root;
    }
}
