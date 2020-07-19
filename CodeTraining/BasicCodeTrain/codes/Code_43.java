package DailyTest;

public class Code_43 {

    public boolean isPalindromeNumber(int num){

        int s = num;
        int y = 0;
        while (s > 0){
            y = y * 10 + s % 10;
            s = s / 10;
        }

        if(y == num){
            return true;
        }

        return false;

    }


    public int bigPalindromeNumber(){
        int bigPal = 0;
        for (int i = 100; i <= 999; i++) {
            for (int j = 100; j <= 999; j++) {
                int num = i * j;
                if(num > bigPal && isPalindromeNumber(num)){
                    bigPal = num;
                }
            }
        }
        return bigPal;
    }

    public static void main(String[] args) {
        System.out.println(new Code_43().bigPalindromeNumber());
    }


}
