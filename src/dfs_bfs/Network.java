package dfs_bfs;

import java.util.LinkedList;
import java.util.Queue;

public class Network {
    public int solution(int n, int[][] computers) {
        int answer = 0;

        boolean[] visit = new boolean[n];

        Queue<Integer> queue = new LinkedList<>();
        int networkCnt = 0;

        for (int i = 0; i < n; i++) {
            if(visit[i]){
                continue;
            }

            queue.add(i);
            networkCnt++;

            while (!queue.isEmpty()){
                int cur = queue.poll();

                for (int j = 0; j < n; j++) {
                    if(computers[cur][j] == 1 && !visit[j]){
                        queue.add(j);
                        visit[j] = true;
                    }
                }
            }
        }

        answer = networkCnt;
        return answer;
    }
    
    

    public static void main(String[] args) {
        System.out.println(new Network().solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
    }
}
