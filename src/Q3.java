import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Q3 {

    private int cursor;
    private int cursorIndex;

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> indexStack = new Stack<>();

    private boolean[] removeCheck;
    private List<Integer> indexList = new LinkedList<>();

    public String solution(int n, int k, String[] cmd) {
        cursor = cursorIndex = k;
        removeCheck = new boolean[n];
        for (int i = 0; i < n; i++) {
            indexList.add(i);
        }

        for (String s : cmd) {
            char c = s.charAt(0);

            switch (c) {
                case 'U':
                    upCursor(s.charAt(2) - '0');
                    break;
                case 'D':
                    downCursor(s.charAt(2) - '0');
                    break;
                case 'C':
                    removeRow();
                    break;
                case 'Z':
                    rollBack();
                    break;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (boolean b : removeCheck) {
            if (b) {
                stringBuilder.append("X");
            } else {
                stringBuilder.append("O");
            }
        }

        return stringBuilder.toString();
    }

    private void rollBack() {
        Integer pop = stack.pop();
        Integer indexPop = indexStack.pop();
        removeCheck[pop] = false;
        indexList.add(indexPop, pop);

        if (cursorIndex >= indexPop) {
            cursorIndex++;
            cursor = indexList.get(cursorIndex);
        }

    }

    private void removeRow() {
        removeCheck[cursor] = true;
        stack.push(cursor);
        indexStack.push(cursorIndex);
        indexList.remove(cursorIndex);

        if (cursorIndex == indexList.size()) {
            cursorIndex--;
            cursor = indexList.get(cursorIndex);
        }
    }

    private void upCursor(int c) {
        cursorIndex -= c;
        cursor = indexList.get(cursorIndex);
    }

    private void downCursor(int c) {
        cursorIndex += c;
        cursor = indexList.get(cursorIndex);
    }

    public static void main(String[] args) {
        System.out.println(new Q3().solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}));
//        System.out.println(new Q3().solution(8, 2, new String[]{"C", "C", "D 1", "C", "C", "C","U 2","C","Z","Z","Z"}));
    }
}
