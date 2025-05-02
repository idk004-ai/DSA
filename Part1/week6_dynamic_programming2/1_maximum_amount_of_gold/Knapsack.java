import java.util.*;
import java.lang.Math;
public class Knapsack {

    static int optimalWeight(int W, int[] w)
    {
        int[] cache = new int[W + 1];
        cache[0] = 0;
        for (int i = 1, len = cache.length; i < len; i++)
        {
            cache[i] = w[0];
        }

        for (int i = 0, len = w.length; i < len; i++)
        {
            for (int j = 1; j <= W; j++)
            {
                if (w[i] <= j)
                {
                    cache[j] = Math.max(Math.max(cache[j], w[i]), );
                }
            }
        }
    }





























//    static int optimalWeight(int W, int[] w) {
//        //write you code here
//        int[] opArray = new int[W+1];
//        for (int i = 0; i < w.length; i++) {
//            for (int j = W; j >= w[i]; j--) {
//                opArray[j] = Math.max(opArray[j], opArray[j - w[i]] + w[i]);
//            }
//        }
//        return opArray[W];
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

