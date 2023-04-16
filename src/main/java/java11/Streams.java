package java11;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Product {

    int id;
    String name;
    public Product(int id, String n){
        this.id = id;
        this.name = n;
    }

    public String getName(){
        return this.name;
    }
}

class Streams {

    static void streamCreation() {
        Stream<String> stream = Arrays.asList("a", "b", "c").stream();
        stream.forEach(System.out::println);
        System.out.println(Stream.empty().count());
        Stream<Integer> objectStream = Stream.<Integer>builder().add(1).add(2).add(3).build();
        objectStream.forEach(System.out::println);

        Stream<String> generate = Stream.<String>generate(new Supplier<String>() {
            @Override
            public String get() {
                return "hello";
            }
        }).limit(5);
        generate.forEach(System.out::println);
    }

    static void streamCreation2() {
        Stream<String> stream = Stream.of("a", "b", "c");
        List<Supplier<String>> collect = stream.map(x -> new Supplier<String>() {
            @Override
            public String get() {
                return "hello";
            }
        }).collect(Collectors.toList());
        collect.forEach(x -> System.out.println(x.get()));
    }

    static void stringStream() {
        "hello".chars().forEach(System.out::println);
    }

    static void filterStream() {
        Arrays.stream(new int [] {1,2,3,4,5}).filter(x -> x%2==0).forEach(System.out::println);
        Stream<String> stream = Arrays.stream(new String [] {"this","is", "good"}).filter( x -> x.contains("is"));

        stream.forEach(System.out::println);
        stream.count();
    }

    static void operations() {
        Stream.of("abc", "xyz", "pqr")
                .map(x -> x.toUpperCase())
                .filter(x-> x.contains("A"))
                .forEach(System.out::println);
    }

    static void reduce() {
        Optional<Integer> reduce = Stream.of(1, 2, 3)
                .reduce((x, y) -> x + y);
        System.out.println(reduce.isPresent());
    }

    static void collect() {
        List<Integer> collect = Stream.of(1, 2, 3, 4).collect(Collectors.toList());
        System.out.println(collect);
    }
    
    public static void main(String args[]) {
        //streamCreation();
        //stringStream();
        //filterStream();
        //operations();
        //reduce();
        //collect();
        streamCreation2();
    }

    public static void wasCalled() {

        String tst = "this is just a string";
        System.out.println(tst);
      //  System.out.println(tst.lines().count());

        List<Character> lst = new ArrayList<>();

        for(char c : tst.toCharArray()) {
            if(!lst.contains(c)) {
                lst.add(c);
            }
        }
        System.out.println(lst);

        List<Character> collect = tst.chars().mapToObj( c -> (char)c).distinct().collect(Collectors.toList());
        System.out.println(collect);

        System.out.println(Stream.empty().count());

        System.out.println( Arrays.asList(1,2,3,4).stream().count() );
        System.out.println( Stream.of(1,2,3,4).count() );

        System.out.println( Arrays.stream(new int[] {1,2,3}).count() );

        Stream<Integer> intStream = Stream.<Integer>builder().add(1).add(2).add(3).build();
        System.out.println(intStream.count());

        // System.out.println(intStream.findFirst());

        System.out.println( Stream.generate(() -> "voilla !!").limit(10).collect(Collectors.toList()) );

        System.out.println( Stream.iterate(0, n -> n+1).limit(10).filter(n -> n%2 == 0).collect(Collectors.toList()) );

        System.out.println(
                Arrays
                        .stream(new String[] {"abc", "def", "ghi", "xyz"})
                        .map( e-> e.subSequence(2, 3)).sorted()
                        .collect(Collectors.toList()));

        List<Integer> collect2 = Arrays.stream(new int[]{1,2,3,4}).mapToObj( e -> {
            wasCalled();
            return e+1;
        }).collect(Collectors.toList());
        System.out.println(collect2);

        long count = Arrays.stream(new int[]{1,2,3,4}).mapToObj( e -> {
            wasCalled();
            return e+1;
        }).count();
        System.out.println(count);

        OptionalInt reduce = IntStream.range(1, 5).reduce((a, b) -> a+b);
        System.out.println(reduce.getAsInt());

        int reduce2 = IntStream.range(1, 5).reduce(100, (a, b) -> a+b);
        System.out.println(reduce2);

        int reduce3 = Arrays.asList(1,2,3,4).parallelStream().reduce(100, (a, b) -> a+b, (a,b) -> {
            System.out.println("combiner");
            return a+b;
        });
        System.out.println(reduce3);

        List<Product> products = Arrays.asList(new Product(1,"as"), new Product(2, "bd"), new Product(3, "mn"));
        String collect3 = products.stream().map(Product::getName).collect(Collectors.joining(" | ", "{", "}"));
        System.out.println(collect3);
    }
}