import java.util.*;
import java.io.*;

public class CarFueling {

    // task: a car travel with "tank" on a road with "dist", on the road will have some "stops"
    // You must compute as long as the car can travel on the road



    // d = 950
    // t = 400
    // n = 4
    // 200 375 550 750
    public static int computeMinRefills(int dist, int tank, int[] stops) {
        if (tank < stops[0]) return -1;
        if (tank > dist) return 0;

        int len = stops.length;
        int[] numOfRefill = new int[len+2];
        numOfRefill[0] = 0; numOfRefill[len+1] = dist;
        for (int i = 1; i <= len; i++)
            numOfRefill[i] = stops[i-1];

        int count = 0;
        int curr = 0;
        len = numOfRefill.length;
        while (curr < len - 1)
        {
            int last_curr = curr;
            while ((curr < len - 1) && (numOfRefill[curr+1] - numOfRefill[last_curr] <= tank))
            {
                curr += 1;
            }
            if (curr == last_curr) return -1;
            if (curr < len - 1) count += 1;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int stops[] = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
