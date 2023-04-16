package uber;

import java.util.*;

class Pair4{
    int pid;
    boolean start;
    int startTime;

    public Pair4 (int x, boolean s, int z) {
        pid =x;
        start = s;
        startTime =z;
    }
}

public class ExecutionTime {

    public static void main(String[] args) {
        List<Pair4> lst = new ArrayList<>();
        lst.add(new Pair4(0, true, 0));
        lst.add(new Pair4(1, true, 2));
        lst.add(new Pair4(1, true, 5));
        lst.add(new Pair4(1, false, 7));
        lst.add(new Pair4(2, true, 8));
        lst.add(new Pair4(2, false, 10));
        lst.add(new Pair4(1, false, 11));
        lst.add(new Pair4(0, false, 14));

        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> ended = new HashSet<>();

        Pair4 prev = lst.get(0);
        map.put(prev.pid, 0);

        if(!prev.start)
            ended.add(prev.pid);

        for(int i=1; i<lst.size(); i++) {

            Pair4 tmp = lst.get(i);
            int et = tmp.startTime - prev.startTime;

            if(!ended.contains(prev.pid)) {
                Integer time = map.get(prev.pid);

                if (time == null) {
                    map.put(prev.pid, et);
                } else {
                    map.put(prev.pid, map.get(prev.pid) + et);
                }
            }
            //map.put(tmp.pid);
        }
        System.out.println(map);
    }
}
