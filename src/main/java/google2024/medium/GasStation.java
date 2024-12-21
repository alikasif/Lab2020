package google2024.medium;

public class GasStation {
    public static void main(String[] args) {

        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};

//        int[] gas =  {2,3,4};
//        int[] cost = {3,4,3};

//        int[] gas =  {7,1,0,11,4};
//        int[] cost = {5,9,1,2,5};

        int start = 0;
        int i=2;
        int gasleft = gas[i];

       /* while (true) {
            System.out.println("a " + gasleft);

            i++;
            gasleft = gasleft - cost[ i-1 < 0? cost.length-1 : i-1];

            System.out.println("b " + gasleft);

            if( i >= cost.length)
                i=0;


            if(start == i) {
                System.out.println("completed");
                return;
            }
            gasleft = gasleft + gas[i];

            if(cost[i] > gasleft) {
                System.out.println("cannot complete " + i + " | " + cost[i]);
                return;
            }
        }*/

        int total_surplus = 0;
        int surplus = 0;
        start = 0;
        for(int k =0; k<gas.length; k++) {
            total_surplus = (total_surplus + gas[k] - cost[k]);
            surplus = (surplus + gas[k] - cost[k]);
            if(surplus <0){
                surplus = 0;
                start = k+1;
            }
        }
        System.out.println(total_surplus);
        if(total_surplus < 0)
            System.out.println(-1);
        else
            System.out.println(start);

        System.out.println(canCompleteCircuit(gas, cost));
    }

    static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int total_surplus = 0;
        int surplus = 0;
        int start = 0;

        for(int i = 0; i < n; i++) {
            total_surplus += gas[i] - cost[i];
            surplus += gas[i] - cost[i];
            if(surplus < 0){
                surplus = 0;
                start = i + 1;
            }
        }
        return (total_surplus < 0) ? -1 : start;
    }
}
