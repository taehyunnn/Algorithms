package programmers.summer_winter;

import java.util.*;

public class 배달 {
    private int[] dist;
    private List<List<int[]>> links;

    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        // road 배열로 마을간의 연결 정보를 초기화한다.
        init(road, N);

        // 마을 연결 정보를 토대로 dist배열을 초기화한다. (다익스트라)
        dijkstra();

        // k이하의 개수 구한다.
        for(int i=1; i<dist.length; i++){
            if(dist[i] <= K){
                answer++;
            }
        }

        return answer;
    }

    public void init(int[][] road, int N){
        dist = new int[N+1];
        Arrays.fill(dist, 10000*50);

        links = new ArrayList<>(road.length);
        for(int i=0; i<=N; i++){
            links.add(new ArrayList());
        }

        for(int[] temp : road){
            int start = temp[0];
            int end = temp[1];
            int cost = temp[2];

            links.get(start).add(new int[]{end, cost});
            links.get(end).add(new int[]{start,cost});
        }
    }

    public void dijkstra(){
        dist[1] = 0;
        Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        q.add(new int[]{1,0});

        while(!q.isEmpty()){
            int[] poll = q.poll();

            int pollNum = poll[0];

            if(dist[pollNum] < poll[1]){
                continue;
            }

            for(int[] next : links.get(pollNum)){
                int nextNodeNum = next[0];
                int costFromPollToNext = next[1];

                if(dist[nextNodeNum] > dist[pollNum] + costFromPollToNext){
                    dist[nextNodeNum] = dist[pollNum] + costFromPollToNext;
                    q.add(new int[]{nextNodeNum, dist[nextNodeNum]});
                }
            }

        }
    }
}
