import java.io.*;
import java.util.*;

class Point implements Comparable<Point>{
    int end;
    int weight;

    public Point(int end, int weight){this.end = end; this.weight = weight;}

    @Override
    public int compareTo(Point o) {
        return weight - o.weight;
    }
}

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int INF = 200_000_000;
    static List<Point> list[];
    static int dist[];
    static boolean check[];
    static int n, v;


    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        // 그래프 정보 저장할 list를 초기화한다.
        list = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++)
            list[i] = new ArrayList<>();

        dist = new int[n + 1];
        check = new boolean[n + 1];

        // 간선 정보 초기화
        for(int i = 0 ; i < v; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // list에 graph정보 저장 list의 index는 출발노드
            // element는 도착노드와 가중치를 저장한다.
            list[start].add(new Point(end, weight));
            // 무방향 그래프이므로 반대도 추가해준다.
            list[end].add(new Point(start, weight));
        }

        // 필수 노드 초기화
        st = new StringTokenizer(br.readLine());
        int require1 = Integer.parseInt(st.nextToken());
        int require2 = Integer.parseInt(st.nextToken());

        // 풀이 메소드 호출
        int answer = solve(require1, require2);
        bw.write(answer+ "\n");

        bw.close();
        br.close();
    }

    private static int solve(int required1, int required2){
        int result1 = 0;
        int result2 = 0;

        // 경로1
        // 1 -> 필수1 최단거리
        result1 += dijkstra(1, required1);
        // 필수1 -> 필수2 최단거리
        result1 += dijkstra(required1, required2);
        // 필수2 -> n 최단거리
        result1 += dijkstra(required2, n);

        //경로2
        // 1 -> 필수2 최단거리
        result2 += dijkstra(1, required2);
        // 필수2 -> 필수1 최단거리
        result2 += dijkstra(required2, required1);
        // 필수1 -> n 최단거리
        result2 += dijkstra(required1, n);

        // 경로1 && 경로2 -> 가는길이 없는 경우
        if(result1 >= INF && result2 >= INF) return -1;
        else return Math.min(result1, result2);
    }

    private static int dijkstra(int start, int end){
        Arrays.fill(dist, INF);
        Arrays.fill(check, false);

        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.add(new Point(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()){
            Point curPoint = queue.poll();
            int curNode = curPoint.end;
            int curWeight = curPoint.weight;

            if(check[curNode] == true) continue;
            check[curNode] = true;

            for(int i = 0; i < list[curNode].size(); i++){
                int nextNode = list[curNode].get(i).end;
                int nextWeight = list[curNode].get(i).weight;
                // 미방문 && 기존의 계산된 거리보다 새로운 거리가 작을 경우
                if(check[nextNode] == false && dist[nextNode] > curWeight + nextWeight){
                    dist[nextNode] = curWeight + nextWeight;
                    queue.add(new Point(nextNode, dist[nextNode]));
                }
            }
        }
        return dist[end];
    }
}