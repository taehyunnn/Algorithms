package baekjoon.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 단어섞기 {

    private static char[] word1, word2, word3;
    private static int len1, len2, len3;
    private static boolean answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(br.readLine());

        for (int i = 1; i <= cnt; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            word1 = stringTokenizer.nextToken().toCharArray();
            word2 = stringTokenizer.nextToken().toCharArray();
            word3 = stringTokenizer.nextToken().toCharArray();

            len1 = word1.length;
            len2 = word2.length;
            len3 = word3.length;

            if (canMixFirstAndSecondToResult()) {
                System.out.println("Data set " + i + ": yes");
            } else {
                System.out.println("Data set " + i + ": no");
            }
        }
    }

    private static boolean canMixFirstAndSecondToResult() {
        // 단어가 있는지 확인
        Set<Character> set = new HashSet<>();

        for (char c : word1) {
            set.add(c);
        }

        for (char c : word2) {
            set.add(c);
        }

        for (char c : word3) {
            if (!set.contains(c)) {
                return false;
            }
        }

        answer = false;

        // 순서가 맞는지 확인
        solve(0, 0, 0);
        return answer;
    }

    private static void solve(int idx1, int idx2, int idx3) {
        if (answer) return;

        if (idx1 + idx2 == len3) {
            answer = true;
            return;
        }

        if (idx1 < len1 && word1[idx1] == word3[idx3]) {
            solve(idx1 + 1, idx2, idx3 + 1);
        }

        if (idx2 < len2 && word2[idx2] == word3[idx3]) {
            solve(idx1, idx2 + 1, idx3 + 1);
        }
    }
}
