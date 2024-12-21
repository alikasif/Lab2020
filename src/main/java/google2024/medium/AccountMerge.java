package google2024.medium;

import java.util.*;

public class AccountMerge {
    public static void main(String[] args) {

        Set<Integer> set = new HashSet<>();
        set.add(1); set.add(2);

        Set<Integer> set2 = new HashSet<>();
        set2.add(5);

        Set<Integer> set3 = new HashSet<>();
        set3.add(4); set3.add(1);

        Set<Integer> set4 = new HashSet<>();
        set4.add(6);

        List<Set<Integer>> list = new ArrayList<>();
        list.add(set2); list.add(set); list.add(set3); list.add(set4);

        Set<Integer> visited = new HashSet<>();
        visited.addAll(set); visited.addAll(set2); visited.addAll(set3); visited.addAll(set4);

        int i = 0;
        while (!visited.isEmpty()) {

            Set<Integer> set1 = list.remove(0);

            Set<Integer> resultSet = new HashSet<>();
            boolean found = false;

            for(Integer x : set1) {
                if(!visited.contains(x))
                    continue;
                visited.remove(x);
                found = true;
                for(Set<Integer> tmpset : list) {

                    if (tmpset.contains(x)) {
                        resultSet.addAll(set1);
                        resultSet.addAll(tmpset);
                        visited.removeAll(set1);
                        visited.removeAll(tmpset);
                    }

                }
            }

            if(!resultSet.isEmpty()) {
                list.add(resultSet);
            }
            else if (found && resultSet.isEmpty()) {
                list.add(set1);
            }

        }
        System.out.println(list);
    }
}
