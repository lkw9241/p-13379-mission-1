package com.ll;

import java.util.Arrays;

public class Calc {
    public static int run(String expr) {
        expr = expr.replaceAll(" - ", " + -");

        String[] exprBits = expr.split(" \\+ ");

        int sum = Arrays.stream(exprBits)
                .map(Integer::parseInt)
                .reduce((a, b) -> a + b)
                .orElse(0);

        return sum;
    }
}
