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

    @Override
    public void log(String s) {
        System.out.println("overridden log from class1");
    }
}

public class DefaultStaticInterfacePractise {
    public static void main(String[] args) {
        class1 c1 = new class1();

        c1.log("hello");

        one.log2("hello");
    }
}