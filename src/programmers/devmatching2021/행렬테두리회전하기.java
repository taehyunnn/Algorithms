package programmers.devmatching2021;

public class 행렬테두리회전하기 {

    private int[][] array;

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        init(rows,columns);

        for(int i=0; i<queries.length; i++){
            // 쿼리 하나씩 꺼내서 array 회전. 좌표는 -1씩 보정해야한다
            int min = rotateArray(queries[i][0]-1, queries[i][1] -1, queries[i][2] -1, queries[i][3] -1);
            answer[i] = min;
        }

        return answer;
    }

    public int rotateArray(int startRow, int startCol, int endRow, int endCol){
        int min = array[startRow][startCol];

        int row = startRow, col = startCol;
        int temp = array[row][col];

        while(row < endRow){
            min = Math.min(min, array[row][col]);
            array[row][col] = array[row+1][col];
            row++;
        }

        while(col < endCol){
            min = Math.min(min, array[row][col]);
            array[row][col] = array[row][col+1];
            col++;
        }

        while(row > startRow){
            min = Math.min(min, array[row][col]);
            array[row][col] = array[row-1][col];
            row--;
        }

        while(col > startCol){
            min = Math.min(min, array[row][col]);
            array[row][col] = array[row][col-1];
            col--;
        }
        col++;

        array[row][col] = temp;

        return min;
    }

    public void init(int r, int c){
        array = new int[r][c];

        int num=1;

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                array[i][j] = num++;
            }
        }
    }
}