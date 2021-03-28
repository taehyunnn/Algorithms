package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Search {

    private static int[] pi;    //  0 ~ i까지의 부분 문자열에서 접두사와 접미사가 얼마나 일치하는지 저장해놓은 배열
    private static int result;
    private static List<Integer> indexResult;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String origin = bufferedReader.readLine();
        String pattern = bufferedReader.readLine();

        pi = new int[pattern.length()];
        indexResult = new ArrayList<>();

        getPi(pattern);
        kmp(origin, pattern);

        System.out.println(result);
        for (Integer integer : indexResult) {
            System.out.print(integer + " ");
        }
    }

    private static void kmp(String origin, String pattern) {
        int j = 0;

        for (int i = 0; i < origin.length(); i++) {

            while (j > 0 && origin.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            if (origin.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    result++;
                    indexResult.add(i - j + 1);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }
    }

    private static void getPi(String pattern) {
        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }
    }
}
