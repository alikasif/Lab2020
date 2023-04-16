package java11;


import com.codahale.metrics.Gauge;

import java.util.List;
import java.util.stream.Collectors;

interface Sayable{
    void say();
}

class TestMethodReference{

    String s1;
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

    String smallCase(String s){
        return s.toLowerCase();
    }
}
public class MethodReference {
    public static void main(String[] args) {
        List<String> strings = List.of("a", "b", "c").stream().map(TestMethodReference::capitalize).collect(Collectors.toList());
        System.out.println(strings);

        List<TestMethodReference> strings2 = List.of("a", "b", "c").stream().map(TestMethodReference::new).collect(Collectors.toList());
        System.out.println(strings2);
    }
}