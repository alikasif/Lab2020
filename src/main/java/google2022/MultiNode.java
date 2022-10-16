package google2022;

import java.util.ArrayList;
import java.util.List;

public class MultiNode {

    int value;
    List<MultiNode> children;

    public MultiNode(int v) {
        this.value = v;
        children = new ArrayList<>();
    }
}
