import java.util.Scanner;

public class FibonacciHuge {

    // Method to calculate the Pisano period for a given m
    private static long getPisanoPeriod(long m) {
        if (m <= 1) return m;
        long a = 0, b = 1, c;
        for (long i = 0; i < m * m; i++) {
            c = (a + b) % m;
            a = b;
            b = c;
            // Pisano period starts with 01
            if (a == 0 && b == 1) return i + 1;
        }
        return -1; // Should never reach here for m > 1
    }

    // Method to calculate the nth Fibonacci number modulo m
    private static long getFibonacciHuge(long n, long m) {
        if (m <= 1) return n % m;
        long period = getPisanoPeriod(m);
        n = n % period;
        long a = 0, b = 1, c;
        if (n <= 1) return n;

        for (long i = 0; i < n - 1; i++) {
            c = (a + b) % m;
            a = b;
            b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHuge(n, m));
        scanner.close();
    }
}

