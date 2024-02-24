import java.util.Stack;

public class Code11_Palindrome {

    public static class Node {
        int value;
        Node next;

        public Node(int data) {
            value = data;
        }
    }

    // 使用已有容器，需要额外空间
    public static boolean isPalindromeContainer(Node head) {
        if (head == null) {
            return false;
        }
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.value != stack.pop().value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    // 只使用链表自身
    public static boolean isPalindrome(Node head) {
        if (head == null) {
            return false;
        }
        boolean res = true;
        // 使用快慢指针
        Node fast = head;
        Node slow = head;

        // 将快指针移到最后，此时慢指针在链表中间（有奇数个节点），或中间前一个位置（有偶数个节点）
        while (fast.next!=null && fast.next.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 将slow末端指向null，slow后的节点指向调转
        fast = slow.next; // mid of link
        slow.next = null;
        Node rhead = null;
        while (fast != null) {
            rhead = fast.next;
            fast.next = slow;
            slow = fast;
            fast = rhead;
        }
        // 记录调转后另一端的头
        rhead = slow;

        // 开始比较，fast从后向前， slow从前向后
        fast = slow;
        slow = head;
        while (slow!=fast && slow!=null) {
            if (slow.value != fast.value) {
                res = false;
                break;
            }
            slow = slow.next;
            fast = fast.next;
        }

        // 恢复链表
        fast = rhead;
        rhead = null;
        while (fast != null) {
            slow = fast.next;
            fast.next = rhead;
            rhead = fast;
            fast = slow;
        }

        return res;
    }

    public static Node generatePalindromeList(int maxSize, int maxValue) {
//        int[] arr = {1,2,3,2,1};
        int[] arr = Code_Utils.generateRandomArray(maxSize/2,maxValue);

        Node head = null;
        Node end = null;
        for (int i=0; i<arr.length; i++) {
            Node node = new Node(arr[i]);
            node.next = null;
            if (i == 0) {
                head = node;
            } else {
                end.next = node;
            }
            end = node;
        }
        for (int i=arr.length-1-arr.length%2; i>=0; i--) {
            Node node = new Node(arr[i]);
            node.next = null;
            end.next = node;
            end = node;
        }
        return head;
    }

    public static void main(String[] args) {
        int maxSize = 9;
        int maxValue = 999;
        int testRounds = 1000;

        boolean succeed = true;
        for (int i=0; i<testRounds; i++) {
            Node ll = generatePalindromeList(maxSize, maxValue);
            boolean res1 = isPalindrome(ll);
            boolean res2 = isPalindromeContainer(ll);
            if (res1 != res2) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Test succeed!" : "Test failed!");
    }
}
