public class TreesA1 {

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

    /**
     * A class that represents a node in a binary search tree.
     * @param <E> the type of element stored in the node
     */
    static class BSTree<E extends Comparable<E>> {
        private TreeNode root;

        /**
         * @return the root
         */
        public BSTree() {
            root = null;
        }

        /**
         * inserts a value into the tree
         * @param comparable the value to insert
         */
        public void insert(Comparable<E> comparable) {
            root = insert(root, comparable);
        }

        /**
         * recursive method that inserts a value into the tree
         * @param node the node to insert into
         * @param comparable the value to insert
         * @return the node that was inserted into
         */
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

        /**
         * prints the tree in inorder
         */
        public void print() {
            print(root);
            System.out.println();
        }

        /**
         * recursive method that prints the tree in inorder
         * @param parent the node to print
         */
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

class TreeNode {
    private Object value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Object initValue) {
        value = initValue;
        left = null;
        right = null;
    }

    public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight) {
        value = initValue;
        left = initLeft;
        right = initRight;
    }

    public Object getValue() {
        return value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setValue(Object theNewValue) {
        value = theNewValue;
    }

    public void setLeft(TreeNode theNewLeft) {
        left = theNewLeft;
    }

    public void setRight(TreeNode theNewRight) {
        right = theNewRight;
    }
}