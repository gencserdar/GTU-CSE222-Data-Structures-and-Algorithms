/**
 * An implementation of an AVL Tree data structure to store and manage Stock objects.
 */
public class AVLTree {
    /**
     * Inner class representing a node in the AVL Tree.
     */
    private class Node {
        Stock stock;
        Node left, right;
        int height;

        /**
         * Constructs a new Node with the given Stock object.
         * @param stock The Stock object to be stored in the node.
         */
        Node(Stock stock) {
            this.stock = stock;
            this.height = 1;
        }
    }

    private Node root;
    /**
     * Returns the height of the given node.
     * @param node The node whose height is to be determined.
     * @return The height of the node, or 0 if the node is null.
     */
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // Insertion
    /**
     * Inserts a Stock object into the AVL Tree.
     * @param stock The Stock object to be inserted.
     */
    public void insert(Stock stock) {
        root = insert(root, stock);
    }

    private Node insert(Node node, Stock stock) {
        if (node == null) return new Node(stock);

        if (stock.getSymbol().compareTo(node.stock.getSymbol()) < 0) node.left = insert(node.left, stock);
        else if (stock.getSymbol().compareTo(node.stock.getSymbol()) > 0) node.right = insert(node.right, stock);
        else {
            node.stock = stock; // Update the existing stock
            return node;
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int balance = getBalance(node);

        if (balance > 1 && stock.getSymbol().compareTo(node.left.stock.getSymbol()) < 0) return rightRotate(node);

        if (balance < -1 && stock.getSymbol().compareTo(node.right.stock.getSymbol()) > 0) return leftRotate(node);

        if (balance > 1 && stock.getSymbol().compareTo(node.left.stock.getSymbol()) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && stock.getSymbol().compareTo(node.right.stock.getSymbol()) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Deletion
    /**
     * Deletes the node with the given symbol from the AVL Tree.
     * @param symbol The symbol of the Stock object to be deleted.
     */
    public void delete(String symbol) {
        root = delete(root, symbol);
    }

    private Node delete(Node root, String symbol) {
        // STEP 1: Perform standard BST delete
        if (root == null) return root;
        
    
        // If the symbol to be deleted is smaller than the root's symbol, it lies in the left subtree
        if (symbol.compareTo(root.stock.getSymbol()) < 0) root.left = delete(root.left, symbol);
        
        // If the symbol to be deleted is greater than the root's symbol, it lies in the right subtree
        else if (symbol.compareTo(root.stock.getSymbol()) > 0) root.right = delete(root.right, symbol);
        
        // If symbol is the same as root's symbol, this is the node to be deleted
        else {
            // Node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left) temp = root.right;
                else temp = root.left;
            
                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else { // One child case
                    root = temp; // Copy the contents of the non-empty child
                }
            } else {
                // Node with two children: Get the inorder successor (smallest in the right subtree)
                Node temp = minValueNode(root.right);
    
                // Copy the inorder successor's data to this node
                root.stock = temp.stock;
    
                // Delete the inorder successor
                root.right = delete(root.right, temp.stock.getSymbol());
            }
        }
    
        // If the tree had only one node then return
        if (root == null) return root;
        
    
        // Update height of the current node
        root.height = Math.max(height(root.left), height(root.right)) + 1;
    
        //  Get the balance factor of this node
        int balance = getBalance(root);
    
        // If this node becomes unbalanced, then there are 4 cases
    
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0) return rightRotate(root);
        
        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
    
        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0) return leftRotate(root);
        
        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
    
        return root;
    }
    /**
     * Finds the node with the minimum value in the given subtree.
     * @param node The root node of the subtree.
     * @return The node with the minimum value.
     */
    private Node minValueNode(Node node) {
        Node current = node;
        // Loop down to find the leftmost leaf
        while (current.left != null) current = current.left;
        return current;
    }
    // Search
    /**
     * Searches for a Stock object with the given symbol in the AVL Tree.
     * @param symbol The symbol of the Stock object to search for.
     * @return The Stock object if found, or null if not found.
     */
    public Stock search(String symbol) {
        return search(root, symbol);
    }

    private Stock search(Node node, String symbol) {
        if (node == null || node.stock.getSymbol().equals(symbol)) return node == null ? null : node.stock;

        if (symbol.compareTo(node.stock.getSymbol()) < 0) return search(node.left, symbol);

        return search(node.right, symbol);
    }

    // Balancing methods (left rotation, right rotation, etc.)
    // Method to perform right rotation
    /**
     * Performs a right rotation operation on the given node.
     * @param y The node to rotate.
     * @return The new root of the rotated subtree.
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    // Method to perform left rotation
    /**
     * Performs a left rotation operation on the given node.
     * @param x The node to rotate.
     * @return The new root of the rotated subtree.
     */
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T1 = y.left;

        y.left = x;
        x.right = T1;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }
    // Height update and balance factor calculations
    /**
     * Calculates the balance factor of the given node.
     * @param node The node to calculate the balance factor for.
     * @return The balance factor of the node.
     */
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }
    // In-order, pre-order, post-order traversals
    // For example:
    /**
     * Performs an in-order traversal of the AVL Tree.
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.stock);
            inOrderTraversal(node.right);
        }
    }

    /**
     * Performs a pre-order traversal of the AVL Tree.
     */
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }
    
    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.println(node.stock);
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }
    
    /**
     * Performs a post-order traversal of the AVL Tree.
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }
    
    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.println(node.stock);
        }
    }

    /**
     * Prints the AVL Tree structure.
     */
    public void printTree() {
        printTree(root, 0);
    }
    /**
     * Prints the AVL Tree structure starting from the given node with the specified indentation level.
     * @param node The root node of the subtree to print.
     * @param indent The indentation level for the subtree.
     */
    private void printTree(Node node, int indent) {
        if (node != null) {
            printTree(node.left, indent + 4);
            System.out.printf("%" + (indent + node.stock.getSymbol().length()) + "s\n", node.stock.getSymbol());
            printTree(node.right, indent + 4);
        }
    }
}
