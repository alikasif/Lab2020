package salesforce;

public class MaxSumCard {
    public static void main(String[] args) {

        int[] cardPoints = {1,2,3,4,5,6,1};

        int k = 3;
        int i = 0;
        int j = cardPoints.length-1;
        int c=0;
        int sum =0;
        while (c < k) {
            if(cardPoints[i] > cardPoints[j]) {
                sum = sum + cardPoints[i];
                i++;
            }
            else {
                sum = sum + cardPoints[j];
                j--;
            }
            c++;
        }
        System.out.println(sum);

    }
}
