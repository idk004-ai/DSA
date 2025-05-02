import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        //write your code here
        // TODO: Find max k (summands.size())
        // Safe choice: 1 (if n > 1)
        // continue: n - 1 = ...

        if (n == 1) return List.of(1);
        if (n == 2) return List.of(2);
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int tmpSum = sum + i;
            int remain = n - tmpSum;

            if (remain > i) {
                summands.add(i);
                sum = tmpSum;
            }
            else if (remain == 0) {
                summands.add(i);
                break;
            }
        }
        return summands;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

