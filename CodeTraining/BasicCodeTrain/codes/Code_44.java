package DailyTest;

import java.util.Scanner;

public class Code_44 {

    private static final String[][] Left = null;

    public static boolean Isfake(String[][] Left, String c, boolean light) {
        for (int i = 1; i <= 3; i++) {
            String pleft = null;
            String pright = null;
            if (light == true) {
                pleft = Left[i][1];
                pright = Left[i][2];
            } else {
                pleft = Left[i][2];
                pright = Left[i][1];

            }
            switch (Left[i][3]) {
                case "up":
                    if (!pright.contains(c))
                        return false;
                    break;
                case "even":
                    if (pright.contains(c) || pleft.contains(c))
                        return false;
                    break;
                case "down":
                    if (!pleft.contains(c)) {
                        return false;
                    }
                    break;
            }

        }
        return true;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        while (N-- > 0) {
            String[][] Left = new String[4][4];
            for (int i = 1; i <= 3; i++)
                for (int j = 1; j <= 3; j++)
                    Left[i][j] = sc.next();
            for (char c = 'A'; c <= 'L'; c++) {
                String s = String.valueOf(c);
                if (Isfake(Left, s, true)) {
                    System.out.println(s + " is the counterfeit coin and it is light");
                    break;
                } else if (Isfake(Left, s, false)) {
                    System.out.println(s + " is the counterfeit coin and it is heavy");
                    break;
                }
            }

        }

    }

}
