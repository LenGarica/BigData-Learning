package DailyTest;

public class Code_42 {

    public long primeFactors(long n){
        if(n == 1){
            return 1;
        }
        for (int i = 2; i < n; i++) {
            if(n % i == 0){
                return primeFactors(n / i);
            }
        }
        return n;
    }


    public static void main(String[] args) {
        System.out.println(new Code_42().primeFactors(13195));
    }


}
