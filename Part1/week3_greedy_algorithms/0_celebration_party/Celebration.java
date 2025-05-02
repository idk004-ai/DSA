import java.util.*;

public class Celebration {
    // Description: Có n đứa trẻ đến tham dự một buổi tiệc.
    // Mỗi đứa trẻ có độ tuổi xác định. Bạn cần phân chia tất cả các đứa trẻ thành các nhóm sao cho:
    // 1. Bất kỳ hai đứa trẻ nào trong cùng một nhóm đều có độ tuổi chênh lệch nhau không quá 2 năm.
    // 2. Số lượng nhóm phải ít nhất có thể (vì mỗi nhóm cần một người lớn giám sát).

    // Input: Dòng đầu tiên là số nguyên n (1 ≤ n ≤ 1000) - số lượng trẻ em.
    // Dòng thứ hai là n số nguyên a1, a2, ..., an (1 ≤ ai ≤ 100) - độ tuổi của các trẻ em.

    // Output: Số lượng cặp trẻ em tối thiểu cần thiết để tổ chức buổi tiệc.

    // Ví dụ:
    // Input:
    // 5
    // 1 2 3 4 5
    // Output:
    // 2


    public static int getMinimumPairs(int[] ages) {
        Arrays.sort(ages);
        List<List<Integer>> groups = new ArrayList<>();
        int left = 0;
        while (left < ages.length) {
            List<Integer> group = new ArrayList<>();
            group.add(ages[left]);
            group.add(ages[left] + 2);
            groups.add(group);
            left++;

            while (left < ages.length && ages[left] <= group.get(1)) {
                left++;
            }
        }
        return groups.size();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] ages = new int[n];
        for (int i = 0; i < n; i++) {
            ages[i] = scanner.nextInt();
        }
        System.out.println(getMinimumPairs(ages));
    }
}