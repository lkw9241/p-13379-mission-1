package com.ll;

import java.util.Arrays;

public class Calc {
    public static int run(String expr) {
        expr = expr.trim();
        expr = removeUnnecessaryBrackets(expr);
        expr = expr.replaceAll(" - ", " + -");

        if (expr.contains("(")) {
            String[] exprBits = splitTwoParts(expr);

            int sum = Arrays.stream(exprBits)
                    .map(Calc::run)
                    .reduce((a, b) -> a + b)
                    .orElse(0);

            return sum;
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

            int product = Arrays.stream(exprBits)
                    .map(Integer::parseInt)
                    .reduce((a, b) -> a * b)
                    .orElse(0);

            return product;
        }

        String[] exprBits = expr.split(" \\+ ");

        int sum = Arrays.stream(exprBits)
                .map(Integer::parseInt)
                .reduce((a, b) -> a + b)
                .orElse(0);

        return sum;
    }

    private static String[] splitTwoParts(String expr) {
        int bracketDepth = 0;

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (bracketDepth == 0 && c == '+') {
                return new String[]{expr.substring(0, i), expr.substring(i + 1)};
            } else if (c == '(') {
                bracketDepth++;
            } else if (c == ')') {
                bracketDepth--;
            }
        }

        throw new IllegalArgumentException("Invalid expression: " + expr);
    }

    private static String removeUnnecessaryBrackets(String expr) {
        if (expr.startsWith("(") && expr.endsWith(")")) {
            return removeUnnecessaryBrackets(expr.substring(1, expr.length() - 1));
        }

        return expr;
    }
}
