package smasung_type_a_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baseball {

    private static List<List<Integer>> players;
    private static int[] order = new int[10];
    private static boolean[] visit = new boolean[10];
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        init();

        // 순열로 가능한 모든 타순을 구한다
        permutation(1, 10);

        System.out.println(max);
    }

    private static void permutation(int currentDepth, int targetDepth) {
        if (currentDepth == targetDepth) {
            // 게임 시작
            max = Math.max(max, startBaseball());
//            printOrder();
            return;
        }

        if (currentDepth == 4) {
            permutation(currentDepth + 1, targetDepth);
            return;
        }

        for (int i = 1; i < 10; i++) {
            if (visit[i]) {
                continue;
            }

            order[currentDepth] = i;
            visit[i] = true;

            permutation(currentDepth + 1, targetDepth);
            visit[i] = false;
        }
    }

    private static int startBaseball() {
        int score = 0;
        int currentIndex = 1;
        int currentPlayer = order[currentIndex];

        // 이닝 반복
        for (List<Integer> player : players) {
            int outCount = 0;
            int[] base = new int[4];
            while (outCount != 3) {
                int hit = player.get(currentPlayer);

                switch (hit) {
                    case 0:
                        outCount++;
                        break;
                    case 1:
                        score += moveBase(1, base);
                        break;
                    case 2:
                        score += moveBase(2, base);
                        break;
                    case 3:
                        score += moveBase(3, base);
                        break;
                    case 4:
                        score += moveBase(4, base);
                }

                currentIndex = (currentIndex) % 9 + 1;
                currentPlayer = order[currentIndex];
            }
        }

        return score;
    }

    private static int moveBase(int hit, int[] base) {
        int score = 0;
        base[0] = 1;

        for (int i = 3; i >= 0; i--) {
            if (base[i] == 1) {
                score = movePlayer(hit, base, score, i);
                base[i] = 0;
            }
        }

        return score;
    }

    private static int movePlayer(int hit, int[] base, int score, int i) {
        if (i + hit >= 4) {
            score++;
        } else {
            base[i + hit] = 1;
        }
        return score;
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        players = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            players.add(new ArrayList<>(10));
            players.get(i).add(0);
            for (int j = 1; j < 10; j++) {
                players.get(i).add(Integer.valueOf(stringTokenizer.nextToken()));
            }
        }

        order[4] = 1;
        visit[1] = true;
    }
}
