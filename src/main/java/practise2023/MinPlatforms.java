package practise2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class MinPlatforms {
    public static void main(String[] args) {

        List<Integer> arrivals = Arrays.asList(900, 940, 950, 1100, 1500, 1800);
        List<Integer> departures = Arrays.asList(910, 1200, 1120, 1130, 1900, 2000);

        List<Integer> all = new ArrayList<>();
        all.addAll(arrivals); all.addAll(departures);
        Collections.sort(all);
        int i=0;
        int pc=0;
        int mpc=0;
        while (i < all.size()) {
            if(arrivals.contains(all.get(i))) {
                pc++;
            }
            else
                pc--;
            mpc = Math.max(pc, mpc);
            i++;

        }
        System.out.println(mpc);

    }
}