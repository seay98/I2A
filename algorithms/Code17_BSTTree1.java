public class Code17_BSTTree1<T extends Comparable<T>> {
    Node<T> root;
    static class Node<T> {
        T key;
        Object value;
        Node<T> left;
        Node<T> right;

        public Node(T key) {
            this.key = key;
        }

        public Node(T key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Node(T key, Object value, Node<T> left, Node<T> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public Object get(T key) {
        if (key == null) {
            return null;
        }
        Node<T> cur = root;
        while (cur != null) {
            // -1: key < cur.key
            // 0: key == cur.key
            // 1: key > cur.key
            int res = key.compareTo(cur.key);
            if (res < 0) {
                cur = cur.left;
            } else if (res > 0) {
                cur = cur.right;
            } else {
                return cur.value;
            }
        }
        return null;
    }
}
