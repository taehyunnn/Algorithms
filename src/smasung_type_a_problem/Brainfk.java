package smasung_type_a_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Brainfk {

    private static int[] memories;
    private static char[] code;
    private static char[] input;
    private static int[] pair;
    private static int pointer;
    private static int inputPointer;
    private static int loopEndIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int testCnt = Integer.parseInt(stringTokenizer.nextToken());
        for (int i = 0; i < testCnt; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            memories = new int[Integer.parseInt(stringTokenizer.nextToken())];
            code = new char[Integer.parseInt(stringTokenizer.nextToken())];
            input = new char[Integer.parseInt(stringTokenizer.nextToken())];
            inputPointer = 0;
            pointer = 0;
            loopEndIndex = 0;
            String s = bufferedReader.readLine();
            for (int j = 0; j < s.length(); j++) {
                code[j] = s.charAt(j);
            }

            s = bufferedReader.readLine();
            for (int j = 0; j < s.length(); j++) {
                input[j] = s.charAt(j);
            }

            initPair();

            if (start()) {
                System.out.println("Terminates");
            } else {
                System.out.println("Loops " + pair[loopEndIndex] + " " + loopEndIndex);
            }
        }
    }

    private static void initPair() {
        Stack<Integer> stack = new Stack<>();
        pair = new int[code.length];

        for (int i = 0; i < code.length; i++) {
            if (code[i] == '[') {
                stack.push(i);
            } else if (code[i] == ']') {
                Integer pop = stack.pop();
                pair[i] = pop;
                pair[pop] = i;
            }
        }
    }

    private static boolean start() {
        int runCnt = 0;
        boolean flag = false;
        for (int i = 0; i < code.length; i++) {
            runCnt++;

            if (runCnt >= 50000000) {
                if (flag) {
                    return false;
                } else {
                    runCnt = 0;
                    loopEndIndex = 0;
                    flag = true;
                }
            }

            char c = code[i];
            if (c == '-') {
                memories[pointer] = memories[pointer] == 0 ? 255 : memories[pointer] - 1;
            } else if (c == '+') {
                memories[pointer] = memories[pointer] == 255 ? 0 : memories[pointer] + 1;
            } else if (c == '<') {
                pointer = pointer == 0 ? pointer = memories.length - 1 : pointer - 1;
            } else if (c == '>') {
                pointer = pointer == memories.length - 1 ? 0 : pointer + 1;
            } else if (c == '[') {
                if (memories[pointer] == 0) {
                    i = pair[i];
                }
            } else if (c == ']') {
                if (memories[pointer] != 0) {
                    loopEndIndex = Math.max(loopEndIndex, i);
                    i = pair[i];
                }
            } else if (c == '.') {
                ;
            } else if (c == ',') {
                if (inputPointer == input.length) {
                    memories[pointer] = 255;
                } else {
                    memories[pointer] = input[inputPointer];
                    inputPointer++;
                }
            }
        }

        return true;
    }

}
