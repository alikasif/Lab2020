package google2024.hard;

import java.util.*;

class StackElement {
    int freq;
    int order;
    int val;

    public StackElement(int freq, int order, int val) {
        this.freq = freq;
        this.order = order;
        this.val = val;
    }

    @Override
    public String toString() {
        return "StackElement{" +
                "freq=" + freq +
                ", order=" + order +
                ", val=" + val +
                '}';
    }
}

public class FrequentStack {
    Map<Integer, Integer> freqMap = new HashMap<>();
    Queue<StackElement> stack = new PriorityQueue(new Comparator<StackElement>() {
        @Override
        public int compare(StackElement o1, StackElement o2) {

            if(o1.freq == o2.freq)
                return o2.order-o1.order;

            return o2.freq - o1.freq;
        }
    });

    void push(int val, int order) {
        freqMap.put(val, freqMap.getOrDefault(val, 0)+1);
        stack.offer(new StackElement(freqMap.get(val), order, val));
    }

    int pop() {
        StackElement poll = stack.poll();
        freqMap.put(poll.val, freqMap.getOrDefault(poll.val, 0)-1);
        return poll.val;
    }

    void print() {
        while (!stack.isEmpty()) {
            System.out.println(stack.poll());
        }
    }

    public static void main(String[] args) {

        FrequentStack frequentStack = new FrequentStack();
        frequentStack.push(5, 1);
        frequentStack.push(7, 2);
        frequentStack.push(5, 3);
        frequentStack.push(7, 4);
        frequentStack.push(4, 5);
        frequentStack.push(5, 6);
        frequentStack.print();

        System.out.println(frequentStack.pop());
        System.out.println(frequentStack.pop());

    }
}
