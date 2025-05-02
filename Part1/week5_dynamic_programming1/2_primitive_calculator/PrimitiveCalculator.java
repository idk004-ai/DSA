import java.util.*;
import java.lang.Math;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        
        List<Integer> sequence = new ArrayList<Integer>();
        if (n <= 1)
        {
            sequence.add(1);
            return sequence;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        int min, tmp;
        for (int i = 2; i <= n; i++) {
            min = Integer.MAX_VALUE;
            if (i % 3 == 0)
            {
                tmp = i / 3;
                if (map.get(tmp) < min) min = map.get(tmp);
            }
            if (i % 2 == 0)
            {
                tmp = i / 2;
                if (map.get(tmp) < min) min = map.get(tmp);
            }
            if (true)
            {
                tmp = i - 1;
                if (map.get(tmp) < min) min = map.get(tmp);
            }
            map.put(i, min+1);            
        }

        for (int i = n ; i >= 1; )
        {
            sequence.add(i);
            if (i % 3 == 0 && map.get(i/3) == map.get(i) - 1)
                i = i / 3;
            else if (i % 2 == 0 && map.get(i/2) == map.get(i) - 1)
                i = i / 2;
            else
                i = i - 1;
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
        scanner.close();
    }
}

