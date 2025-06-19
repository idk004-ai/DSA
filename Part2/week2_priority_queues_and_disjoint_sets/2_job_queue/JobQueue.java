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

    static class Worker implements Comparable<Worker> {
        int workerId;
        long nextTime;       
        
        public Worker(int workerId, long nextTime) {
            this.workerId = workerId;
            this.nextTime = nextTime;
        }

        @Override
        public int compareTo(JobQueue.Worker o) {
            if (this.nextTime < o.nextTime) return -1;
            if (this.nextTime > o.nextTime) return 1;

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

        PriorityQueue<Worker> queueWorker = new PriorityQueue<>();

        for (int i = 0; i < numWorkers; ++i) {
            Worker w = new Worker(i, 0);
            queueWorker.add(w);
        }
        
        for (int i = 0; i < jobs.length; ++i) {
            int duration = jobs[i];

            Worker w = queueWorker.poll();
            assignedWorker[i] = w.workerId;
            startTime[i] = w.nextTime;

            w.nextTime += duration;
            queueWorker.add(w);
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
