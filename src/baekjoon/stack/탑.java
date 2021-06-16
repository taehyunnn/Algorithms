package baekjoon.stack;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class 탑 {

    private static int[] tops;
    private static Stack<Integer> stack = new Stack<>();
    private static int[] result;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = tops.length - 1; i >= 0; i--) {
            while (!stack.isEmpty()) {
                Integer peek = stack.peek();
                if (tops[peek] < tops[i]) {
                    result[peek] = i+1;
                    stack.pop();
                } else {
                    break;
                }
            }
            stack.push(i);
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i : result) {
            bufferedWriter.write(i+" ");
        }

        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        tops = new int[n];
        result = new int[n];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < n; i++) {
            tops[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
    }
}
