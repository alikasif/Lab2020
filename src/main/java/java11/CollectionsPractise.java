package java11;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsPractise {

    static void unmodifiableList() {
        List<Integer> integers = Collections.unmodifiableList(Arrays.asList(1, 2, 3));
        integers.add(4);
    }


    public static void main(String[] args) {

        unmodifiableList();
    }
}
