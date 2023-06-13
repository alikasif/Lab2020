package salesforce;

public class ValidateBTPreOrderSer {
    public static void main(String[] args) {

        String ser = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        String[] nodes = ser.split(",");

        int idegree = 0;
        int odegree = 0;

        int diff= 1;
        for(String node : nodes) {
            diff--;
            if(diff<0)
                System.out.println("not valid");
            if(!node.equals("#"))
                diff+=2;
        }
        //System.out.println(diff <=0? "not vlaid":"valid");
    }
}
