import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q5 {

    private List<Node> links = new ArrayList<>();
    private int[] sum;
    private int[] incomingLink;
    private int[][] linkInfo;
    private int[] permuteArray;
    private int min = Integer.MAX_VALUE;
    private int[] parentNum;

    public int solution(int k, int[] num, int[][] links) {
        init(k, num, links);

        int root = findRoot();
        calcSum(root);

        // k개의 그룹으로 나눈 뒤
        // 잘라진 그룹의 root끼리 계산해서 나오는 최대값 중의 최소값을 구한다
        permutation(-1, 0, k - 1);

        return min;
    }

    private void permutation(int index, int currentDepth, int targetDepth) {
        if (currentDepth == targetDepth) {
            removeLink(incomingLink.clone(), sum.clone());
            return;
        }

        for (int i = index + 1; i < linkInfo.length; i++) {
            permuteArray[currentDepth] = i;
            permutation(i, currentDepth + 1, targetDepth);
        }
    }

    private void removeLink(int[] incomingLink, int[] sum) {
        for (int i : permuteArray) {
            int[] link = linkInfo[i];
            int parent = link[0];
            int child = link[1];

            while (parent != -1) {
                sum[parent] -= this.sum[child];
                parent = parentNum[parent];
            }
            incomingLink[child]--;
        }

        int currentMax = 0;
        for (int i = 0; i < incomingLink.length; i++) {
            if (incomingLink[i] == 0) {
                currentMax = Math.max(currentMax, sum[i]);
            }
        }

        min = Math.min(min, currentMax);
    }

    private int calcSum(int root) {
        if (root == -1) {
            return 0;
        }

        Node node = links.get(root);
        sum[root] = node.getPeopleNum();

        sum[root] += calcSum(node.getLeft());
        sum[root] += calcSum(node.getRight());

        return sum[root];
    }

    private int findRoot() {
        for (int i = 0; i < incomingLink.length; i++) {
            if (incomingLink[i] == 0) {
                return i;
            }
        }
        return 0;
    }

    private void init(int k, int[] num, int[][] links) {
        sum = new int[num.length];
        incomingLink = new int[num.length];
        parentNum = new int[num.length];
        linkInfo = new int[num.length - 1][2];
        permuteArray = new int[k - 1];

        Arrays.fill(parentNum, -1);

        for (int i = 0, linkInfoIndex = 0; i < links.length; i++) {
            int[] link = links[i];
            int left = link[0];
            int right = link[1];

            this.links.add(new Node(i, left, right, num[i]));

            if (left >= 0) {
                incomingLink[left]++;
                linkInfo[linkInfoIndex++] = new int[]{i, left};
                parentNum[left] = i;
            }
            if (right >= 0) {
                incomingLink[right]++;
                linkInfo[linkInfoIndex++] = new int[]{i, right};
                parentNum[right] = i;
            }
        }
    }

    static class Node {
        int num;
        int parent;
        int left;
        int right;
        int peopleNum;

        public Node(int num, int left, int right, int peopleNum) {
            this.num = num;
            this.left = left;
            this.right = right;
            this.peopleNum = peopleNum;
        }

        public int getNum() {
            return num;
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }

        public int getPeopleNum() {
            return peopleNum;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Q5().solution(2, new int[]{6, 9, 7, 5}, new int[][]{{-1, -1}, {-1, -1}, {-1, 0}, {2, 1}}));
    }
}
