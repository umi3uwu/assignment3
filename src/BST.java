import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public void put(K key, V value) {
            int cmp = key.compareTo(this.key);
            if (cmp < 0) {
                if (left == null) {
                    left = new Node(key, value);
                } else {
                    left.put(key, value);
                }
            } else if (cmp > 0) {
                if (right == null) {
                    right = new Node(key, value);
                } else {
                    right.put(key, value);
                }
            } else {
                this.value = value;
            }
        }

        public V get(K key) {
            int cmp = key.compareTo(this.key);
            if (cmp < 0) {
                return left == null ? null : left.get(key);
            } else if (cmp > 0) {
                return right == null ? null : right.get(key);
            } else {
                return value;
            }
        }

        public void delete(K key) {
            root = delete(root, key);
        }

        private Node delete(Node node, K key) {
            if (node == null) return null;

            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node.left = delete(node.left, key);
            } else if (cmp > 0) {
                node.right = delete(node.right, key);
            } else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;
                Node temp = node;
                node = min(temp.right);
                node.right = deleteMin(temp.right);
                node.left = temp.left;
            }
            return node;
        }

        private Node min(Node node) {
            if (node.left == null) return node;
            return min(node.left);
        }

        private Node deleteMin(Node node) {
            if (node.left == null) return node.right;
            node.left = deleteMin(node.left);
            return node;
        }
    }

    public static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
        } else {
            root.put(key, value);
        }
        size++;
    }

    public V get(K key) {
        return root == null ? null : root.get(key);
    }

    public void delete(K key) {
        if (root != null) {
            root.delete(key);
            size--;
        }
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<Entry<K, V>> {
        private Stack<Node> stack;

        public InOrderIterator() {
            stack = new Stack<>();
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node current = stack.pop();
            pushLeft(current.right);
            return new Entry<>(current.key, current.value);
        }
    }
}