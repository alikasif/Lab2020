package fb;

public class Util {


    public static void printSLL(Node head) {

        while (head != null) {
            System.out.print(head +" -> ");
            head = head.next;
        }
        System.out.println();
    }
}
