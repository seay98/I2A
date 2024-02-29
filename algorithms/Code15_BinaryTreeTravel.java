import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code15_BinaryTreeTravel {
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

    public static void recursive(Node root) {
        if (root == null) {
            return;
        }
        recursive(root.left);
        recursive(root.right);
    }

    public static void preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value);
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.value);
        inorder(root.right);
    }

    public static void postorder(Node root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.value);
    }

    public static void preorderUnrecur(Node root) {
        Node cur = root;
        if (cur != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(cur);
            while (!stack.empty()) {
                cur = stack.pop();
                System.out.print(cur.value);
                if (cur.right != null) {
                    stack.push(cur.right);
                }if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
        System.out.println();
    }

    public static void postorderUnrecur(Node root) {
        if (root == null) {
            return;
        }
        Node cur = root;
        Stack<Node> stack = new Stack<>();
        Stack<Node> collect = new Stack<>();
        stack.push(cur);
        while (!stack.empty()) {
            cur = stack.pop();
            collect.push(cur);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        while (!collect.empty()) {
            System.out.print(collect.pop().value);
        }
        System.out.println();
    }

    public static void inorderUnrecur(Node root) {
        if (root == null) {
            return;
        }
        Node cur = root;
        Stack<Node> stack = new Stack<>();
        // 左边界入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        while (!stack.empty()) {
            // 弹出，打印，查看是否有右孩子
            cur = stack.pop();
            System.out.print(cur.value);
            if (cur.right != null) {
                // 有右孩子则所有右孩子的左边界入栈
                cur = cur.right;
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
        System.out.println();
    }

    public static void inorderUnrecurShort(Node root) {
        if (root == null) {
            return;
        }
        Node cur = root;
        Stack<Node> stack = new Stack<>();
        while (!stack.empty() || cur!=null) {
            // 所有左边界入栈
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                // 弹出，打印，查看右节点
                cur = stack.pop();
                System.out.print(cur.value);
                cur = cur.right;
            }
        }
        System.out.println();
    }

    public static void widthOrder(Node root) {
        if (root == null) {
            return;
        }
        Node cur = root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.print(cur.value);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.print("递归先序：");
        preorder(root);
        System.out.println();
        System.out.print("递归中序：");
        inorder(root);
        System.out.println();
        System.out.print("递归后序：");
        postorder(root);
        System.out.println();
        System.out.print("非递归先序：");
        preorderUnrecur(root);
        System.out.print("非递归中序：");
        inorderUnrecurShort(root);
        System.out.print("非递归后序：");
        postorderUnrecur(root);
        System.out.print("宽带优先：");
        widthOrder(root);
    }
}
