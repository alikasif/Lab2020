package leetcode;

public class FlattenMultiLevelLL {

    public static void main(String[] args) {

        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        Node nine = new Node(9);
        Node ten = new Node(10);
        Node eleven = new Node(11);
        Node twelve = new Node(12);

        one.next = two;
        two.prev = one;
        two.next = three;
        three.prev = two;
        three.next = four;
        four.prev = three;
        four.next = five;
        five.prev = four;
        five.next = six;
        six.prev = five;

    }

}
