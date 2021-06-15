package baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 스타트링크 {

    private static int[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int maxFloor = Integer.parseInt(stringTokenizer.nextToken());
        int currentFloor = Integer.parseInt(stringTokenizer.nextToken());
        int destinationFloor = Integer.parseInt(stringTokenizer.nextToken());
        int up = Integer.parseInt(stringTokenizer.nextToken());
        int down = Integer.parseInt(stringTokenizer.nextToken());

        visit = new int[maxFloor + 1];
        Arrays.fill(visit, 1000000);

        bfs(maxFloor, currentFloor, destinationFloor, up, down);

    }

    private static void bfs(int maxFloor, int currentFloor, int destinationFloor, int up, int down) {
        Queue<Floor> queue = new LinkedList<>();
        queue.add(new Floor(currentFloor, 0));
        visit[currentFloor] = 0;

        while (!queue.isEmpty()) {
            Floor poll = queue.poll();

            if (poll.floorNum == destinationFloor) {
                System.out.println(poll.moveCnt);
                return;
            }

            int upFloor = poll.floorNum + up;
            int downFloor = poll.floorNum - down;
            int moveCnt = poll.moveCnt + 1;

            if (upFloor <= maxFloor) {
                moveNextFloor(queue, upFloor, moveCnt);
            }

            if (downFloor >= 1) {
                moveNextFloor(queue, downFloor, moveCnt);
            }
        }

        System.out.println("use the stairs");
    }

    private static void moveNextFloor(Queue<Floor> queue, int nextFloor, int moveCnt) {
        if (visit[nextFloor] > moveCnt) {
            visit[nextFloor] = moveCnt;
            queue.add(new Floor(nextFloor, moveCnt));
        }
    }

    static class Floor {
        int floorNum;
        int moveCnt;

        public Floor(int floorNum, int moveCnt) {
            this.floorNum = floorNum;
            this.moveCnt = moveCnt;
        }
    }
}
