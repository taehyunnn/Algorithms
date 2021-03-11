package samsung_sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DiceRolling {

    private static final int[][] moves = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};    // 동 서 북 남
    private static final int EAST = 0;
    private static final int WEST = 1;
    private static final int NORTH = 2;
    private static final int SOUTH = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        int n,m, diceRow, diceCol, numOfCommand;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        diceRow = Integer.parseInt(st.nextToken());
        diceCol = Integer.parseInt(st.nextToken());
        numOfCommand = Integer.parseInt(st.nextToken());

        int[][] map =new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st= new StringTokenizer(bufferedReader.readLine());

        int[] commands = new int[numOfCommand];
        for (int i = 0; i < numOfCommand; i++) {
            commands[i] = Integer.parseInt(st.nextToken()) -1;
        }

        Dice dice = new Dice(diceRow,diceCol);

        for (int command : commands) {
            int nextRow = dice.row + moves[command][0];
            int nextCol = dice.col + moves[command][1];

            if (!isPossible(nextRow, nextCol, map)) {
                continue;
            }

            dice.move(command);

            if (map[dice.row][dice.col] == 0) {
                map[dice.row][dice.col] = dice.down;
            } else {
                dice.down = map[dice.row][dice.col];
                map[dice.row][dice.col] = 0;
            }

            System.out.println(dice.up);
        }
    }

    private static boolean isPossible(int nextRow, int nextCol, int[][] map) {
        return nextRow >=0 && nextCol >= 0 && nextRow < map.length && nextCol < map[0].length;
    }

    static class Dice{
        int row;
        int col;

        int up;
        int down;
        int east;
        int west;
        int south;
        int north;

        public Dice(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void moveEast(){
            int temp = up;
            up = west;
            west = down;
            down = east;
            east = temp;

            col++;
        }
        public void moveWest(){
            int temp = up;
            up = east;
            east = down;
            down = west;
            west = temp;

            col--;
        }
        public void moveSouth(){
            int temp = up;
            up = north;
            north = down;
            down = south;
            south = temp;

            row++;
        }
        public void moveNorth(){
            int temp = up;
            up = south;
            south = down;
            down = north;
            north = temp;

            row--;
        }

        public void move(int command) {
            switch (command) {
                case EAST : moveEast();
                    break;
                case WEST: moveWest();
                    break;
                case NORTH : moveNorth();
                    break;
                case SOUTH: moveSouth();
            }
        }
    }
}
