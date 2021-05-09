import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Q3Temp {

    private int cursor;
    private List<Integer> list = new LinkedList<>();
    private Stack<Integer> stack = new Stack<>();

    public String solution(int n, int k, String[] cmd) {
        cursor = k;

        for (int i = 0; i < n; i++) {
            list.add(i);
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
        for (int i = 0, listIndex = 0; i < n; i++) {
            if (listIndex < list.size() && list.get(listIndex) == i) {
                listIndex++;
                stringBuilder.append("O");
            } else {
                stringBuilder.append("X");
            }
        }

        return stringBuilder.toString();
    }

    private void rollBack() {
        Integer pop = stack.pop();

        int i = 0;
        for (; i < list.size(); i++) {
            if (list.get(i) > pop) {
                list.add(i, pop);
                if (i <= cursor) {
                    cursor++;
                }
                break;
            }
        }

        if (i == list.size()) {
            list.add(pop);
        }
    }


    private void removeRow() {
        stack.push(list.get(cursor));
        list.remove(cursor);
        if (cursor == list.size()) {
            cursor--;
        }
    }

    private void upCursor(int c) {
        cursor -= c;
    }

    private void downCursor(int c) {
        cursor += c;
    }

    public static void main(String[] args) {
        System.out.println(new Q3Temp().solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}));
    }
}
