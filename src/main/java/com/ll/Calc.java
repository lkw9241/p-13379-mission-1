package com.ll;

import java.util.Arrays;

public class Calc {
    private static boolean isDebug = true;

    public static int run(String expr) {
        return _run(expr, 0);
    }

    public static int _run(String expr, int depth) {
        expr = expr.trim();

        if (isDebug) {
            System.out.print("  ".repeat(depth) + "expr[" + depth + "] { raw : " + expr);
        }

        expr = removeUnnecessaryBrackets(expr);
        expr = expr.replaceAll(" - ", " + -");

        if (isDebug) {
            System.out.println(", clean : " + expr + " }");
        }

        if (expr.contains("(")) {
            String[] exprBits = splitTwoPartsBy(expr, '+');

            if (exprBits != null) {
                return _run(exprBits[0], depth + 1) + _run(exprBits[1], depth + 1);
            }

            exprBits = splitTwoPartsBy(expr, '*');

            if (exprBits != null) {
                return _run(exprBits[0], depth + 1) * _run(exprBits[1], depth + 1);
            }

            throw new IllegalArgumentException("Invalid expression: " + expr);
        }

        if (expr.contains(" * ") && expr.contains(" + ")) {
            String[] exprBits = expr.split(" \\+ ");

            return Arrays.stream(exprBits)
                    .map(exprBit -> _run(exprBit, depth + 1))
                    .reduce((a, b) -> a + b)
                    .orElse(0);
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
