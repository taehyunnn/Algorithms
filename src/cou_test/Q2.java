package cou_test;

import java.util.PriorityQueue;

public class Q2 {

    private int[] month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int solution(int n, String[] customers) {
        int[][] newCustomers = new int[customers.length][2];
        PriorityQueue<Kiosk> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            priorityQueue.add(new Kiosk(i));
        }

        for (int i = 0; i < customers.length; i++) {
            String[] split = customers[i].split(" ");

            int dayWithSec = dateToDay(split[0]) * 60 * 60 * 24;
            int departureTimeWithSec = timeToSec(split[1]);

            newCustomers[i][0] = dayWithSec + departureTimeWithSec;
            newCustomers[i][1] = Integer.parseInt(split[2]) * 60;
        }

        for (int[] newCustomer : newCustomers) {
            Kiosk current = priorityQueue.poll();

            current.cnt++;
            current.endTime = newCustomer[0] + newCustomer[1];

            priorityQueue.add(current);
        }

        int max = 0;
        for (Kiosk kiosk : priorityQueue) {
            max = Math.max(max, kiosk.cnt);
        }

        return max;
    }

    static class Kiosk implements Comparable {
        int n;
        int cnt;
        int endTime;

        public Kiosk(int n) {
            this.n = n;
        }

        @Override
        public int compareTo(Object o) {
            if (this.endTime == ((Kiosk) o).endTime) {
                return n - ((Kiosk) o).n;
            }
            return endTime - ((Kiosk) o).endTime;
        }
    }

    private int dateToDay(String s) {
        String[] split = s.split("/");

        int result = 0;
        for (int i = 0; i < Integer.parseInt(split[0]) - 1; i++) {
            result += month[i];
        }
        result += Integer.parseInt(split[1]);

        return result;
    }

    private int timeToSec(String s) {
        String[] split = s.split(":");
        return Integer.parseInt(split[0]) * 3600 + Integer.parseInt(split[1]) * 60 + Integer.parseInt(split[0]);
    }

    public static void main(String[] args) {
//        System.out.println(new Q2().solution(3, new String[]{"10/01 23:20:25 30", "10/01 23:25:50 26", "10/01 23:31:00 05", "10/01 23:33:17 24", "10/01 23:50:25 13", "10/01 23:55:45 20", "10/01 23:59:39 03", "10/02 00:10:00 10"}));
        System.out.println(new Q2().solution(2, new String[]{"02/28 23:59:00 03","03/01 00:00:00 02", "03/01 00:05:00 01"}));

    }
}
