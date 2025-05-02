import java.util.*;


public class FibonacciSumLastDigit {
    private static final long MOD = 1000000007;

    public static void multiply(long[][] A, long[][] B)
    {
        long[][] C = new long[2][2];

        C[0][0] = (A[0][0] * B[0][0] + A[0][1] * B[1][0]) % MOD;
        C[0][1] = (A[0][0] * B[0][1] + A[0][1] * B[1][1]) % MOD;
        C[1][0] = (A[1][0] * B[0][0] + A[1][1] * B[1][0]) % MOD;
        C[1][1] = (A[1][0] * B[0][1] + A[1][1] * B[1][1]) % MOD;

        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                A[i][j] = C[i][j];
            }
        }
    }

    public static long[][] power(long[][] M, long e)
    {
        long[][] ans = {{1, 0}, {0, 1}};
        while (e > 0)
        {
            if ((e & 1) != 0)
            {
                multiply(ans, M);
            }
            multiply(M, M);
            e >>= 1;
        }
        return ans;
    }

    public static long nthFibonacci(long n)
    {
        if (n <= 1)
            return n;

        long[][] M = {{1, 1}, {1, 0}};
        long[][] F = {{1, 0}, {0, 0}};

        long[][] res = power(M, n - 1);
        multiply(res, F);
        return (res[0][0]) % MOD;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long fib = nthFibonacci(n + 2);
        System.out.println(fib == 0 ? 9 : (fib - 1)%10);
        scanner.close();
    }
}

