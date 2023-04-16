package java11;

interface one {
    default void log(String s) {
        System.out.println("default " +s);
    }
    static void log2(String s) {
        System.out.println("static " + s);
    }
}

class class1 implements one {

}

public class DefaultStaticInterfacePractise {
    public static void main(String[] args) {
        new class1().log("hello");

        one.log2("hello");
    }
}