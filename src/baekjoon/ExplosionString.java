package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExplosionString {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String st = bufferedReader.readLine();
        String explosion = bufferedReader.readLine();

        char[] chars = new char[st.length()];

        int idx = 0;
        for (int i = 0; i < st.length(); i++) {
            char c = st.charAt(i);
            chars[idx] = c;
            if (isBomb(chars, idx, explosion)) {
                idx -= explosion.length();
            }
            idx++;
        }

        String s = String.valueOf(chars, 0, idx);
        System.out.println(s.length() == 0 ? "FRULA" : s);
    }

    private static boolean isBomb(char[] chars, int idx, String explosion) {
        if (idx < explosion.length() - 1) {
            return false;
        }

        for (int i = 0; i < explosion.length(); i++) {
            if (explosion.charAt(i) != chars[i + idx - explosion.length() + 1]) {
                return false;
            }
        }
        return true;
    }
}
