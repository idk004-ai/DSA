import java.util.Arrays;
import java.util.Scanner;

public class FractionalKnapsack {

    static class Item implements Comparable<Item> {
        int value;
        int weight;
        double fraction;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
            this.fraction = weight > 0 ? (double)  value / weight : Double.MAX_VALUE;
        }

        @Override
        public int compareTo(Item o) {
            return Double.compare(o.fraction, this.fraction);
        }
    }

    private static double getOptimalValue(int capacity, int[] weights, int[] values) {
        int n = weights.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(values[i], weights[i]);
        }

        Arrays.sort(items);


        double totalValue = 0.0;
        int remainingCapacity = capacity;
        for (int i = 0; i < n; ++i) {
            if (items[i].weight <= remainingCapacity) {
                remainingCapacity -= items[i].weight;
                totalValue += items[i].value;
            }
            else {
                double fraction = (double)remainingCapacity / items[i].weight;
                totalValue += fraction * items[i].value;
                break;
            }
        }
        return totalValue;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        scanner.close();
        System.out.printf("%.3f\n", getOptimalValue(capacity, weights, values));
    }
}
