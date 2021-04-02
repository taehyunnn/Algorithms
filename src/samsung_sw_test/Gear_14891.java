package samsung_sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Gear_14891 {

    private static GearList gearList;
    private static List<int[]> moves;
    private static final int GEAR_NUM = 4;
    private static final int CLOCK_DIR = 1;
    private static final int COUNTER_CLOCK_DIR = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        gearList = new GearList();
        gearList.add(new Gear(0, new char[0])); // 빈칸 때우기

        for (int i = 1; i <= GEAR_NUM; i++) {
            gearList.add(new Gear(i,br.readLine().toCharArray()));
        }

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int moveCnt = Integer.parseInt(stringTokenizer.nextToken());
        moves = new ArrayList<>(moveCnt);

        for (int i = 0; i < moveCnt; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(stringTokenizer.nextToken());
            int dir = Integer.parseInt(stringTokenizer.nextToken());

            moves.add(new int[]{gearNum, dir});
        }

        for (int[] move : moves) {
            gearList.rotate(move[0], move[1]);
        }

        System.out.println(gearList.printScore());
    }

    static class GearList {
        List<Gear> gearList = new ArrayList<>();

        void add(Gear gear) {
            gearList.add(gear);
        }

        void rotate(int num, int dir) {
            // 회전 하지 않는 제일 왼쪽 톱니를 찾는다.
            checkLeftGear(num, dir);

            // 회전 하지 않는 제일 오른쪽 톱니를 찾는다.
            checkRightGear(num, dir);

            // 자기자신이 회전한다.
            gearList.get(num).rotate(dir);
        }

        private void checkRightGear(int num, int dir) {
            if (num < gearList.size() - 1 && gearList.get(num).getRight() != gearList.get(num + 1).getLeft()) {
                checkRightGear(num + 1, -dir);
                gearList.get(num + 1).rotate(-dir);
            }
        }

        private void checkLeftGear(int num, int dir) {
            if (num > 1 && gearList.get(num).getLeft() != gearList.get(num - 1).getRight()) {
                checkLeftGear(num - 1, -dir);
                gearList.get(num - 1).rotate(-dir);
            }
        }


        int printScore() {
            int score = 0;
            for (int i = 1; i < gearList.size(); i++) {
                Gear gear = gearList.get(i);
                score += (1 << gear.num - 1) * (gear.getTop() -'0');
            }

            return score;
        }
    }

    static class Gear {
        int num;
        char[] tooth;

        Gear(int num, char[] tooth) {
            this.num = num;
            this.tooth = tooth;
        }

        int getLeft() {
            return tooth[6];
        }

        int getRight() {
            return tooth[2];
        }

        int getTop() {
            return tooth[0];
        }

        void rotateClockDir() {
            char temp = tooth[tooth.length - 1];
            System.arraycopy(tooth, 0, tooth, 1, tooth.length - 1);
            tooth[0] = temp;
        }

        void rotateCounterClockDir() {
            char temp = tooth[0];
            System.arraycopy(tooth, 1, tooth, 0, tooth.length - 1);
            tooth[tooth.length - 1] = temp;
        }

        void rotate(int dir) {
            switch (dir) {
                case CLOCK_DIR:
                    rotateClockDir();
                    break;
                case COUNTER_CLOCK_DIR:
                    rotateCounterClockDir();
                    break;
            }
        }
    }
}
