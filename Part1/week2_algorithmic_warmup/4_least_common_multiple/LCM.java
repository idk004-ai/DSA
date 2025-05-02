import java.util.*;

public class LCM {

  private static long gcd(long a, long b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }

  private static long lcm_naive(long a, long b) {
    return a * b / gcd(a, b);
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    long a = scanner.nextLong();
    long b = scanner.nextLong();

    System.out.println(lcm_naive(a, b));
    scanner.close();
  }
}
