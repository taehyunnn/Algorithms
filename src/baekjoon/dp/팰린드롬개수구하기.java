package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 팰린드롬개수구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();

        int[][] cntOfPalindrome = new int[s.length()][s.length()];

        for (int i = 0; i < cntOfPalindrome.length; i++) {
            cntOfPalindrome[i][i] = 1;
        }

        for (int i = 0; i < cntOfPalindrome.length - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                cntOfPalindrome[i][i + 1] = 3;
            } else {
                cntOfPalindrome[i][i + 1] = 2;
            }
        }

        // i : 문자 사이의 거리
        for (int i = 2; i < cntOfPalindrome.length; i++) {
            // j: index
            for (int j = 0; j + i < cntOfPalindrome.length; j++) {
                cntOfPalindrome[j][j + i] = cntOfPalindrome[j + 1][j + i] + cntOfPalindrome[j][j + i - 1] - cntOfPalindrome[j + 1][j + i - 1];

                // 좌,우 끝이 같으면 ex {a, ..., a} 로 일치하는거 추가
                if (s.charAt(j) == s.charAt(j + i)) {
                    cntOfPalindrome[j][j + i] += cntOfPalindrome[j + 1][j + i - 1] +1;
                }
            }
        }

        System.out.println(cntOfPalindrome[0][s.length() - 1]);
    }
}
