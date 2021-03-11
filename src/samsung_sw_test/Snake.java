package samsung_sw_test;

import java.io.*;
import java.util.*;

public class Snake {

    private static int numOfApple;
    private static int currentNumOfApple;
    private static final int APPLE = 1;
    private static final int BODY = 2;
    private static int[][] map;
    private static Map<Integer, Character> directionMap;
    private static final int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};    // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int answer = 0;

        init(bufferedReader);

        Deque<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 1, 0, 0));
        map[1][1] = BODY;
        while (!queue.isEmpty()) {
            Node head = queue.peekLast();

            int direction;

            if (directionMap.containsKey(head.time)) {
                char dirCh = directionMap.get(head.time);
                if (dirCh == 'D') {
                    direction = (head.direction + 1) % moves.length;
                } else {
                    direction = (head.direction + 3) % moves.length;
                }
            } else {
                direction = head.direction;
            }

            int nextRow = head.row + moves[direction][0];
            int nextCol = head.col + moves[direction][1];

            if (!isPossible(map, nextRow, nextCol)) {
                answer = head.time + 1;
                break;
            }

            if (map[nextRow][nextCol] != APPLE) {
                Node tail = queue.pollFirst();
                map[tail.row][tail.col] = 0;
                answer = tail.time;
            }

            map[nextRow][nextCol] = BODY;
            queue.add(new Node(nextRow, nextCol, direction, head.time + 1));
        }

        System.out.println(answer);
    }

    private static boolean isPossible(int[][] map, int nextRow, int nextCol) {
        return nextRow > 0 && nextCol > 0 && nextRow < map.length && nextCol < map.length && map[nextRow][nextCol] != BODY;
    }

    private static void init(BufferedReader bufferedReader) throws IOException {
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bufferedReader.readLine());
        numOfApple = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];

        for (int i = 0; i < numOfApple; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = APPLE;
        }

        st = new StringTokenizer(bufferedReader.readLine());
        int numOfChangeDirection = Integer.parseInt(st.nextToken());

        directionMap = new TreeMap<>();

        for (int i = 0; i < numOfChangeDirection; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            directionMap.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
        }
    }

    static class Node {
        int row;
        int col;
        int direction;
        int time;

        Node(int row, int col, int direction, int time) {
            this.row = row;
            this.col = col;
            this.direction = direction;
            this.time = time;
        }
    }
}
