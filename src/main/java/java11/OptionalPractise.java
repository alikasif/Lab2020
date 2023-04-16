package java11;

import java.util.Optional;

public class OptionalPractise {
    public static void main(String[] args) {
        String s = null;
        System.out.println(Optional.ofNullable(s));
    }
}
