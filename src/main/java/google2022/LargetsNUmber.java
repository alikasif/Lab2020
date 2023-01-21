package google2022;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class MYComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return -1*Double.valueOf(o1+o2).compareTo(Double.valueOf(o2+o1));
    }
}

public class LargetsNUmber {
    public static void main(String[] args) {
        int [] arr = {3,30,34,5,9};

        List<String> lst= new ArrayList<>();
        for(int x : arr)
            lst.add(String.valueOf(x));
        Collections.sort(lst, new MYComparator());
        System.out.println(lst);
    }
}
