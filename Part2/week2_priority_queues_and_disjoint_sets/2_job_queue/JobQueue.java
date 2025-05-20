import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    static class WorkerInfo implements Comparable<WorkerInfo> {
        int workerId;
        long nextFreeTime;

        WorkerInfo(int workerId, long nextFreeTime) {
            this.workerId = workerId;
            this.nextFreeTime = nextFreeTime;
        }

        @Override
        public int compareTo(WorkerInfo o) {
            if (this.nextFreeTime < o.nextFreeTime) return -1;
            if (this.nextFreeTime > o.nextFreeTime) return 1;

            if (this.workerId < o.workerId) return -1;
            if (this.workerId > o.workerId) return 1;

            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];

        PriorityQueue<WorkerInfo> workerQueue = new PriorityQueue<>();

        for (int i = 0; i < numWorkers; ++i) {
            workerQueue.add(new WorkerInfo(i, 0));
        }

        for (int i = 0; i < jobs.length; ++i) {
            int duration = jobs[i];

            WorkerInfo bestWorker = workerQueue.poll();
            assignedWorker[i] = bestWorker != null ? bestWorker.workerId : 0;
            startTime[i] = bestWorker != null ? bestWorker.nextFreeTime : 0;

            bestWorker.nextFreeTime += duration;
            workerQueue.add(bestWorker);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
