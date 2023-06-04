package salesforce;

import java.util.*;

public class FruitBasket {
    public static void main(String[] args) {

        int[] fruits = {3,3,3,1,2,1,1,2,3,3,4};
       // int[] fruits = {1,3,3,3,2,2,2,2,2,3,3,4};
        //int[] fruits = {1,2,1};
        //int[] fruits = {1,0,1,4,1,4,1,2,3};

        //int[] fruits = {1, 1, 6, 5, 6, 6, 1, 1, 1, 1};
        try2(fruits);
    }

    static void try2(int[] fruits){

        int basket1 = fruits[0];
        int basket2 = -1;
        int c = 1;
        int start =0;
        int maxc = Integer.MIN_VALUE;

        for(int i=1; i<fruits.length; i++) {

            if(fruits[i] == basket1) {
                c++;
            }
            else {
                if(basket2 == -1) {
                    basket2 = fruits[i];
                    c++;
                }
                else if (fruits[i] == basket2) {
                    c++;
                }
                else {
                    System.out.println("collected  "+ (i-start));
                    maxc = Math.max(maxc, (i-start));
                    int j = i-1;
                    int f = fruits[j];
                    while (j >= start) {
                        if(fruits[j] == f) {
                            j--;
                        }
                        else{
                            break;
                        }
                    }
                    start = j+1;
                    if(basket1 == fruits[i-1])
                        basket2=fruits[i];
                    else
                        basket1 = fruits[i];
                    c++;
                }
            }
        }
        maxc = Math.max(maxc, (fruits.length-start));
        System.out.println("collected " + (fruits.length-start));
        System.out.println("max "+ maxc);
    }

    static void try1(int[] fruits){

        int c = 0;
        int prevf = -1;
        int maxc = Integer.MIN_VALUE;
        int i=0;
        int j=0;

        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for(int f : fruits) {
            System.out.println(list);
            maxc = Math.max(maxc, list.size());
            if(set.isEmpty()){
                set.add(f);
                list.add(i);
            }
            else {
                if(set.contains(f) || set.size() < 2) {
                    set.add(f);
                    list.add(i);
                }
                else {
                    int p = 0;
                    int prev = fruits[list.get(0)];
                    for(int x = 0; x < list.size(); x++) {
                        if(prev == fruits[list.get(x)]) {
                            p = x;
                        }
                    }
                    if( p == list.size()-1) {
                        Integer integer = list.get(p);
                        list.clear();
                        list.add(integer);
                        set.clear();
                        for (Integer g : list)
                            set.add(fruits[g]);
                    }
                    else {
                        list = list.subList(p + 1, list.size());
                        set.clear();
                        for (Integer g : list)
                            set.add(fruits[g]);
                    }
                    set.add(f);
                    list.add(i);
                }
            }
            i++;
        }
        maxc = Math.max(maxc, list.size());
        System.out.println(maxc);
        System.out.println(list);
    }
}
