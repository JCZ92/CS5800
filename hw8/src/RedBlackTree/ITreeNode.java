package RedBlackTree;

/**
 * ClassName: ITreeNode
 * Package: RedBlackTree
 * Description: Interface of the tree node.
 *
 * @Author: Junchao Zhu
 */
public interface ITreeNode<K extends Comparable<K>, V> {
  /**
   * Search the tree rooted at the current node with the given key K and return the search result.
   *
   * @param key      the given key
   * @param sentinel sentinel node
   * @return the matched node or null if not found
   */
  ITreeNode<K, V> search(K key, ITreeNode sentinel);

  /**
   * @param sentinel sentinel node
   * @return the minimum node of the tree rooted at the current node.
   */
  ITreeNode<K, V> minimum(ITreeNode sentinel);

  /**
   * @param sentinel sentinel node
   * @return the maximum node of the tree rooted at the current node.
   */
  ITreeNode<K, V> maximum(ITreeNode sentinel);

  /**
   * @param sentinel sentinel node
   * @return the successor of the current node.
   */
  ITreeNode<K, V> successor(ITreeNode sentinel);

  /**
   * @param sentinel sentinel node
   * @return the predecessor of the current node.
   */
  ITreeNode<K, V> predecessor(ITreeNode sentinel);

  /**
   * @param sentinel sentinel node
   * Print the tree rooted at the current node with inorder traversal.
   */
  void sort(ITreeNode sentinel);

  /**
   * Get key of the node.
   * @return key of the node
   */
  K getKey();

  /**
   * Get value of the node.
   * @return value of the node
   */
  V getVal();

  /**
   * @return Parent of the node
   */
  ITreeNode<K, V> getParent();

  /**
   * @return Left child of the node
   */
  ITreeNode<K, V> getLeft();

  /**
   * @return Right child of the node
   */
  ITreeNode<K, V> getRight();

  /**
   * Setter of the parent.
   */
  void setParent(ITreeNode<K, V> parent);

  /**
   * Setter of the left child.
   */
  void setLeft(ITreeNode<K, V> left);

  /**
   * Setter of the right child.
   */
  void setRight(ITreeNode<K,V> right);
}

