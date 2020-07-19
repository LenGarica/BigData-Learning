package DailyTest;

public class Code_41 {


    public void perfectCube(int N){

        for (int a = 2; a <= N; a++){
            for(int b = 2; b < a; b++){
                for(int c = b; c < a; c++){
                    for (int d = c; d < a; d++){
                        if(a* a* a == b* b* b + c* c* c + d* d* d){
                            System.out.printf("Cube = %d, Triple = (%d,%d,%d)\n", a, b, c, d);
                        }
                    }
                }
            }
        }

    }


    public static void main(String[] args) {
        new Code_41().perfectCube(24);
    }



}
