import java.util.Scanner;
import java.lang.Math;


public class ChangeDP {



    private static int getChange(int m)
    {
        if (m < 1) return 0;
        int[] minCoins = new int[m+1];
        minCoins[0] = 0;
        int coins[] = {1,3,4};
        for (int i = 1; i <= m; i++)
        {
            minCoins[i] = Integer.MAX_VALUE;
            for (int coin : coins)
            {
                if (i >= coin)
                {
                    minCoins[i] = Math.min(minCoins[i], minCoins[i-coin]+1);
                }
            }
        }
        return minCoins[m];
    }









//     private static int getChange(int m) {
//         //write your code here
//         // 1 3 4
//         if (m < 1) return 0;
//         int[] coins = {1, 3, 4};
//         int[] minCoins = new int[m+1];
//         minCoins[0] = 0;
//         for (int i = 1; i <= m; i++)
//         {
//             minCoins[i] = Integer.MAX_VALUE;
//             for (int coin : coins)
//             {
//                 if (coin <= i && minCoins[i-coin] != Integer.MAX_VALUE)
//                 {
//                     minCoins[i] = Math.min(minCoins[i], 1 + minCoins[i-coin]);
//                 }
//             }
//         }
//         if (minCoins[m] == Integer.MAX_VALUE) return -1;
//         return minCoins[m];
//     }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
    }
}

