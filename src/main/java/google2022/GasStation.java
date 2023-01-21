package google2022;

public class GasStation {
    public static void main(String[] args) {

        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        int total_gas=0, total_cost=0;
        int starting_point =-1;

        for(int i=0;i<gas.length;i++)
        {
            total_gas+=gas[i];
            total_cost+=cost[i];
        }

        if (total_gas<total_cost)
            System.out.println("not possible");
        else {
            int curr_gas =0;
            for (int i = 0; i < gas.length; i++) {
                //for checking the total present gas at index i
                curr_gas += gas[i] - cost[i];
                if (curr_gas < 0) {
                    //there is a breakdown....so we will start from next point or index
                    starting_point = i + 1;
                    //reset our fuel
                    curr_gas = 0;
                }
            }
        }
        System.out.println(starting_point);
    }
}
