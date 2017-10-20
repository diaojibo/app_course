package com.rocklct.equationquiz.helper;

import android.util.Log;

import com.rocklct.equationquiz.model.Equation;
import com.rocklct.equationquiz.model.LinearEquation;
import com.rocklct.equationquiz.model.QuadraticEquation;

import java.util.Random;

/**
 * Created by rocklct on 2017/10/11.
 */

public class EquationHelper {

    public static boolean areEqual(double a, double b) {
        if (Math.abs(a - b) < 1e-6) {
            return true;
        }
        return false;
    }

    public static boolean isIntegers(double d) {
        return areEqual(d, Math.round(d));
    }

    public static boolean upTwoDecimal(String s) {
        String splits[] = s.split("\\.");
        Log.d("decimal", splits[0]);
        if (splits.length == 2) {
            String decimals = splits[1];
            Log.d("decimal", splits[1]);
            if (decimals.length() > 2) {
                return true;
            }
        }
        return false;
    }

    public static boolean valueValidation(String s) {

        return false;
    }

    public static boolean isNumber(String s) {
        try {
            double test = Double.parseDouble(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public static int getRandomNumber(int lower, int upper) {
        return new Random().nextInt(upper - lower) + lower;
    }

    // to change a single integer p to ' + p' or ' - p'
    public static String formatOperateParamer(int x) {
        if (x == 0) return "";
        if (x > 0) {
            return " + " + Integer.toString(x);
        }
        return " - " + Integer.toString(-x);
    }

    public static String getLinearEquationString(LinearEquation e) {
        int a = e.getA();
        int b = e.getB();

        String sa = Integer.toString(a);
        if (sa.equals("1")) {
            sa = "";
        }

        if (sa.equals("-1")) {
            sa = "-";
        }
        return sa + "x" + formatOperateParamer(b) + " = 0";
    }

    public static boolean areEqualRoots(double x, double y, QuadraticEquation e) {
        if (areEqual(x, e.getRoot1()) && areEqual(y, e.getRoot2())) return true;
        if (areEqual(x, e.getRoot2()) && areEqual(y, e.getRoot1())) return true;
        return false;
    }

    public static String getQuadraticEquationString(QuadraticEquation e) {
        int a = e.getA();
        int b = e.getB();
        int c = e.getC();

        String sa = Integer.toString(a);
        if (sa.equals("1")) {
            sa = "";
        }

        if (sa.equals("-1")) {
            sa = "-";
        }

        String eq = sa + "x^2";
        if (b != 0) {
            String sb = formatOperateParamer(b);
            if (sb.substring(sb.length() - 1).equals("1")) {
                sb = sb.substring(0, sb.length() - 1);
            }
            eq = eq + sb + "x";
        }
        return eq + formatOperateParamer(c) + " = 0";
    }

    public static LinearEquation generateLinearEquation() {
        while (true) {
            int a = getRandomNumber(-99, 99);
            int b = getRandomNumber(-99, 99);
            if (a != 0) {
                LinearEquation e = new LinearEquation(a, b);
                double ax = a;
                double bx = b;
                double root = Math.round((-bx / ax) * 100) / 100d;
                e.setRoot(root);
                return e;
            }
        }
    }

    public static QuadraticEquation generateQuadraticEquation() {
        while (true) {
            int a = getRandomNumber(-99, 99);
            int b = getRandomNumber(-99, 99);
            int c = getRandomNumber(-99, 99);
            int delta = b * b - 4 * a * c;
            if (a != 0 && delta >= 0) {
                QuadraticEquation e = new QuadraticEquation(a, b, c);
                double root1, root2;
                root1 = (-b + Math.sqrt(delta)) / (2 * a);
                if (delta == 0) root2 = root1;
                else root2 = (-b - Math.sqrt(delta)) / (2 * a);
                e.setRoot1(Math.round(root1 * 100) / 100d);
                e.setRoot2(Math.round(root2 * 100) / 100d);
                return e;
            }
        }
    }


}
