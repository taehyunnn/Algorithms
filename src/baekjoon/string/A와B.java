package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class A와B {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String S = bufferedReader.readLine();
        String T = bufferedReader.readLine();

        System.out.println(canChangeStoT(S,T) ? "1" :"0");
    }

    private static boolean canChangeStoT(String s, String t) {
        StringBuilder tempT = new StringBuilder(t);

        Queue<StringBuilder> queue = new LinkedList<>();
        queue.add(tempT);

        while (!queue.isEmpty()) {
            StringBuilder poll = queue.poll();

            if (poll.toString().equals(s)) {
                return true;
            }

            if (poll.length() <= s.length()) {
                continue;
            }

            char lastChar = poll.charAt(poll.length() - 1);
            removeLastWord(poll);
            if (lastChar == 'A') {
                queue.add(poll);
            } else {
                queue.add(poll.reverse());
            }
        }

        return false;
    }

    private static void removeLastWord(StringBuilder poll) {
        poll.deleteCharAt(poll.length() - 1);
    }

}
