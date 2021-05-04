package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Contact {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < cnt; i++) {
            String s = bufferedReader.readLine();

            checkPattern(s);
        }
    }

    private static void checkPattern(String s) {
        //(100+1+ | 01)+
        boolean matches = s.matches("(100+1+|01)+");

        System.out.println(matches ? "YES" : "NO");
    }
}
