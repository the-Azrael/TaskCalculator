import java.util.function.*;

public class Calculator {
    @FunctionalInterface
    interface ICalculator {
        public double calculate(double a, double b);
    }
    static Supplier<Calculator> instance = Calculator::new;
    BinaryOperator<Integer> plus = (x, y) -> x + y ;
    BinaryOperator<Integer> minus = (x, y) -> x - y ;
    BinaryOperator<Integer> multiply = (x, y) -> x * y ;
    BinaryOperator<Integer> divide = (x, y) -> x / y ;
    UnaryOperator<Integer> pow = x -> x * x;
    UnaryOperator<Integer> abs = x -> x > 0 ? x : x * -1;
    Predicate<Integer> isPositive = x -> x > 0;
    Consumer<Integer> println = System.out::println;
    Consumer<Boolean> printBool = System.out::println;
    Consumer<String> printString = System.out::println;

    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();
        int mul = calc.multiply.apply(23, 22);
        calc.println.accept(mul);
        int div = calc.divide.apply(22, 11);
        calc.println.accept(div);
        int pow = calc.pow.apply(2);
        calc.println.accept(pow);
        int abs = calc.abs.apply(-12);
        calc.println.accept(abs);
        boolean pos = calc.isPositive.test(-4);
        calc.printBool.accept(pos);

        int a = calc.plus.apply(1, 2);      // = 3
        int b = calc.minus.apply(1, 1);     // = 0
        //int c = calc.divide.apply(a, b);    // = 3/0 - ошибка деления на 0
        int c;
        if (calc.isPositive.test(b)) {
            c = calc.divide.apply(a, b);
            calc.println.accept(c);
        } else {
            calc.printString.accept("Делитель меньше 0");
        }
    }
}

