import java.util.Iterator;

// Create BST with Comparable and Iterable 
public class BinarySearchTree<E extends Comparable<E>> implements Iterable<E> {
    Node<E> root;
    int total;

    // Node class
    protected class Node<E> {
        E value;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        public Node(E value) {
            this.value = value;
            this.left = this.right = this.parent = null;
        }
    }

    // Constructures
    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(E value) {
        this.root = new Node(value);
    }

    // Insert to node 
    private Node<E> insertData(Node<E> root, Node<E> parent, E value) {
        if (root == null) {
            this.root = new Node(value);
            this.root.parent = parent;
            return this.root;
        } else {
            if (value.compareTo(root.value) < 0)
                root.left = insertData(root.left, root, value);
            else
                root.right = insertData(root.right, root, value);

            return root;
        }
    }

    // Inser for user 
    public void insert(E value) {
        root = insertData(root, root, value);
        this.total++;
    }

    // Traverse the node 
    private void traverse(Node<E> root) {
        if (root != null) {
            traverse(root.left);
            System.out.println(root.value);
            traverse(root.right);
        }
    }

    // Traverse for user 
    public void startTraverseWithRoot() {
        traverse(root);
    }

    // Remove from a node for user
    public void remove(E value) {
        root = removeNode(root, value);
        this.total--;
    }

    // Remove from node 
    private Node<E> removeNode(Node<E> root, E value) {
        if (root == null)
            return root;
        if (value.compareTo(root.value) < 0)
            root.left = removeNode(root.left, value);
        else if (value.compareTo(root.value) > 0)
            root.right = removeNode(root.right, value);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            else {
                root.value = minValue(root.right);
                root.right = removeNode(root.right, root.value);
            }
        }
        return root;
    }

    // Find min value 
    private E minValue(Node<E> root) {
        E minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    public int getTotal() {
        return this.total;
    }

    @Override
    public Iterator<E> iterator() {
        BinarySearchTreeIterator bIter = new BinarySearchTreeIterator(root);
        return bIter;
    }

    // Iterator for using in the chaning 
    private class BinarySearchTreeIterator implements Iterator<E> {

        private Node<E> node;

        public BinarySearchTreeIterator(Node<E> node) {
            if (node != null) {
                this.node = smallest(node);
            } else {
                this.node = node;
            }
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        private Node<E> smallest(Node<E> n) {
            if (n.left != null) {
                return smallest(n.left);
            } else {
                return n;
            }
        }

        @Override
        public E next() {
            E result = node.value;
            if (node.right != null) {
                node = smallest(node.right);
            } else {
                while (node.parent != null && node.parent.left != node) {
                    node = node.parent;
                }
                node = node.parent;
            }
            return result;
        }
    }
}
