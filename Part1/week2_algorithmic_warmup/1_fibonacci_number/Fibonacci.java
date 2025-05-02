import java.util.Scanner;

public class Fibonacci {
  private static long calc_fib(int n) {
    if (n <= 1)
      return n;

    long pre = 0;
    long cur = 1;

    for (int i = 2; i <= n; i++) {
      long temp = cur;
      cur = pre + cur;
      pre = temp;
    }

    return cur;
  }


  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    long fibo = calc_fib(n);
    System.out.println(fibo);
    in.close();
  }
}
