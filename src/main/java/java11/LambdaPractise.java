package java11;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaPractise {
    public static void main(String[] args) {
        // testFunction();
         testConsumer();
        // testSupplier();
        // testPredicate();
    }

    static void testPredicate() {
        Predicate<Integer> predicate = x -> x%2==0;
        Predicate<Integer> predicate5 = x -> x>5;
        List<Integer> collect = List.of(1, 2, 3,30,12,43,44).stream().filter(predicate.and(predicate5)).collect(Collectors.toList());
        System.out.println(collect);

        List<Integer> collect2 = List.of(1, 2, 3,30,12,43,44).stream().filter(predicate.or(predicate5)).collect(Collectors.toList());
        System.out.println(collect2);

    }
    static void testSupplier() {
        Supplier<Double> supplier = () -> Math.random() % 2.0;
        System.out.println(supplier.get());
        List.of(1.0, 2.0, 3.0).forEach( x -> System.out.println(x * supplier.get()));
        List<Double> collect = List.of(1.0, 2.0, 3.0).stream().map(x -> x * supplier.get()).collect(Collectors.toList());
        System.out.println(collect);
    }

    static void testConsumer(){
        Consumer<Integer> consumer = x -> System.out.println(x*x);
        Consumer<Integer> consumer2 = x -> System.out.println(2*x);
        List.of(1,2,3,4).forEach(consumer.andThen(consumer2).andThen(consumer).andThen(consumer2));
        System.out.println("*****");
        List.of(1,2,3).forEach(consumer);
    }

    static void testFunction(){
        var integers = List.of(1, 2, 3, 4, 5);
        System.out.println(integers);

        Function<Integer, Integer> integerIntFunction = (Integer x) -> {
            int y = x * x;
            // System.out.println(y);
            return y;
        };

        Stream<Integer> integerStream = integers.stream().map(integerIntFunction);
        integerStream.forEach(x-> System.out.println(x));

        Function<Integer, String> namingFunction = x -> String.valueOf(x + "X");
        List<String> names = integers.stream().map(namingFunction).collect(Collectors.toList());
        System.out.println(names);

        // System.out.println(collect);

        integers.forEach(x -> integerIntFunction.apply(x));
    }
}