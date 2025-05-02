import java.util.*;
import java.lang.Math;


class EditDistance {

    public static int EditDistance(String s, String t)
    {
        int len1 = s.length();
        int len2 = t.length();
        int[][] dis = new int[len1+1][len2+1];
        dis[0][0] = 0;
        // set tất cả các giá trị vào trong string s
        for (int i = 1; i <= len1; i++)
        {
            dis[i][0] = i;
        }

        for (int i = 1; i <= len2; i++)
        {
            dis[0][i] = i;
        }

        for (int i = 1; i <= len1; i++)
        {
            for (int j = 1; j <= len2; j++)
            {
                //insert
                int insert = dis[i][j-1] + 1;
                int delete = dis[i-1][j] + 1;
                int mis_match = dis[i-1][j-1] + 1;

                if (s.charAt(i-1) == t.charAt(j-1))
                {
                    dis[i][j] = Math.min(Math.min(insert, delete),mis_match-1);
                }
                else
                {
                    dis[i][j] = Math.min(Math.min(insert, delete),mis_match);
                }
            }
        }
        return dis[len1][len2];
    }










//   public static int EditDistance(String s, String t) {
//     //write your code here
//     int[][] d = new int[s.length()+1][t.length()+1];
//     for (int i = 0; i <= s.length(); i++)
//     {
//         d[i][0] = i;
//     }
//     for (int j = 0; j <= t.length(); j++)
//     {
//         d[0][j] = j;
//     }
//
//     int insert, del, match, mis_match;
//     for (int j = 1; j <= t.length(); j++)
//     {
//         for (int i = 1; i <= s.length(); i++)
//         {
//             insert = d[i][j-1] + 1;
//             del = d[i-1][j] + 1;
//             match = d[i-1][j-1];
//             mis_match = d[i-1][j-1] + 1;
//
//             if (s.charAt(i-1) == t.charAt(j-1))
//             {
//                 d[i][j] = Math.min(Math.min(insert, del), match);
//             }else
//             {
//                 d[i][j] = Math.min(Math.min(insert, del), mis_match);
//             }
//         }
//     }
//     return d[s.length()][t.length()];
//   }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
