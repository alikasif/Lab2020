package google2024.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class QueueReconstructionByHeight {
    public static void main(String[] args) {

        int[][] people = {
                {7,0},
                {4,4},
                {7,1},
                {5,0},
                {6,1},
                {5,2}
        };

        for(int i=0; i<people.length; i++)
            System.out.print(Arrays.toString(people[i]) + " => ");
        System.out.println();
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] x, int[] y) {
                return Integer.compare(y[0], x[0]);
            }
        });
        printArr(people);
        System.out.println();
        List<int[]> list = new ArrayList<>();
        for(int i=0; i< people.length; i++) {
            list.add(people[i][1], people[i]);
            printList(list);
        }
    }
    static void printArr(int[][] people) {
        System.out.println();
        for(int i=0; i<people.length; i++)
            System.out.print(Arrays.toString(people[i]) + " => ");
    }

    static void printList(List<int[]> list) {
        System.out.println();
        for(int[] x: list) {
            System.out.print(Arrays.toString(x) +" => ");
        }
    }

}
