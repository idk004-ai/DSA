import java.util.*;
import java.io.*;

public class StackWithMax {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    class StackElement {
        int value;
        int maxValue; 
        
        StackElement (int newValue, int newMax) {
            this.value = newValue;
            this.maxValue = newMax;
        }
    }

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        Stack<StackElement> stack = new Stack<StackElement>();

        int max = Integer.MIN_VALUE;

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                max = Math.max(max, value);
                StackElement newElement = new StackElement(value, max);
                stack.push(newElement);
            } else if ("pop".equals(operation)) {
                stack.pop();
            } else if ("max".equals(operation)) {
                StackElement topElement = stack.peek();
                System.out.println(topElement.maxValue);
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}
