package RedBlackTree;

/**
 * ClassName: ITreeNode
 * Package: RedBlackTree
 * Description: The interface for all binary search tree operations.
 *
 * @Author: Junchao Zhu
 */
public interface IBST<K extends Comparable<K>, V> {
  /**
   * Search the BST rooted at the current node with the given key K and return the search result.
   * @param key the given key
   * @return the matched node or null if not found
   */
  ITreeNode<K, V> search(K key);

  /**
   * @return the minimum node of the BST rooted at the current node.
   */
  ITreeNode<K, V> minimum();

  /**
   * @return the maximum node of the BST rooted at the current node.
   */
  ITreeNode<K, V> maximum();

  /**
   * Print the tree with inorder walk.
   */
  void sort();

  /**
   * Print by level.
   */
  void print();
  /**
   * Insert the given node to the BST.
   * @param node to insert
   */
  void insert(ITreeNode<K, V> node);

  /**
   * Delete the given node from the BST.
   * @param node to delete
   */
  void delete(ITreeNode<K, V> node);

  /**
   * Get the predecessor of the given node in the bst.
   * @param node the given node
   * @return the predecessor
   */
  ITreeNode<K, V> predecessor(ITreeNode<K, V> node);

  /**
   * Get the successor of hte given node in the bst.
   * @param node the given node
   * @return the successor
   */
  ITreeNode<K, V> successor(ITreeNode<K, V> node);
}
