package baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        List<Integer> list = new ArrayList<>();
        list.add(0);

        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(stringTokenizer.nextToken());

            if (a > list.get(list.size() - 1)) {
                list.add(a);
            } else {
                int left = 0;
                int right = list.size() - 1;

                while (left < right) {
                    int mid = (left + right) / 2;

                    if (a > list.get(mid)) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                list.set(right, a);
            }
        }

        System.out.println(list.size() - 1);
    }
}
