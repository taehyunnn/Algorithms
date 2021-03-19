package baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PizzaBaking {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int oven = Integer.parseInt(stringTokenizer.nextToken());
        int pizzaNum = Integer.parseInt(stringTokenizer.nextToken());

        int[] ovenDepth = new int[oven + 1];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= oven; i++) {
            ovenDepth[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int[] pizzas = new int[pizzaNum + 1];

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= pizzaNum; i++) {
            pizzas[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int lastLocation = oven + 1;

        // 오븐의 지름을 재설정한 뒤에 이분탐색하는 문제..
        // WoW 오븐의 지름을 재설정할 생각을 어찌 할까.....

        for (int i = 1; i < oven; i++) {
            if (ovenDepth[i] < ovenDepth[i + 1]) {
                ovenDepth[i + 1] = ovenDepth[i];
            }
        }

        for (int i = 1; i <= pizzaNum; i++) {
            int currentPizza = pizzas[i];

            int front = 0;
            int back = lastLocation - 1;

            while (front <= back) {
                int mid = (front + back) / 2;

                if (ovenDepth[mid] >= currentPizza) {  // 피자가 들어가면
                    front = mid + 1;
                    lastLocation = mid;
                } else {    // 안들어가면
                    back = mid - 1;
                }
            }
            // 피자를 못 넣으면
            if (back < 0) {
                lastLocation = 0;
            }
        }

        System.out.println(lastLocation);
    }
}
