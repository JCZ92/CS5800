package RedBlackTree;

/**
 * ClassName: TreeNode
 * Package: RedBlackTree
 * Description:
 *
 * @Author: Junchao Zhu
 */
public class TreeNode<K extends Comparable<K>, V> implements ITreeNode<K, V> {
  private ITreeNode<K, V> parent;
  private ITreeNode<K, V> left;
  private ITreeNode<K, V> right;
  private K key;
  private V val;

  /**
   * Constructor of the tree node.
   */
  public TreeNode(K key, V val) {
    this.key = key;
    this.val = val;
  }

  public ITreeNode<K, V> getParent() {
    return parent;
  }

  public void setParent(ITreeNode<K, V> parent) {
    this.parent = parent;
  }

  public ITreeNode<K, V> getLeft() {
    return left;
  }

  public void setLeft(ITreeNode<K, V> left) {
    this.left = left;
  }

  public ITreeNode<K, V> getRight() {
    return right;
  }

  public void setRight(ITreeNode<K, V> right) {
    this.right = right;
  }

  public K getKey() {
    return key;
  }

  public V getVal() {
    return val;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    return false;
  }

  @Override
  public int hashCode() {
    return key.hashCode() + val.hashCode();
  }

  @Override
  public ITreeNode search(K key, ITreeNode sentinel) {
    if (key == null) return null;
    ITreeNode<K, V> current = this;
    while (current != sentinel && !current.getKey().equals(key)) {
      if (key.compareTo(current.getKey()) < 0) {
        current = current.getLeft();
      } else {
        current = current.getRight();
      }
    }
    return current == sentinel ? null : current;
  }

  @Override
  public ITreeNode<K, V> minimum(ITreeNode sentinel) {
    ITreeNode<K, V> current = this;
    while (current.getLeft() != sentinel) {
      current = current.getLeft();
    }
    return current;
  }

  @Override
  public ITreeNode<K, V> maximum(ITreeNode sentinel) {
    ITreeNode<K, V> current = this;
    while (current.getRight() != sentinel) {
      current = current.getRight();
    }
    return current;
  }

  @Override
  public ITreeNode<K, V> successor(ITreeNode sentinel) {
    ITreeNode<K, V> current = this;
    if (current.getRight() != sentinel) {
      return current.getRight().minimum(sentinel);
    }
    while (current.getParent() != sentinel && current.getParent().getRight() == current) {
      current = current.getParent();
    }
    return current.getParent();
  }

  @Override
  public ITreeNode<K, V> predecessor(ITreeNode sentinel) {
    ITreeNode<K, V> current = this;
    if (current.getLeft() != sentinel) {
      return current.getLeft().maximum(sentinel);
    }
    while (current.getParent() != sentinel && current.getParent().getLeft() == current) {
      current = current.getParent();
    }
    return current.getParent();
  }

  @Override
  public void sort(ITreeNode sentinel) {
    ITreeNode<K, V> current = this;
    if (current == sentinel) {
      return;
    }
    if (current.getLeft() != null) current.getLeft().sort(sentinel);
    System.out.print(current.getVal().toString() + " ");
    if (current.getRight() != null) current.getRight().sort(sentinel);
  }

  @Override
  public String toString() {
    return String.format("Key = " + this.key.toString() + " value = " + this.val.toString());
  }

}
