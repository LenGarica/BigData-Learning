package DailyTest;

public class Code_45 {


    public static int smallestMultiple(int num){

        int product = num * (num - 1);

        int m = 0;

        for(int i = 2520; i< Integer.MAX_VALUE; i++){
            m = product * i;
            for(int j = 2; j<= num; j++){
                if(m % j != 0){
                    break;
                }
                if(j == num){
                    return m;
                }
            }
        }

        return m;
    }


    public static void main(String[] args) {
        System.out.println(smallestMultiple(20));
    }

}
