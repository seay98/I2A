import java.util.HashMap;
import java.util.HashSet;

public class Code14_FindIntersect {
    public static class Node {
        int value;
        Node next;
        public Node(int data) {
            value = data;
            next = null;
        }
    }

    /* 有环判断，使用容器 */
    public static Node hasLoopContainer(Node head) {
        HashSet<Node> set = new HashSet<>();
        Node cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }
            set.add(cur);
            cur = cur.next;
        }
        return null;
    }

    /* 有环判断，不使用容器 */
    public static Node hasLoop(Node head) {
        if (head == null || head.next==null || head.next.next==null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        // 当相等时，说明有环
        while (fast != slow) {
            if (fast.next==null || fast.next.next==null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        // fast指针回到表头
        fast = head;
        // 当相等时，为如环点
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    /* 查找在end前的交点 */
    private static Node findFirstIntersect(Node l1, Node l2, Node end) {
        Node n1 = l1;
        Node n2 = l2;
        int c = 0;
        while (n1 != end) {
            c++;
            n1 = n1.next;
        }
        while (n2 != end) {
            c--;
            n2 = n2.next;
        }
        // 不相等则无环
        if (n1 != n2) {
            return null;
        }
        // 找到长表进行移动
        n1 = c>0 ? l1 : l2;
        n2 = n1==l1 ? l2 : l1;
        c = Math.abs(c);
        while (c != 0) {
            c--;
            n1 = n1.next;
        }
        // 两表开始同时移动，相等时返回
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    public static Node isIntersect(Node l1, Node l2) {
        // 先判断两表是否有环
        Node lo1 = hasLoop(l1);
        Node lo2 = hasLoop(l2);
        // 如果两表均无环
        if (lo1==null && lo2==null) {
            return findFirstIntersect(l1, l2, null);
        }
        // 如果均有环
        if (lo1!=null && lo2!=null) {
            // 如环点一致，则在如环前相交
            if (lo1 == lo2) {
                return findFirstIntersect(l1, l2, lo1);
            }
            // 如环点不一样，则让其中一个表移动
            Node cur = lo1;
            while (cur != lo2) {
                cur = cur.next;
                // 如果绕了一圈无法相交，则不相交
                if (cur == lo1) {
                    return null;
                }
            }
            // 否则可返回任意如环点为交点
            return lo1;
        }
        // 如果一个有环，一个无环
        return null;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->5...
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);
        n1.next.next.next.next.next = new Node(6);
        n1.next.next.next.next.next.next = new Node(7);
        n1.next.next.next.next.next.next = n1.next.next.next.next; // 7->5

        // 1->2->3->4->5->6->7->null
        Node n2 = new Node(1);
        n2.next = new Node(2);
        n2.next.next = new Node(3);
        n2.next.next.next = new Node(4);
        n2.next.next.next.next = new Node(5);
        n2.next.next.next.next.next = new Node(6);
        n2.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node n3 = new Node(0);
        n3.next = new Node(9);
        n3.next.next = new Node(8);
        n3.next.next.next = n2.next.next.next.next.next; // 8->6

        // 0->9->8->6->7->..
        Node n4 = new Node(0);
        n4.next = new Node(9);
        n4.next.next = new Node(8);
        n4.next.next.next = n1.next.next.next.next.next; // 8->6

        // 0->9->8->10->11->8..
        Node n5 = new Node(0);
        n5.next = new Node(9);
        n5.next.next = new Node(8);
        n5.next.next.next = new Node(10);
        n5.next.next.next.next = new Node(11);
        n5.next.next.next.next.next = n5.next.next;

//        Node first = hasLoopContainer(n1);
//        Node entry = hasLoop(n1);
        Node res = isIntersect(n1, n5);
        System.out.println(res==null?"null":res.value);
    }
}
