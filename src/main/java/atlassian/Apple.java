package atlassian;

public class Apple {
    public static void main(String[] args) {

        int[] apples = {2,0,2}; //{1,2,0,4,1};// {1,2,3,5,2};//

        int[] days =   {2,0,1}; //{2,2,0,3,1}; //{3,2,1,4,2}; //

        int ca = 0;
        int td = 0;

        for(int i=0; i< apples.length; i ++) {
            if( td < i+1) {
            int a = Math.min(apples[i], days[i]);
                td = td + a;
                ca = ca + a;
            }
        }
        System.out.println(td);
    }
}