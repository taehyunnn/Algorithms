package programmers.Greedy;

import java.util.*;

public class CrackdownCamera {

    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(a -> a[1]));
        int ans = 0;
        int last_camera = Integer.MIN_VALUE;
        for (int[] a : routes) {
            if (last_camera < a[0]) {
                ans++;
                last_camera = a[1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new CrackdownCamera().solution(new int[][]{{1,10},{2,3},{11,13}}));
    }
}
