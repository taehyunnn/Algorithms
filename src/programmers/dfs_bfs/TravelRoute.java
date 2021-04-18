package programmers.dfs_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TravelRoute {
    public String[] solution(String[][] tickets) {
        boolean[] visit = new boolean[tickets.length];
        List<String> result = new ArrayList<>();

        Arrays.sort(tickets, (o1, o2) -> {
            // 출발지가 같으면 도착지순로 정렬
            if (o1[0].compareTo(o2[0]) == 0) {
                return o1[1].compareTo(o2[1]);
            }

            // 출발지 순으로 정렬
            return o1[0].compareTo(o2[0]);
        });

        dfs("ICN", tickets, 0, visit, result);

        return result.toArray(new String[0]);
    }

    private boolean dfs(String start, String[][] tickets, int numOfUsedTicket, boolean[] visit, List<String> result) {
        if (numOfUsedTicket == tickets.length) {
            result.add(start);
            return true;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (visit[i] || !start.equals(tickets[i][0])) {
                continue;
            }

            visit[i] = true;
            result.add(tickets[i][0]);
            if (dfs(tickets[i][1], tickets, numOfUsedTicket + 1, visit, result)) return true;
            visit[i] = false;
            result.remove(tickets[i][0]);
        }

        return false;
    }

    public static void main(String[] args) {
//        String[] solution = new TravelRoute().solution(new String[][]{{"ICN","A"},{"A","C"},{"A","D"},{"D","A"}});
//        String[] solution = new TravelRoute().solution(new String[][]{{"ICN","A"},{"A","B"},{"A","C"},{"C","A"},{"B","D"}});
        String[] solution = new TravelRoute().solution(new String[][]{{"ICN", "A"}, {"ICN", "A"}, {"ICN", "A"}, {"A", "ICN"}, {"A", "ICN"}});
        for (String s : solution) {
            System.out.print(s+" ");
        }
    }
}
