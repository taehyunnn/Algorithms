package baekjoon.topologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LastRank {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int testCnt = Integer.parseInt(stringTokenizer.nextToken());


        for (int i = 0; i < testCnt; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());

            int[] lastYearRank = new int[n];
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                lastYearRank[j] = Integer.parseInt(stringTokenizer.nextToken());
            }

            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int resultChangeCnt = Integer.parseInt(stringTokenizer.nextToken());

            int[][] changeRank = new int[resultChangeCnt][2];
            for (int j = 0; j < resultChangeCnt; j++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());

                changeRank[j][0] = a;
                changeRank[j][1] = b;
            }

            process(lastYearRank, changeRank);
        }
    }

    private static void process(int[] lastYearRank, int[][] changeRank) {
        int[] inDegree = new int[lastYearRank.length + 1];
        ArrayList<Integer>[] links = new ArrayList[lastYearRank.length + 1];
        for (int i = 0; i < links.length; i++) {
            links[i] = new ArrayList<>();
        }
        // 순위가 높은 팀이 낮은 팀에게 link.
        makeLink(lastYearRank, inDegree, links);

        // changeRank 를 토대로 link의 순서롤 뒤집음
        changeRank(lastYearRank, changeRank, inDegree, links);

        // inDgree가 0~ n-1까지 순서대로 안되어있으면 IMPOSSIBLE

        // 위상정렬을 통해 순서대로 나열
        // 1등을 먼저 찾는다 ( 가지고 있는 link의 개수가 lastYearRank.length -1 개 )
        int firstTeam = findFirstTeam(inDegree);

        // 1등부터 꼴등까지 while문을 통해 inDegree를 하나씩 제거
        sort(lastYearRank, inDegree, links, firstTeam);
    }

    private static void sort(int[] lastYearRank, int[] inDegree, ArrayList<Integer>[] links, int firstTeam) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(firstTeam);

        // n개의 노드이므로 정확히 n번만 돌아야 된다.
        for (int i = 0; i < lastYearRank.length; i++) {

            // n번이 돌기전에 큐가 비워져있다. == q에 add가 안 됐다. == 사이클이 있다.
            if (queue.isEmpty()) {
                System.out.println("IMPOSSIBLE");
                return;
            }

            Integer winner = queue.poll();
            result.add(winner);
            for (Integer loser : links[winner]) {
                inDegree[loser]--;
                if (inDegree[loser] == 0) {
                    queue.add(loser);
                }
            }

            // 다음 차례가 될 노드가 동시에 2개 이상이면 확실한 순위를 찾을 수 없다.
            if (queue.size() > 1) {
                System.out.println("?");
                return;
            }
        }

        // 정확한 결과
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }

    private static int findFirstTeam(int[] inDegree) {
        int firstTeam = 0;
        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                firstTeam = i;
            }
        }
        return firstTeam;
    }

    private static void changeRank(int[] lastYearRank, int[][] changeRank, int[] inDegree, ArrayList[] links) {
        for (int[] ints : changeRank) {
            int team1 = ints[0];
            int team2 = ints[1];

            int team1Rank = searchLastYearRank(lastYearRank, team1);
            int team2Rank = searchLastYearRank(lastYearRank, team2);

            int newWinner = team1Rank > team2Rank ? team1 : team2;
            int newLoser = team1Rank < team2Rank ? team1 : team2;

            links[newWinner].add(newLoser);
            links[newLoser].remove(Integer.valueOf(newWinner));

            inDegree[newWinner]--;
            inDegree[newLoser]++;
        }
    }

    private static void makeLink(int[] lastYearRank, int[] inDegree, List<Integer>[] links) {
        for (int i = 0; i < lastYearRank.length; i++) {
            int winnerTeam = lastYearRank[i];
            for (int j = i + 1; j < lastYearRank.length; j++) {
                int loserTeam = lastYearRank[j];
                links[winnerTeam].add(loserTeam);
                inDegree[loserTeam]++;
            }
        }
    }

    private static int searchLastYearRank(int[] lastYearRank, int target) {
        for (int i = 1; i < lastYearRank.length; i++) {
            if (lastYearRank[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
