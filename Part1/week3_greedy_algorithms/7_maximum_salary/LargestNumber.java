import java.util.*;

public class LargestNumber {
    private static String largestNumber(String[] a) {
        // TODO: Create the larget number from the array a
        // Key: We need a special compare method

        // 1. Sort the order by the key compare method
        boolean unsorted = true;
        while (unsorted) {
            int count = 0;
            for (int i = 0; i < a.length - 1; i++) {
                int firstResult = Integer.parseInt(a[i] + a[i+1]);
                int secondResult = Integer.parseInt(a[i+1] + a[i]);
                if (firstResult < secondResult) {
                    String tmp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = tmp;
                    count++;
                }
            }
            if (count == 0) unsorted = false;
        }
        return String.join("", a);
    }




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

