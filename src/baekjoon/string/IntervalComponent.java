package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IntervalComponent {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        int left = 0;
        int right = Math.min(s1.length(), s2.length())+1;

        Set<String> set = new HashSet<>();
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            // mid 크기로 s1 자르고 정렬후 set에 삽입
            addSubStringOne(s1, set, mid);

            // mid 크기로 s2를 자른 다음에 정렬 후 set에 있는지 확인
            if (addSubStringTwo(s2, set, mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }

    private static boolean addSubStringTwo(String s2, Set<String> set, int mid) {
        for (int i = 0; i + mid <= s2.length(); i++) {
            char[] chars = s2.substring(i, i + mid).toCharArray();
            Arrays.sort(chars);
            if (set.contains(String.valueOf(chars))) {
                return true;
            }
        }
        return false;
    }

    private static void addSubStringOne(String s1, Set<String> set, int mid) {
        for (int i = 0; i + mid <= s1.length(); i++) {
            char[] chars = s1.substring(i, i + mid).toCharArray();
            Arrays.sort(chars);
            set.add(String.valueOf(chars));
        }
    }
}
