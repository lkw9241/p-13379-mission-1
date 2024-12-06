package com.ll;

import java.util.Arrays;

public class Calc {
    public static int run(String expr) {
        expr = expr.trim();
        expr = removeUnnecessaryBrackets(expr);
        expr = expr.replaceAll(" - ", " + -");

        if (expr.contains("(")) {
            String[] exprBits = splitTwoPartsBy(expr, '+');

            if (exprBits != null) {
                return run(exprBits[0]) + run(exprBits[1]);
            }

            exprBits = splitTwoPartsBy(expr, '*');

            if (exprBits != null) {
                return run(exprBits[0]) * run(exprBits[1]);
            }

            throw new IllegalArgumentException("Invalid expression: " + expr);
        }

        if (expr.contains(" * ") && expr.contains(" + ")) {
            String[] exprBits = expr.split(" \\+ ");

            int sum = Arrays.stream(exprBits)
                    .map(Calc::run)
                    .reduce((a, b) -> a + b)
                    .orElse(0);

            return sum;
        }

        if (expr.contains(" * ")) {
            String[] exprBits = expr.split(" \\* ");

            return Arrays.stream(exprBits)
                    .map(Integer::parseInt)
                    .reduce((a, b) -> a * b)
                    .orElse(0);
        }

        String[] exprBits = expr.split(" \\+ ");

        return Arrays.stream(exprBits)
                .map(Integer::parseInt)
                .reduce((a, b) -> a + b)
                .orElse(0);
    }

    private static String[] splitTwoPartsBy(String expr, char splitBy) {
        int bracketDepth = 0;

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (bracketDepth == 0 && c == splitBy) {
                return new String[]{expr.substring(0, i), expr.substring(i + 1)};
            } else if (c == '(') {
                bracketDepth++;
            } else if (c == ')') {
                bracketDepth--;
            }
        }

        return null;
    }

    private static String removeUnnecessaryBrackets(String expr) {
        if (expr.startsWith("(") && expr.endsWith(")")) {
            return removeUnnecessaryBrackets(expr.substring(1, expr.length() - 1));
        }

        return expr;
    }
}
