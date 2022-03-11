/**
 * @author Ethan Yu
 * @assignment TreesA3
 * The program runs well and does all a binary tree's functions. I encountered issues with getting the
 * recursion to function properly. I tried to use the recursion to get the minimum value of a subtree,
 * but I decided to use a while loop to get the minimum value instead. Overall, this was a good experience
 * and everything works as expected.
 */

public class TreesA3 {

    public static void main(String[] args) {
        System.out.println("Making integer BSTree");
        BSTree<Integer> tree = new BSTree<>();
        System.out.println("Printing empty tree");
        tree.printInOrder();
        System.out.println("\nPrinting 1 node tree");
        tree.insert(5);
        tree.printInOrder();
        System.out.println("\nAdding elements 3 - 9");
        tree.insert(4);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        tree.insert(6);
        tree.insert(3);
        System.out.println("Printing postorder:");
        tree.printPostOrder();
        System.out.println("\nPrinting inorder:");
        tree.printInOrder();
        System.out.println("\nPrinting preorder");
        tree.printPreOrder();

        System.out.println("\nRemoving the nodes.");
        tree.removeNode(4);
        System.out.println("Removed 4 (1 child)");
        tree.removeNode(3);
        System.out.println("Removed 3 (leaf)");
        tree.removeNode(7);
        System.out.println("Removed 7 (2 children)");

        System.out.println("Printing updated the tree: ");
        System.out.println("Printing postorder:");
        tree.printPostOrder();
        System.out.println("\nPrinting inorder:");
        tree.printInOrder();
        System.out.println("\nPrinting preorder");
        tree.printPreOrder();
    }

    /**
     * A class that represents a node in a binary search tree.
     *
     * @param <E> the type of element stored in the node
     */
    static class BSTree<E extends Comparable<E>> {
        private TreeNode root;

        /**
         * instantiates tree to null
         */
        public BSTree() {
            root = null;
        }

        /**
         * inserts a value into the tree
         *
         * @param comparable the value to insert
         */
        public void insert(Comparable<E> comparable) {
            root = insert(root, comparable);
        }

        /**
         * inserts a value into the tree
         *
         * @param node       the root of the subtree
         * @param comparable the value to insert
         * @return the newly inserted node
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
         * returns minimum value in subtree rooted at node
         *
         * @param node the root of the subtree
         * @return the minimum value in the subtree
         */
        private TreeNode minValueNode(TreeNode node) {
            TreeNode current = node;
            while (current.getLeft() != null) {
                current = current.getLeft();
            }
            return current;
        }

        /**
         * removes a node from the tree
         *
         * @param comparable the value to remove
         * @return root node value
         */
        E removeNode(Comparable<E> comparable) {
            return (E) removeNode(root, comparable).getValue();
        }

        /**
         * removes a node from the tree
         *
         * @param parent     the parent node
         * @param comparable the value to remove
         * @return root node value
         */
        private TreeNode removeNode(TreeNode parent, Comparable<E> comparable) {
            if (parent == null) {
                return null;
            } else if (comparable.compareTo((E) parent.getValue()) < 0) { // if value is less than parent
                parent.setLeft(removeNode(parent.getLeft(), comparable));
            } else if (comparable.compareTo((E) parent.getValue()) > 0) { // if value is greater than parent
                parent.setRight(removeNode(parent.getRight(), comparable));
            } else { // if value is equal to parent
                if (parent.getLeft() == null) {
                    return parent.getRight();
                } else if (parent.getRight() == null) {
                    return parent.getLeft();
                } else { // if parent has two children
                    TreeNode temp = minValueNode(parent.getRight());
                    parent.setValue(temp.getValue());
                    parent.setRight(removeNode(parent.getRight(), (Comparable<E>) temp.getValue()));
                }
            }
            return parent;
        }

        /**
         * prints the tree in preorder
         */
        public void printPreOrder() {
            printPreOrder(root);
            System.out.println();
        }

        /**
         * prints the tree in preorder
         *
         * @param parent the root of the subtree
         */
        private void printPreOrder(TreeNode parent) {
            if (parent == null) {
                return;
            }
            System.out.print(parent.getValue() + " ");
            printPreOrder(parent.getLeft());
            printPreOrder(parent.getRight());
        }

        /**
         * prints the tree in inorder
         */
        public void printInOrder() {
            printInOrder(root);
            System.out.println();
        }

        /**
         * prints the tree in inorder
         *
         * @param parent the root of the subtree
         */
        private void printInOrder(TreeNode parent) {
            if (parent == null) {
                return;
            }
            printInOrder(parent.getLeft());
            System.out.print(parent.getValue() + " ");
            printInOrder(parent.getRight());
        }

        /**
         * prints the tree in postorder
         */
        public void printPostOrder() {
            printPostOrder(root);
            System.out.println();
        }

        /**
         * prints the tree in postorder
         *
         * @param parent the root of the subtree
         */
        private void printPostOrder(TreeNode parent) {
            if (parent == null) {
                return;
            }
            printPostOrder(parent.getLeft());
            printPostOrder(parent.getRight());
            System.out.print(parent.getValue() + " ");
        }

    }

}