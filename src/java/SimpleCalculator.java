import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class SimpleCalculator {

    private static String toPostfix(String infixExpression) {
        StringBuilder postfixExpression = new StringBuilder();
        Stack<Operator> operatorStack = new Stack<>();
        StringBuilder operandDigits = new StringBuilder();
        for (int i = 0; i < infixExpression.length(); i++) {
            char ch = infixExpression.charAt(i);
            if (Character.isDigit(ch) || ch == '.') {
                operandDigits.append(ch);
            } else {
                if (Operator.isOperator(ch)) {
                    Operator operator = Operator.valueOf(ch);
                    if (operator == Operator.SUBTRACT) {
                        Character prev = null;
                        if (i > 0) {
                            prev = infixExpression.charAt(i - 1);
                        }
                        if (prev == null || (Operator.isOperator(prev) && prev != ')')) {
                            operator = Operator.UNARY_MINUS;
                        }
                    }
                    if (ch == '(') {
                        operatorStack.push(operator);
                    } else if (ch == ')') {
                        collectOperand(operandDigits, postfixExpression);
                        while (true) {
                            Operator o = operatorStack.pop();
                            if (o == Operator.OPEN_BRACKET) {
                                break;
                            }
                            postfixExpression.append(o.getSymbol());
                            postfixExpression.append(' ');
                        }
                    } else {
                        collectOperand(operandDigits, postfixExpression);
                        handleOperator(operator, operatorStack, postfixExpression);
                    }
                } else {
                    collectOperand(operandDigits, postfixExpression);
                }
            }
        }

        collectOperand(operandDigits, postfixExpression);

        if (!operatorStack.isEmpty()) {
            while (operatorStack.size() > 1) {
                postfixExpression.append(operatorStack.pop().getSymbol());
                postfixExpression.append(' ');
            }
            postfixExpression.append(operatorStack.pop().getSymbol());
        }

        return postfixExpression.toString();
    }

    private static void handleOperator(Operator operator, Stack<Operator> operatorStack,
                                       StringBuilder postfixExpression) {
        while (!operatorStack.isEmpty() &&
                operatorStack.peek() != Operator.OPEN_BRACKET &&
                operatorStack.peek().getPriority() >= operator.getPriority()) {
            Operator top = operatorStack.pop();
            postfixExpression.append(top.getSymbol());
            postfixExpression.append(' ');
        }
        operatorStack.push(operator);
    }

    private static void collectOperand(StringBuilder operand, StringBuilder postfixExpression) {
        if (operand.length() > 0) {
            postfixExpression.append(Double.valueOf(operand.toString()));
            postfixExpression.append(' ');
            operand.setLength(0);
        }
    }

    public enum Operator {
        ADD(0, '+') {
            @Override
            public double apply(double... a) {
                return a[0] + a[1];
            }
        },
        SUBTRACT(0, '-') {
            @Override
            public double apply(double... a) {
                return a[0] - a[1];
            }
        },
        MULTIPLY(1, '*') {
            @Override
            public double apply(double... a) {
                return a[0] * a[1];
            }
        },
        DIVIDE(1, '/') {
            @Override
            public double apply(double... a) {
                return a[0] / a[1];
            }
        },
        EXPONENT(2, '^') {
            @Override
            public double apply(double... a) {
                return Math.pow(a[0], a[1]);
            }
        },
        UNARY_MINUS(3, '~') {
            @Override
            public double apply(double... a) {
                return -a[0];
            }

            @Override
            public boolean isUnary() {
                return true;
            }
        },
        OPEN_BRACKET(3, '(') {
            @Override
            public double apply(double... a) {
                throw new UnsupportedOperationException();
            }
        },
        CLOSE_BRACKET(3, ')') {
            @Override
            public double apply(double... a) {
                throw new UnsupportedOperationException();
            }
        };

        private int priority;
        private char symbol;

        Operator(int priority, char symbol) {
            this.priority = priority;
            this.symbol = symbol;
        }

        public int getPriority() {
            return this.priority;
        }

        public char getSymbol() {
            return this.symbol;
        }

        public static Operator valueOf(char symbol) {
            Operator operator = operatorForSymbol(symbol);
            if (operator == null) {
                throw new IllegalArgumentException("unknown operator " + symbol);
            }
            return operator;
        }

        public static boolean isOperator(char ch) {
            return operatorForSymbol(ch) != null;
        }

        public boolean isUnary() {
            return false;
        }

        public abstract double apply(double... a);

        private static Operator operatorForSymbol(char symbol) {
            for (Operator operator : Operator.values()) {
                if (symbol == operator.getSymbol()) {
                    return operator;
                }
            }
            return null;
        }

    }

    public static double evaluatePostfix(String postfixExpression) {
        Scanner scanner = new Scanner(postfixExpression);
        Stack<Double> numbers = new Stack<>();
        while (scanner.hasNext()) {
            String token = scanner.next();
            if (token.length() == 1 && Operator.isOperator(token.charAt(0))) {
                Operator operator = Operator.valueOf(token.charAt(0));
                if (operator.isUnary()) {
                    Double operand = numbers.pop();
                    numbers.push(operator.apply(operand));
                } else {
                    Double operand2 = numbers.pop();
                    Double operand1 = numbers.pop();
                    numbers.push(operator.apply(operand1, operand2));
                }
            } else {
                numbers.push(Double.valueOf(token));
            }
        }
        return numbers.pop();
    }

    public static String format(Double value) {
        if (value == Math.floor(value) && !Double.isInfinite(value)) {
            return Integer.toString(value.intValue());
        }
        return rtrim(rtrim(String.format("%.5f", value), '0'), '.');
    }

    private static String rtrim(String s, char ch) {
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ch) {
            i--;
        }
        return s.substring(0, i + 1);
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                String infixExpression = scanner.nextLine();
                String postfixExpression = toPostfix(infixExpression);
                Double value = evaluatePostfix(postfixExpression);
                System.out.println(format(value));
            }
        }
    }

}


