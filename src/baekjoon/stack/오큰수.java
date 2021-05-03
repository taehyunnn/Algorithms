package baekjoon.stack;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class 오큰수 {

    private static int[] array;
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        init();

        stack.push(0);
        for (int i = 1; i < array.length; i++) {
            while (!stack.isEmpty() && array[stack.peek()] < array[i]) {
                array[stack.pop()] = array[i];
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            array[pop] = -1;
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i : array) {
            bufferedWriter.write(i+" " );
        }
        bufferedWriter.flush();
        bufferedWriter.close();;
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());

        array = new int[n];

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        bufferedReader.close();
    }
}
