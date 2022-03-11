public class TreesA3 {

    public static void main(String[] args) {
        System.out.println("Integer Binary Search Tree, random shape");
        BSTree<Integer> tree = new BSTree<>();
        for (int i = 0; i < 10; i++) {
            int number = (int) (Math.random() * 10);
            tree.insert(number);
            System.out.println("Inserted " + number);
        }
        System.out.println("Printing tree: ");
        tree.print();
        tree = new BSTree<>();

        System.out.println("Making Integer BST, linear shape");
        for (int i = 10; i <= 20; i++) {
            tree.insert(i);
            System.out.println("Inserted " + i);
        }
        System.out.println("Printing tree: ");
        tree.print();
        tree = new BSTree<>();

        System.out.println("Making Integer BST, triangle shape");
        tree.insert(5);
        tree.insert(4);
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(0);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        tree.insert(10);
        System.out.println("Printing tree: ");
        tree.print();
    }

    static class BSTree<E extends Comparable<E>> {
        private TreeNode root;

        public BSTree() {
            root = null;
        }

        public void insert(Comparable<E> comparable) {
            root = insert(root, comparable);
        }

        private TreeNode insert(TreeNode node, Comparable<E> comparable) {
            if (node == null) {
                return new TreeNode(comparable);
            } else if (comparable.compareTo((E) node.getValue()) < 0) {
                node.setLeft(insert(node.getLeft(), comparable));
            } else {
                node.setRight(insert(node.getRight(), comparable));
            }
            return node;
        }

        E removeNode(E datum) {
            return removeNode(root, datum);
        }

        private E removeNode(TreeNode parent, E datum) {
            if (parent == null) {
                return null;
            }
            if (datum.compareTo((E) parent.getValue()) < 0) {
                parent.setLeft((TreeNode) removeNode(parent.getLeft(), datum));
            } else if (datum.compareTo((E) parent.getValue()) > 0) {
                parent.setRight((TreeNode) removeNode(parent.getRight(), datum));
            } else {
                if (parent.getLeft() == null) {
                    return (E) parent.getRight().getValue();
                } else if (parent.getRight() == null) {
                    return (E) parent.getLeft().getValue();
                } else {
                    E temp = (E) parent.getRight().getValue();
                    parent.setValue(temp);
                    parent.setRight((TreeNode) removeNode(parent.getRight(), temp));
                }
            }
            return null;
        }

        public void print() {
            print(root);
            System.out.println();
        }

        private void print(TreeNode parent) {
            if (parent == null) {
                return;
            }
            print(parent.getLeft());
            System.out.print(parent.getValue() + " ");
            print(parent.getRight());
        }

    }

}