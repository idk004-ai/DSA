import java.util.*;
import java.io.*;
import java.util.Arrays;
import java.lang.Math;

public class Partition3 {


//    public static void partitions(int W, int n, int[] items) {
//        int count = 0;
//        int[][] value = new int[W + 1][n + 1];
//        for (int i = 1; i <= W; i++) {
//            for (int j = 1; j <= n; j++) {
//                value[i][j] = value[i][j - 1];
//                if (items[j - 1] <= i) {
//                    int temp = value[i - items[j - 1]][j - 1] + items[j - 1];
//                    if (temp > value[i][j]) {
//                        value[i][j] = temp;
//                    }
//                }
//                if (value[i][j] == W) {
//                    count += 1;
//                }
//            }
//        }
//        if (count < 3) {
//            System.out.println('0');
//        } else {
//            System.out.println('1');
//        }
//    }

    private static int partition3(int[] A) {
//        write your code here
        int total = 0;
        for (int a : A)
        {
            total += a;
        }
        if (total % 3 != 0 || A.length < 3)
            return 0;
        return (canPartition(A, 0, 0, 0, 0))? 1 : 0;
    }

    private static boolean canPartition(int[] A, int index, int sum1, int sum2, int sum3)
    {
        // base case;
        if (index == A.length)
            return (sum1 == sum2 && sum2 == sum3);

        boolean result = canPartition(A, index+1, sum1+A[index], sum2, sum3)
                || canPartition(A, index+1, sum1, sum2+A[index], sum3)
                || canPartition(A, index+1, sum1, sum2, sum3+A[index]);
        return (result);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
    }
}

