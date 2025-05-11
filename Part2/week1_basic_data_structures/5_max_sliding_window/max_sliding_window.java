import java.util.Scanner;
import java.util.Stack;

public class max_sliding_window {
    static class Element {
        int value;
        int max;

        public Element(int newValue, int newMax) {
            this.value = newValue;
            this.max = newMax;
        }
    }
    public static void maxSlidingWindowNaive(int[] A, int w) {
        Stack<Element> in = new Stack<>();
        Stack<Element> out = new Stack<>();

        int inMax = Integer.MIN_VALUE;
        int outMax = Integer.MIN_VALUE;

        for (int i = 0; i < A.length; ++i) {
            int windowMax = A[i];

            if (i < w) {
                inMax = Math.max(inMax, windowMax);
                in.push(new Element(windowMax, inMax));
                if (i == w - 1) System.out.print(inMax + " ");
            } else {
                if (out.empty()) {
                    outMax = Integer.MIN_VALUE;
                    while (!in.empty()) {
                        Element inElement = in.pop();
                        outMax = Math.max(outMax, inElement.value);
                        Element outElement = new Element(inElement.value, outMax);
                        out.push(outElement);
                    }
                }
                out.pop();

                if (out.size() == w - 1) inMax = Integer.MIN_VALUE;

                inMax = Math.max(inMax, windowMax);
                in.push(new Element(windowMax, inMax));

                int inPeek = !in.empty() ? in.peek().max : 0;
                int outPeek = !out.empty() ? out.peek().max : 0;
                System.out.print(Math.max(inPeek, outPeek) + " ");
            }

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        
        int[] A = new int[n];
        for (int i = 0; i < n; ++i) {
            A[i] = scanner.nextInt();
        }
        
        int w = scanner.nextInt();
        
        maxSlidingWindowNaive(A, w);
        
        scanner.close();
    }
}