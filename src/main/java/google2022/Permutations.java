package google2022;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public static void main(String[] args) {

        int[] nums = {1,2,3};

    }

    static void pemt(List<Integer> lst) {
        for(int t : lst) {
            List<Integer> tmp = new ArrayList<>(lst);
            tmp.remove(t);
        }
    }
}
