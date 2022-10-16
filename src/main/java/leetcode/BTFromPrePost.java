package leetcode;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BTFromPrePost {

    int pre_idx;
    int[] pre;
    int[] post;
    Map<Integer, Integer> post_map = new HashMap<>();

    public static void main(String[] args) {

        BTFromPrePost obj = new BTFromPrePost();
        int[] pre = new int[]{1, 2, 4, 5, 3, 6, 7};
        int[] post = new int[]{4, 5, 2, 6, 7, 3, 1};

//        int[] pre = new int[]{2, 1, 3};
//        int[] post = new int[]{1, 3, 2};
//
        Map<Integer, Integer> e2idxmap = new HashMap<>();

        for (int i = 0; i < post.length; i++)
            e2idxmap.put(post[i], i);
        System.out.println(e2idxmap);

        Node node = obj.constructFromPrePost(pre, e2idxmap, 0, post.length - 1, new AtomicInteger(0));

        List<Node> queue = new ArrayList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node t = queue.get(queue.size()-1);
            queue.remove(t);
            System.out.print(t + " -> ");

            if(t.left != null)
                queue.add(t.left);
            if(t.right != null)
                queue.add(t.right);
        }
        //System.out.println(node + " " + node.left + " " + node.right);
    }

    static Node constructFromPrePost(int[] pre, Map<Integer, Integer> e2idxmap, int start, int end, AtomicInteger index) {

        if(start > end)
            return null;

        Node root = new Node(pre[index.get()]);
        index.incrementAndGet();

        if(start == end || index.get() == pre.length)
            return root;

        int postIndex = e2idxmap.get(pre[index.get()]); // get index of left element in post

        root.left = constructFromPrePost(pre, e2idxmap, start, postIndex, index);
        root.right = constructFromPrePost(pre, e2idxmap, postIndex+1, end-1, index);

        return root;
    }


}