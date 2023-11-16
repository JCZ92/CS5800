package BinomialHeap;

/**
 * ClassName: BinomialTree
 * Package: BinomialHeap
 * Description: This class represents a binomial tree node.
 *
 * @Author: Junchao Zhu
 */
public class BinomialTreeNode<K extends Comparable<K>, V> {
  private BinomialTreeNode<K, V> parent;
  private K key;
  private V val;
  private int degree;
  private BinomialTreeNode<K, V> child;
  private BinomialTreeNode<K, V> sibling;

  public BinomialTreeNode(K key, V val) {
    this.key = key;
    this.val = val;
    this.degree = 0;
  }

  /**
   * Link together with another Binomial node. The given node will be the child of current node.
   * @param other the node to link with
   */
  public void link(BinomialTreeNode<K, V> other) {
    other.setParent(this);
    other.setSibling(this.getChild());
    this.setChild(other);
    this.degree++;
  }

  public BinomialTreeNode<K, V> getParent() {
    return parent;
  }

  public void setParent(BinomialTreeNode<K, V> parent) {
    this.parent = parent;
  }

  public int getDegree() {
    return degree;
  }

  public void setDegree(int degree) {
    this.degree = degree;
  }

  public BinomialTreeNode<K, V> getChild() {
    return child;
  }

  public void setChild(BinomialTreeNode<K, V> child) {
    this.child = child;
  }

  public BinomialTreeNode<K, V> getSibling() {
    return sibling;
  }

  public void setSibling(BinomialTreeNode<K, V> sibling) {
    this.sibling = sibling;
  }

  public K getKey() {
    return key;
  }

  public V getVal() {
    return val;
  }
}
