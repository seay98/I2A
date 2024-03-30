public class Code17_BSTTree {

    Node root;

    static class Node {
        int key;
        Object value;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
        }

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Node(int key, Object value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private Node getNode(int key) {
        Node cur = root;
        while (cur != null) {
            if (key < cur.key) {
                cur = cur.left;
            } else if (key > cur.key) {
                cur = cur.right;
            } else {
                return cur;
            }
        }
        return null;
    }
    public Object get(int key) {
        Node cur = root;
        while (cur != null) {
            if (key < cur.key) {
                cur = cur.left;
            } else if (key > cur.key) {
                cur = cur.right;
            } else {
                return cur.value;
            }
        }
        return null;
    }
    public Object getRecursion(int key) {
        return doGet(root, key);
    }
    private Object doGet(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            return doGet(node.left, key);
        } else if (key > node.key) {
            return doGet(node.right, key);
        } else {
            return node.value;
        }
    }
    public Object minimum() {
        // Recursion method
        //return doMin(root);

        // Non recursion method
        return minimum(root).value;
    }
    private Node minimum(Node node) {
        if (node == null) {
            return null;
        }
        Node cur = node;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }
    private Object doMin(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.value;
        }
        return doMin(node.left);
    }
    public Object maximum() {
        // Recursion method
//        return doMax(root);

        // Non recursion method
        return maximum(root).value;
    }
    private Node maximum(Node node) {
        if (node == null) {
            return null;
        }
        Node cur = node;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur;
    }
    private Object doMax(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.value;
        }
        return doMax(node.right);
    }
    public Object successor(int key) {
        Node cur = root;
        Node prefromright = null;
        while (cur != null) {
            if (key < cur.key) {
                prefromright = cur;
                cur = cur.left;
            } else if (key > cur.key) {
                cur = cur.right;
            } else {
                break;
            }
        }
        //有右子树时，前驱为右子树的最小值
        if (cur == null) {
            return null;
        }
        if (cur.right != null) {
            return minimum(cur.right);
        }
        //没有右子树时，则时最近一个从右而来的祖先为其后继
        return prefromright == null ? null : prefromright.value;
    }
    public Object predecessor(int key) {
        Node cur = root;
        Node prefromleft = null;
        while (cur != null) {
            if (key < cur.key) {
                cur = cur.left;
            } else if (key > cur.key) {
                prefromleft = cur;
                cur = cur.right;
            } else {
                break;
            }
        }
        //有左子树时，前驱为左子树的最大值
        if (cur == null) {
            return null;
        }
        if (cur.left != null) {
            return maximum(cur.left);
        }
        //没有左子树时，则为最近一个从左而来的祖先为其前驱
        return prefromleft == null ? null : prefromleft.value;
    }
    public void put(int key, Object value) {
        Node cur = root;
        Node pre = null;
        while (cur != null) {
            pre = cur;
            if (key < cur.key) {
                cur = cur.left;
            } else if (key > cur.key) {
                cur = cur.right;
            } else {
                // key存在时，更新值
                cur.value = value;
                return;
            }
        }
        // key不存在时，添加key和值
        Node node = new Node(key, value);
        if (root == null) {
            root = node;
            return;
        }
        if (key < pre.key) {
            pre.left = node;
        } else {
            pre.right = node;
        }
    }
    public void delete(int key) {
        //Recursion
        //root = doDelete(root, key);
        //Non recursion
        Node cur = root;
        Node parent = null;
        while (cur != null) {
            if (key < cur.key) {
                parent = cur;
                cur = cur.left;
            } else if (key > cur.key) {
                parent = cur;
                cur = cur.right;
            } else {
                break;
            }
        }
        //节点没有左孩子，直接将其右孩子给其父节点，作为右孩子
        //节点没有右孩子，直接将其左孩子给其父节点，作为左孩子
        //删除的节点没有孩子，可用前两种情况的逻辑
        //删除的节点既有左孩子又右右孩子，用后继节点将其替换，当后继节点与其不相邻时，先处理其后继节点的孩子
        if (cur.left==null) {
            transplant(parent, cur, cur.right);
        } else if (cur.right==null) {
            transplant(parent, cur, cur.left);
        } else {
            Node successor = cur.right;
            Node sparent = cur;
            while (successor.left != null) {
                sparent = successor;
                successor = successor.left;
            }
            if (sparent != cur) {
                transplant(sparent, successor, successor.right);
                successor.right = cur.right;
            }
            transplant(parent, cur, successor);
            successor.left = cur.left;
        }
    }
    /*
     * 将cur节点移除，并将next节点交给其父节点
     */
    private void transplant(Node parent, Node cur, Node next) {
        if (parent == null) {
            root = next;
        } else if (cur==parent.left) {
            parent.left = next;
        } else {
            parent.right = next;
        }
    }
    /*
     * 删除节点的递归实现
     */
    private Node doDelete(Node node, int key) {
        if (key < node.key) {
            node.left = doDelete(node.left, key);
            return node;
        }
        if (key > node.key) {
            node.right = doDelete(node.right, key);
            return node;
        }
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }
        Node successor = node.right;
        while (successor.left != null) {
            successor = successor.left;
        }
        successor.right = doDelete(node.right, successor.key);
        successor.left = node.left;
        return successor;
    }
}
