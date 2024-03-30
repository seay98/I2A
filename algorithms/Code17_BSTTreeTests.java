import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class Code17_BSTTreeTests {
    public Code17_BSTTree createTree() {
        /*
                    4
                   / \
                  2   6
                 / \ / \
                1  3 5  7
         */
        Code17_BSTTree.Node n1 = new Code17_BSTTree.Node(1, "value1");
        Code17_BSTTree.Node n3 = new Code17_BSTTree.Node(3, "value3");
        Code17_BSTTree.Node n2 = new Code17_BSTTree.Node(2, "value2", n1, n3);

        Code17_BSTTree.Node n5 = new Code17_BSTTree.Node(5, "value5");
        Code17_BSTTree.Node n7 = new Code17_BSTTree.Node(7, "value7");
        Code17_BSTTree.Node n6 = new Code17_BSTTree.Node(6, "value6", n5, n7);

        Code17_BSTTree.Node n4 = new Code17_BSTTree.Node(4, "value4", n2, n6);

        Code17_BSTTree bstTree = new Code17_BSTTree();
        bstTree.root = n4;
        return bstTree;
    }
    public Code17_BSTTree1<String> createTree1() {
        /*
                    4
                   / \
                  2   6
                 / \ / \
                1  3 5  7
         */
        Code17_BSTTree1.Node<String> n1 = new Code17_BSTTree1.Node<String>("1", "value1");
        Code17_BSTTree1.Node<String> n3 = new Code17_BSTTree1.Node<String>("3", "value3");
        Code17_BSTTree1.Node<String> n2 = new Code17_BSTTree1.Node<String>("2", "value2", n1, n3);

        Code17_BSTTree1.Node<String> n5 = new Code17_BSTTree1.Node<String>("5", "value5");
        Code17_BSTTree1.Node<String> n7 = new Code17_BSTTree1.Node<String>("7", "value7");
        Code17_BSTTree1.Node<String> n6 = new Code17_BSTTree1.Node<String>("6", "value6", n5, n7);

        Code17_BSTTree1.Node<String> n4 = new Code17_BSTTree1.Node<String>("4", "value4", n2, n6);

        Code17_BSTTree1<String> bstTree = new Code17_BSTTree1<String>();
        bstTree.root = n4;
        return bstTree;
    }
    @Test
    public void getRecursion() {
        Code17_BSTTree bstTree = createTree();
        assertEquals("value1", bstTree.getRecursion(1));
        assertEquals("value4", bstTree.getRecursion(4));
        assertNull(bstTree.getRecursion(8));
    }
    @Test
    public void get() {
        Code17_BSTTree bstTree = createTree();
        assertEquals("value1", bstTree.get(1));
        assertEquals("value4", bstTree.get(4));
        assertNull(bstTree.get(8));
    }
    @Test
    public void get1() {
        Code17_BSTTree1<String> bstTree = createTree1();
        assertEquals("value1", bstTree.get("1"));
        assertEquals("value4", bstTree.get("4"));
        assertNull(bstTree.get("8"));
    }
    @Test
    public void minimum() {
        Code17_BSTTree bstTree = createTree();
        assertEquals("value1", bstTree.minimum());

        Code17_BSTTree nullTree = new Code17_BSTTree();
        assertNull(nullTree.minimum());
    }
    @Test
    public void maximum() {
        Code17_BSTTree bstTree = createTree();
        assertEquals("value7", bstTree.maximum());

        Code17_BSTTree nullTree = new Code17_BSTTree();
        assertNull(nullTree.maximum());
    }
    @Test
    public void predecessor() {
        Code17_BSTTree bstTree = createTree();
        assertEquals("value6", bstTree.predecessor(7));
        assertEquals("value5", bstTree.predecessor(6));
        assertEquals("value4", bstTree.predecessor(5));
        assertNull(bstTree.predecessor(1));
    }
    @Test
    public void successor() {
        Code17_BSTTree bstTree = createTree();
        assertEquals("value6", bstTree.successor(5));
        assertEquals("value5", bstTree.successor(4));
        assertEquals("value4", bstTree.successor(3));
        assertNull(bstTree.successor(7));
    }
}
