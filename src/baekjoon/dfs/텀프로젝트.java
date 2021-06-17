package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 텀프로젝트 {

    private static int m;
    private static Student[] students;
    private static int numOfBelongedStudents;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        for (int i = 0; i < n; i++) {
            m = Integer.parseInt(bufferedReader.readLine());
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            students = new Student[m + 1];
            numOfBelongedStudents = 0;
            for (int j = 1; j < m + 1; j++) {
                students[j] = new Student(j, Integer.parseInt(stringTokenizer.nextToken()), 0, 0);
            }

            int groupNum = 1;
            for (int k = 1; k < students.length; k++) {
                if (students[k].group != 0) {
                    continue;
                }
                calcGroup(k, 1, groupNum++);
            }
            System.out.println(m - numOfBelongedStudents);
        }
    }

    private static void calcGroup(int i, int cnt, int groupNum) {
        if (students[i].group == groupNum) {
            numOfBelongedStudents += cnt - students[i].order;
            return;
        }

        if (students[i].order != 0) {
            return;
        }

        students[i].group = groupNum;
        students[i].order = cnt;
        calcGroup(students[i].friendNum, cnt + 1, groupNum);
    }

    static class Student {
        int num;
        int friendNum;
        int order;
        int group;

        public Student(int num, int friendNum, int order, int group) {
            this.num = num;
            this.friendNum = friendNum;
            this.order = order;
            this.group = group;
        }
    }
}
