package java11;

import java.util.List;
import java.util.stream.Collectors;

class TestMethodReference{

    String s1;

    public TestMethodReference(){

    }

    public TestMethodReference(String s) {
        s1=s;
    }

    @Override
    public String toString() {
        return s1;
    }

    static String capitalize(String s){
        return s.toUpperCase();
    }

    public String smallCase(String s){
        return s.toLowerCase();
    }
}

public class MethodReference {
    public static void main(String[] args) {
        List<String> strings = List.of("a", "b", "c").stream().map(TestMethodReference::capitalize).collect(Collectors.toList());
        System.out.println(strings);

        List<TestMethodReference> instances = List.of("a", "b", "c").stream().map(TestMethodReference::new).collect(Collectors.toList());
        System.out.println(instances);

        final TestMethodReference testMethodReference = new TestMethodReference();

        List<String> strings1 = List.of("a", "B", "C").stream().map(testMethodReference::smallCase).collect(Collectors.toList());
        System.out.println(strings1);
    }
}