package programmers.kakao_2021;

import java.util.*;

public class 카드짝맞추기 {

    // permutation 관련
    private int[] orders;
    private boolean[] visit;
    private boolean[] exist;

    // 인게임 상황에서 움직임
    private int[] dRow = new int[]{0, 0, 1, -1};
    private int[] dCol = new int[]{1, -1, 0, 0};
    private int[][] moveCountArr;

    // 맵 정보
    private int[][] board;
    private int r, c;

    // 카드 정보
    private Map<Integer, List<Card>> cardMap;

    // 결과값
    private int answer = Integer.MAX_VALUE;

    public int solution(int[][] board, int r, int c) {

        // 0,1,2,3,4,5,6 원소 들 중에 어느 순서대로 없앨지 정한다 ( 순열 )
        int cnt = init(board, r, c);

        startGame(0, cnt);

        return answer;
    }

    private void startGame(int currentDepth, int targetDepth) {
        if (currentDepth == targetDepth) {

            // 정해진 순서대로 게임 시작
            startGameWithOneCase();
            return;
        }

        for (int i = 1; i < exist.length; i++) {
            if (!exist[i] || visit[i]) {
                continue;
            }

            visit[i] = true;
            orders[currentDepth] = i;
            startGame(currentDepth + 1, targetDepth);
            visit[i] = false;
        }
    }

    private void startGameWithOneCase() {
        int sumOfMoveCnt = 0;
        int[][] tempBoard = getNewBoard(board);

        Card startCard = new Card(0, r, c, 0);

        for (int nextCard : orders) {
            int moveCountA = 2, moveCountB = 2;  // enter 2회는 기본으로 설정

            Card cardX = cardMap.get(nextCard).get(0);
            Card cardY = cardMap.get(nextCard).get(1);

            // start에서 nextCardX -> nextCardY 의 경로와
            moveCountA += getMoveCountFromStartToEnd(tempBoard, startCard, cardX);
            moveCountA += getMoveCountFromStartToEnd(tempBoard, cardX, cardY);

            // start에서 nextCardY -> nextCardX 의 경로 비교
            moveCountB += getMoveCountFromStartToEnd(tempBoard, startCard, cardY);
            moveCountB += getMoveCountFromStartToEnd(tempBoard, cardY, cardX);

            removeCard(tempBoard, cardX, cardY);

            // start의 위치를 nextCardX 또는 nextCardY로 변경
            startCard.row = cardX.row;
            startCard.col = cardX.col;
            if (moveCountA < moveCountB) {
                startCard.row = cardY.row;
                startCard.col = cardY.col;
            }

            sumOfMoveCnt += Math.min(moveCountA, moveCountB);
        }

        answer = Math.min(answer, sumOfMoveCnt);
    }

    private int getMoveCountFromStartToEnd(int[][] tempBoard, Card startCard, Card endCard) {

        Queue<Card> queue = new LinkedList<>();
        queue.add(startCard);
        initMoveCountArr();

        while (!queue.isEmpty()) {
            Card poll = queue.poll();

            // 도착
            if (poll.row == endCard.row && poll.col == endCard.col) {
                return poll.moveCnt;
            }

            // 상하좌우 컨트롤 없이 이동
            moveWithoutCtrl(queue, poll);

            // 상하좌우 컨트롤 있이 이동
            moveWithCtrl(tempBoard, queue, poll);
        }

        System.out.println("Error GetMoveCountFromStartToEnd");
        return -1;
    }

    private void moveWithCtrl(int[][] tempBoard, Queue<Card> queue, Card poll) {
        for (int i = 0; i < dRow.length; i++) {
            int currentRow = poll.row;
            int currentCol = poll.col;

            while (true) {
                int nextRow = currentRow + dRow[i];
                int nextCol = currentCol + dCol[i];

//                System.out.print("nextRow = " + nextRow);
//                System.out.println(" nextCol = " + nextCol);


                if (!isRange(nextRow, nextCol)) {
                    break;
                }

                currentRow = nextRow;
                currentCol = nextCol;

                if (tempBoard[currentRow][currentCol] != 0) {
                    break;
                }
            }

            if (poll.moveCnt + 1 >= moveCountArr[currentRow][currentCol]) {
                continue;
            }
            moveCountArr[currentRow][currentCol] = poll.moveCnt + 1;
            queue.add(new Card(0, currentRow, currentCol, poll.moveCnt + 1));
        }
    }

    private void moveWithoutCtrl(Queue<Card> queue, Card poll) {
        for (int i = 0; i < dCol.length; i++) {
            int nextRow = poll.row + dRow[i];
            int nextCol = poll.col + dCol[i];

            if (!isRange(nextRow, nextCol)) {
                continue;
            }

            if (poll.moveCnt + 1 >= moveCountArr[nextRow][nextCol]) {
                continue;
            }

            moveCountArr[nextRow][nextCol] = poll.moveCnt + 1;
            queue.add(new Card(0, nextRow, nextCol, poll.moveCnt + 1));
        }
    }

    private void initMoveCountArr() {
        moveCountArr = new int[4][4];
        for (int[] ints : moveCountArr) {
            Arrays.fill(ints, 100);
        }
    }

    private boolean isRange(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < board.length && nextCol < board[0].length;
    }

    private void removeCard(int[][] tempBoard, Card cardX, Card cardY) {
        tempBoard[cardX.row][cardX.col] = tempBoard[cardY.row][cardY.col] = 0;
    }

    private int[][] getNewBoard(int[][] board) {
        int[][] tempBoard = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, tempBoard[i], 0, board[i].length);
        }

        return tempBoard;
    }

    private int init(int[][] board, int r, int c) {
        exist = new boolean[7];
        visit = new boolean[7];
        cardMap = new HashMap<>();

        int cnt = 0;

        for (int i = 0; i < 7; i++) {
            cardMap.put(i, new ArrayList<>(2));
        }

        for (int i = 0; i < board.length; i++) {
            int[] ints = board[i];
            for (int j = 0; j < ints.length; j++) {
                int anInt = ints[j];

                if (anInt != 0) {
                    exist[anInt] = true;
                    cnt++;
                    cardMap.get(anInt).add(new Card(anInt, i, j, 0));
                }
            }
        }

        orders = new int[cnt / 2];

        this.board = board;
        this.r = r;
        this.c = c;
        return cnt / 2;
    }

    static class Card {
        int num;
        int row;
        int col;
        int moveCnt;

        public Card(int num, int row, int col, int moveCnt) {
            this.num = num;
            this.row = row;
            this.col = col;
            this.moveCnt = moveCnt;
        }
    }

    public static void main(String[] args) {
//        System.out.println(new 카드짝맞추기().solution(new int[][]{{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}}, 1, 0));
        System.out.println(new 카드짝맞추기().solution(new int[][]{{3,0,0,2}, {0,0,1,0}, {0,1,0,0}, {2,0,0,3}}, 0, 1));
    }
}
