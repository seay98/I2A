import java.util.HashMap;

public class Code13_CopyListRandom {
    public static class Node {
        int value;
        Node next;
        Node rand;
        public Node(int data) {
            value = data;
            next = null;
            rand = null;
        }
    }

    public static Node copyRandomContainer(Node head) {
        if (head ==null) {
            return null;
        }
        HashMap<Node, Node> hashNode = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            Node n = new Node(cur.value);
            hashNode.put(cur, n);
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            Node n = hashNode.get(cur);
            n.rand = hashNode.get(cur.rand);
            n.next = hashNode.get(cur.next);
            cur = cur.next;
        }
        return hashNode.get(head);
    }

    public static Node copyRandom(Node head) {
        if (head == null) {
            return null;
        }
        //复制节点，放到原节点后
        Node cur = head;
        while (cur != null) {
            Node n = new Node(cur.value);
            n.next = cur.next;
            cur.next = n;
            cur = n.next;
        }
        //复制随机指针状态
        cur = head;
        while (cur != null) {
            Node n = cur.next;
            if (cur.rand != null) {
                n.rand = cur.rand.next;
            }
            cur = n.next;
        }
        //断开原节点与复制节点
        cur = head;
        Node nh = cur.next;
        while (cur != null) {
            Node n = cur.next;
            cur.next = n.next;
            cur = n.next;
            if (n.next != null) {
                n.next = n.next.next;
            }
        }
        return nh;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        n1.rand = n3;
        n2.rand = n1;
        n3.rand = null;
        n4.rand = n2;

        Node copy = copyRandom(n1);
        Node copy1 = copyRandomContainer(n1);
    }

}
