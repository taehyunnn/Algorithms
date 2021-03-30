package gabia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2 {

    private static boolean[] alphabet;
    private static Set<String> result;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();

        result = new HashSet<>();

        for (int i = 1; i <= s.length(); i++) {  // 부분 문자열로 자를 길이
            for (int j = 0; j + i <= s.length(); j++) {    // 자를 위치. 0번부터 자른다.
                String temp = s.substring(j, j + i);
                if (isGood(temp)) {
                    result.add(temp);
                }
            }
        }

        System.out.println(result.size());
    }

    private static boolean isGood(String temp) {
        alphabet = new boolean[26];
        for (int k = 0; k < temp.length(); k++) {
            if (alphabet[temp.charAt(k)-'a']) {
                return false;
            }
            alphabet[temp.charAt(k)-'a'] = true;
        }
        return true;
    }

}
