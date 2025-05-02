public class JobScheduling {

    private static int jobScheduling(int[] deadlines, int[] profits) {
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] deadlines = new int[n];
        int[] profits = new int[n];
        for (int i = 0; i < n; i++) {
            deadlines[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            profits[i] = scanner.nextInt();
        }
        int maxProfit = jobScheduling(deadlines, profits);
    }
}