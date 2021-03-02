package cou_test;

public class Q4 {
    private final int MOD = 10007;
    private int path1 = 0;
    private int path2 = 0;

    public int solution(String depar, String hub, String dest, String[][] roads) {

        dfs(1, depar, hub, roads);
        dfs(2, hub, dest, roads);

        return path1 * path2;
    }

    private void dfs(int path, String depar, String dest, String[][] roads) {
        if (depar.equals(dest)) {
            if (path == 1) {
                path1 = (++path1) % MOD;
            } else {
                path2 = (++path2) % MOD;
            }
            return;
        }

        for (String[] road : roads) {
            if (road[0].equals(depar)) {
                dfs(path, road[1], dest, roads);
            }
        }
    }

    public static void main(String[] args) {
//        System.out.println(new Q4().solution("SEOUL", "DAEGU", "YEOSU", new String[][]{{"ULSAN","BUSAN"},{"DAEJEON","ULSAN"},{"DAEJEON","GWANGJU"},{"SEOUL","DAEJEON"},{"SEOUL","ULSAN"},{"DAEJEON","DAEGU"},{"GWANGJU","BUSAN"},{"DAEGU","GWANGJU"},{"DAEGU","BUSAN"},{"ULSAN","DAEGU"},{"GWANGJU","YEOSU"},{"BUSAN","YEOSU"}}));
        System.out.println(new Q4().solution("ULSAN", "SEOUL", "BUSAN", new String[][]{{"SEOUL","DAEJEON"},{"ULSAN","BUSAN"},{"DAEJEON","ULSAN"},{"DAEJEON","GWANGJU"},{"SEOUL","ULSAN"},{"DAEJEON","BUSAN"},{"GWANGJU","BUSAN"}}));
    }
}
