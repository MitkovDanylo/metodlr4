import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BSTNode {
    private static class Node {
        int key;
        String value;
        Node left, right;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }

    private Node root;

    public BSTNode() {
        root = null;
    }

    public void insert(int key, String value) {
        root = insertRec(root, key, value);
    }

    private Node insertRec(Node root, int key, String value) {
        if (root == null) {
            root = new Node(key, value);
            return root;
        }

        if (key < root.key) {
            root.left = insertRec(root.left, key, value);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key, value);
        } else {
            root.value = value;
        }

        return root;
    }

    public String search(int key) {
        Node result = searchRec(root, key);
        return (result != null) ? result.value : null;
    }

    private Node searchRec(Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }

        if (key < root.key) {
            return searchRec(root.left, key);
        }

        return searchRec(root.right, key);
    }

    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.key = minValue(root.right);
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    private int minValue(Node root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    public void traverseInOrder() {
        traverseInOrderRec(root);
    }

    private void traverseInOrderRec(Node root) {
        if (root != null) {
            traverseInOrderRec(root.left);
            System.out.println(root.key + ": " + root.value);
            traverseInOrderRec(root.right);
        }
    }
}

 class BSTNodeTest {
    private BSTNode tree;

    @BeforeEach
    public void setUp() {
        tree = new BSTNode();
    }

    @Test
    public void testInsertAndSearch() {
        tree.insert(15, "A");
        tree.insert(10, "B");
        tree.insert(20, "C");
        tree.insert(5, "D");

        assertEquals("A", tree.search(15));
        assertEquals("B", tree.search(10));
        assertEquals("C", tree.search(20));
        assertEquals("D", tree.search(5));
        assertNull(tree.search(25));
    }

    @Test
    public void testDelete() {
        tree.insert(15, "A");
        tree.insert(10, "B");
        tree.insert(20, "C");

        tree.delete(10);
        assertNull(tree.search(10));

        tree.delete(15);
        assertNull(tree.search(15));

        tree.delete(20);
        assertNull(tree.search(20));
    }

    @Test
    public void testTraverseInOrder() {
        tree.insert(15, "A");
        tree.insert(10, "B");
        tree.insert(20, "C");
        tree.insert(5, "D");

        tree.traverseInOrder();
    }
}
