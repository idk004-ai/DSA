import java.util.Scanner;
import java.lang.Math;


public class Change {
    private static int getChange(int m) {
        // TODO: How to get the min change?
        int money = m;
        int count = 0;
        while (money > 0) {
            if (money >= 10) {
                money -= 10;
                count++;
            } else if (money >= 5) {
                money -= 5;
                count++;
            } else {
                money -= 1;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
        scanner.close();
    }
}

