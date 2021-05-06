package baekjoon.datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class 추월 {

    private static Queue<String> queue = new LinkedList<>();
    private static Set<String> strings = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int carCnt = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < carCnt; i++) {
            queue.add(bufferedReader.readLine());
        }

        int cnt = 0;

        for (int i = 0; i < carCnt; i++) {
            String key = bufferedReader.readLine();

            while (!queue.isEmpty() && strings.contains(queue.peek())) {
                queue.poll();
            }

            strings.add(key);

            // 올바른 순서
            if (queue.peek().equals(key)) {
                queue.poll();
            }
            // 잘못된 순서
            else {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
