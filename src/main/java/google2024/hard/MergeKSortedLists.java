package google2024.hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Pointer {

    int listId; int index; Integer val;
    public Pointer(int listId, int index, Integer val) {
        this.listId = listId;
        this.index = index;
        this.val = val;
    }

    @Override
    public String toString() {
        return "Pointer{" +
                "listId=" + listId +
                ", index=" + index +
                ", val=" + val +
                '}';
    }
}
public class MergeKSortedLists {
    public static void main(String[] args) {
        PriorityQueue<Pointer> priorityQueue = new PriorityQueue<>(3, new Comparator<Pointer>() {
            @Override
            public int compare(Pointer o1, Pointer o2) {
                return o1.val.compareTo(o2.val);
            }
        });


        int[][] arr = {{1, 4, 5}, {1, 3, 4}, {2, 6}};

        priorityQueue.add(new Pointer(0, 0, arr[0][0]));
        priorityQueue.add(new Pointer(1, 0, arr[1][0]));
        priorityQueue.add(new Pointer(2, 0, arr[2][0]));

        int maxLength = 3;
        System.out.println(priorityQueue);
        List<Integer> sortedList = new ArrayList<>();

        while (!priorityQueue.isEmpty()) {
            Pointer poll = priorityQueue.poll();
            sortedList.add(poll.val);

            if (poll.index == arr[poll.listId].length-1) {
                continue;
            }

            priorityQueue.offer(new Pointer(poll.listId, poll.index+1, arr[poll.listId][poll.index+1]));
        }

        System.out.println(sortedList);
    }
}
