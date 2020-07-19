package DailyTest;

import java.util.*;

public class Code_39 {

    public static void main(String[] args) {
        System.out.println(new Code_39().getMaxPrime(5));
    }

    public int getMaxPrime(int N){

        if(N <= 3){
            if(N > 1){
                return N;
            }else{
                throw new IllegalArgumentException("没有最大素数");
            }
        }else{
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int i = 3; i < N; i += 2){
                boolean z = isPrime(i);
                if(z){
                    arrayList.add(i);
                }
            }
            return Collections.max(arrayList);
        }

    }


    public boolean isPrime(int n) {

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


}
