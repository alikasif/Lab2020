package google2022;

import leetcode.Node;

public class PalindromeSLL {
    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(0);
        head.next.next = new Node(0);
        //head.next.next.next = new Node(1);


        Node f = head;
        Node s = head;
        Node t = null;

        while (f.next != null){
            t = s;
            s = s.next;
            f = f.next;
            if(f != null)
              f = f.next;
        }

        System.out.println(s +" "+f + " "+s.next);
        s.next = null;

        Node p = head;
        Node c = head.next;
        head.next = null;

        while ( c != null) {
            Node t1 = c.next;
            c.next = p;
            p =c;
            c=t1;
        }

       System.out.println(p +" "+ p.next +" " );

        while ( p != null && s!= null) {
            if ( p.value != s.value) {
                System.out.println("not plindrome");
                return;
            }
            else {
                p = p.next;
                s = s.next;
            }
        }

        if(  (p == null && s!=null) || (p !=null && s==null))
            System.out.println("not");

        System.out.println("palndrome");
    }
}
