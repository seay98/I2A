import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Code16_BTMaxWidth {
    public static class Node {
        int value;
        Node left;
        Node right;
        public Node(int data) {
            value = data;
            left = null;
            right = null;
        }
    }
    
    public static int getMaxWidth(Node root) {
        if (root == null) {
            return 0;
        }
        Node cur = root;
        Queue<Node> queue = new LinkedList<>();
        HashMap<Node, Integer> map = new HashMap<>();
        int curLevel = 0;
        int maxWidth = 0;
        int curWidth = 0;
        queue.add(root);
        map.put(cur, curLevel);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (curLevel != map.get(cur)) {
                maxWidth = Math.max(maxWidth, curWidth);
                curWidth = 0;
                curLevel++;
            }
            curWidth++;
            if (cur.left != null) {
                queue.add(cur.left);
                map.put(cur.left, curLevel+1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                map.put(cur.right, curLevel+1);
            }
        }
        return Math.max(maxWidth, curWidth);
    }

    public static int getMaxWidthNoMap(Node root){
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        Node cur = root;
        Node curEnd = root;
        Node nextEnd = null;
        int maxWidth = 0;
        int curWidth = 0;
        queue.add(cur);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            // 每个节点的孩子都有可能是下一层的最后节点
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curWidth++;
            if (cur == curEnd) {
                maxWidth = Math.max(curWidth, maxWidth);
                curEnd = nextEnd;
                curWidth = 0;
            }
        }
        return Math.max(curWidth, maxWidth);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
//        Node root = new Node(1);
//        root.left = new Node(2);
//        root.right = new Node(3);
//        root.left.left = new Node(4);
//        root.left.right = new Node(5);
//        root.right.left = new Node(6);
//        root.right.right = new Node(7);
//
//        System.out.println(getMaxWidth(root));
//        System.out.println(getMaxWidthNoMap(root));

        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node root = generateRandomBST(maxLevel, maxValue);
            int res1 = getMaxWidth(root);
            int res2 = getMaxWidthNoMap(root);
            if (res1 != res2) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish!");
    }
}
