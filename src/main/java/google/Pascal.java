package google;

import java.util.ArrayList;
import java.util.List;

public class Pascal {

    public static void main(String[] args) {

        List<List<Integer>> rows = new ArrayList<>();
        for(int i=0; i< 10; i++) {

            List<Integer> l = new ArrayList<>();

            if (i==0){
                l.add(1);
            }
            else if (i==1) {
                l.add(1);
                l.add(1);
            }
            else {
                for(int j=0; j<=i; j++) {
                    if (j==0 || j == i) {
                        l.add(1);
                    }
                    else {
                        List<Integer> prevRow = rows.get(i - 1);
                        l.add(prevRow.get(j) + prevRow.get(j-1));
                    }
                }
            }
            rows.add(l);
        }
        rows.stream().forEach(System.out::println);
    }
}
