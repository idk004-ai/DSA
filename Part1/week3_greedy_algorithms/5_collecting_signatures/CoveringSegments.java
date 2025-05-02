import java.util.*;
import java.util.Arrays;
import java.util.List;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        Arrays.sort(segments);

        List<Integer> points = new ArrayList<>();
        for (Segment segment : segments) {
            if (!points.isEmpty()) {
                int lastPoint = points.get(points.size() - 1);
                if (segment.start > lastPoint) {
                    points.add(segment.end);
                }
            } else {
                points.add(segment.end);
            }
        }

        return points.stream().mapToInt(i -> i).toArray();
    }

    private static class Segment implements Comparable<Segment> {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(this.end, o.end);
        }
    }


//    private static int[] optimalPoints(Segment[] segments)
//    {
//        Arrays.sort(segments, new Comparator<Segment>(){
//            @Override
//            public int compare(Segment s1, Segment s2) {
//                return Integer.compare(s1.end, s2.end);
//            }
//        });
//
//        int[] points = new int[segments.length];
//        int j = 0; // index of array points
//        int min = segments[0].end;
//        points[0] = min;
//        for (int i = 1, len = segments.length; i < len; i++)
//        {
//            if (segments[i].start > min || segments[i].end < min)
//            {
//                min = segments[i].end;
//                points[++j] = min; // chúng ta bắt buộc phải tăng j sao cho bằng với i (i = 1 <=> j = 1)
//                                   // để khi copy mảng không bị lệch !
//            }
//        }
//        return Arrays.copyOf(points, j+1);
//    }



//     private static int[] optimalPoints(Segment[] segments) {
//         // Sort segments by their end points
//         Arrays.sort(segments, new Comparator<Segment>() {
//             @Override
//             public int compare(Segment s1, Segment s2) {
//                 return Integer.compare(s1.end, s2.end);
//             }
//         });
//
//         int[] points = new int[segments.length];
//         int point = segments[0].end;
//         points[0] = point;
//
//         int j = 0;
//         for (int i = 1; i < segments.length; i++) {
//             if (point < segments[i].start || point > segments[i].end) {
//                 point = segments[i].end;
//                 points[++j] = point;
//             }
//         }
//
//         return Arrays.copyOf(points, j + 1);
//     }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}

/** Bad Solution */
//         int[] points = new int[2 * segments.length];
//         for (int i = 0; i < segments.length; i++) {
//             points[2 * i] = segments[i].start;
//             points[2 * i + 1] = segments[i].end;
//         }
//         return points;
 
