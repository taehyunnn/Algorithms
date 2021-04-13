package programmers.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LongestNode {
    public int solution(int n, int[][] edge) {
        int answer = 0;

        int[] edgeFromNodeOne = new int[n+1];   // 0번 인덱스 버림
        boolean[][] map = new boolean[n+1][n+1];    // 0번 인덱스 버림. 1번 노드가 1번 인덱스
        Arrays.fill(edgeFromNodeOne, Integer.MAX_VALUE);
        edgeFromNodeOne[1] = 0;

        for (int i = 0; i < edge.length; i++) {
            map[edge[i][0]][edge[i][1]] = map[edge[i][1]][edge[i][0]] = true;
        }

        // 맵 초기화
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        while(!queue.isEmpty()){
            int cur = queue.poll() ;

            for (int i = 0; i < map.length; i++) {
                if(map[cur][i] && edgeFromNodeOne[i] == Integer.MAX_VALUE){   // 연결되어 있으면서 방문한 적이 없으면
                    edgeFromNodeOne[i] = edgeFromNodeOne[cur] + 1;
                    queue.add(i);
                }
            }
        }

        // edgeFromNodeOne에서 1번 노드를 기준으로 가장 먼 노드 계산하기
        int maxDistance = 0;
        int maxCount = 0 ;
        for (int i = 1; i < edgeFromNodeOne.length; i++) {
            if(maxDistance < edgeFromNodeOne[i]){
                maxDistance = edgeFromNodeOne[i];
                maxCount = 1;
            }
            else if(maxDistance == edgeFromNodeOne[i]){
                maxCount++;
            }
        }
        answer = maxCount;
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new LongestNode().solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
    }
}
