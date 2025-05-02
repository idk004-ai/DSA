import java.util.*;
import java.math.BigInteger;


public class FibonacciPartialSum {

    private static final BigInteger MOD = BigInteger.valueOf(10);
    private static final int PERIOD_10 = 60;
    // multiply function:
    private static BigInteger[][] multiply(BigInteger[][] a, BigInteger[][] b)
    {
        BigInteger[][] res = new BigInteger[2][2];
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                res[i][j] = BigInteger.ZERO;
                for (int k = 0; k < 2; k++)
                {
                    res[i][j] = res[i][j].add(a[i][k].multiply(b[k][j]));
                }
            }
        }
        return res;
    }


    // power function
    private static BigInteger[][] power(BigInteger[][] a, long n)
    {
        // Base case:
        if (n == 1) return a;
        else if (n % 2 == 0)
        {
            // should not assign recursive result to a[][]
            //because we must keep init value of a[][].
            BigInteger[][] res = power(a, n / 2);
            return multiply(res, res);
        }else
        {
            return multiply(a, power(a, n - 1));
        }
    }


    // function get Fibonacci number
    private static BigInteger getFibonacci(long from, long to)
    {
        from = from % PERIOD_10;
        to = to % PERIOD_10;

        if (to < from) {
            to += PERIOD_10;
        }
        if (from == to && from == 0 && to == 0) return BigInteger.ZERO;
        BigInteger[][] a = {
            {BigInteger.ZERO, BigInteger.ONE},
            {BigInteger.ONE, BigInteger.ONE}
        };
        if (from == to)
        {
            BigInteger[][] res = power(a, from);
            return res[0][1].mod(BigInteger.valueOf(10));
        }else
        {
            BigInteger[][] res1 = power(a, (to + 2));
            BigInteger[][] res2 = power(a, (from + 1));
            BigInteger bigger = res1[0][1].subtract(BigInteger.ONE);
            BigInteger smaller = res2[0][1].subtract(BigInteger.ONE);
            return (bigger.subtract(smaller)).mod(BigInteger.valueOf(10));
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacci(from, to));
    }
}

