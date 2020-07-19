package DailyTest;


public class Code_46 {

    public static int N_10001_prime(int num){
        int count = 0;
        for (int i = 2; i < Integer.MAX_VALUE; i++) {
            if(isPrime(i)){
                count++;
                if(count == num){
                    return i;
                }
            }
        }
        return -1; // 没有则返回-1
    }


    public static boolean isPrime(int x){

        if(x < 2){
            return false;
        }else if(x == 2){
            return true;
        }else if((x & 1) == 0){
            return false;
        }else{
            for(int i = 3 ; i< Math.sqrt(x); i++){
                if(x % i == 0){
                    return false;
                }
            }
            return true;
        }

    }


    public static void main(String[] args) {
        System.out.println(N_10001_prime(10001));
    }

}
