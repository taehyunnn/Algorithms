package Greedy;

import java.util.Arrays;

public class IslandConnection {
    public int solution(int n, int[][] costs) {
        int answer = 0;

        int[] representation = new int[n];

        for (int i = 0; i < representation.length; i++) {
            representation[i] = i;
        }

        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);

        int index = 0;
        while (!checkResult(representation)) {
            int island1 = costs[index][0];
            int island2 = costs[index][1];

            if (representation[island1] != representation[island2]) {
                answer += costs[index][2];
                changeRepresentation(representation, island1, island2);
            }

            index++;
        }


        return answer;
    }

    private void changeRepresentation(int[] representation, int island1, int island2) {
        int small = Math.min(representation[island1], representation[island2]);
        int big = Math.max(representation[island1], representation[island2]);

        for (int i = 0; i < representation.length; i++) {
            if(representation[i] == big){
                representation[i] = small;
            }
        }
    }

    private boolean checkResult(int[] representation) {
        int temp = representation[0];
        for (int i : representation) {
            if (temp != i) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new IslandConnection().solution(4, new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}}));
    }
}
