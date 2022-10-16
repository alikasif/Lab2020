package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ValidBT {

    public static void main(String[] args) {

        int[] lchild = {1, -1, -1, 4, -1, -1};
        int[] rchild = {2, -1, -1, 5, -1, -1};
        int n = 6;
//
//        int[] lchild = {1, -1, 3, -1};
//        int[] rchild = {2, -1, -1, -1};
//        int n = 4;
//

//        int[] lchild = {1, -1, 3, -1};
//        int[] rchild = {2, 3, -1, -1};
//        int n = 4;
//
//        int[] lchild = {3, -1, 1, -1};
//        int[] rchild = {-1, -1, 0, -1};
//        int n=4;
//
//        int[] lchild = {1, 0};
//        int[] rchild = {-1, -1};
//        int n = 2;

        validateBT(lchild, rchild, n);
    }

    static void validateBT(int[] lchild, int[] rchild, int n) {

        int[] indegree = new int[n];
        int i= 0;

        while( i<n ) {
            if(lchild[i] != -1) {
                indegree[lchild[i]]++;
                if (indegree[lchild[i]] > 1) {
                    System.out.println("loop..");
                    break;
                }
            }

            if(rchild[i] != -1) {
                indegree[rchild[i]]++;
                if (indegree[rchild[i]] > 1) {
                    System.out.println("loop..");
                    break;
                }
            }
            i++;
        }

        i=0;
        int root =-1;
        while( i < n ) {
            if(indegree[i] == 0) {
               if( root == -1)
                   root = i;
               else {
                   System.out.println("more than 1 root...forest");
                   break;
               }
            }
            i++;
        }

        if(root == -1) {
            System.out.println("no root found");
        }

    }

    static void sol1(int[] rchild, int[] lchild, int n) {

        int i = 0;
        List<Integer> lst = new ArrayList<>();
        List<Integer> visited = new ArrayList<>();
        lst.add(0);
        visited.add(0);

        while ( i < n ) {
            System.out.println(lst);

            if (!lst.contains(i)) {
                System.out.println("invalid tree " + i);
                break;
            }
            else {
                lst.remove(new Integer(i));
                if( visited.contains(lchild[i]) || visited.contains(rchild[i])) {
                    System.out.println("invalid tree at lchild " + lchild[i] + " or "+ rchild[i]);
                    break;
                }
                if(!lst.contains(new Integer(lchild[i])) && lchild[i] != -1 )
                    lst.add(lchild[i]);
                else {
                    if(lchild[i] != -1) {
                        System.out.println("invalid tree at lchild " + lchild[i]);
                        break;
                    }
                }
                if(rchild[i] != -1 && !lst.contains(new Integer(rchild[i])))
                    lst.add(rchild[i]);
                else {
                    if (rchild[i] != -1) {
                        System.out.println("invalid tree at rchild " + rchild[i]);
                        break;
                    }
                }
            }
            i++;
        }
    }

}
