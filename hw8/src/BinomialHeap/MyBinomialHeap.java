package BinomialHeap;

/**
 * ClassName: MyBinomialHeap
 * Package: BinomialHeap
 * Description:
 *
 * @Author: Junchao Zhu
 */
public class MyBinomialHeap<K extends Comparable<K>, V> implements IBinomialHeap<K, V> {
  private BinomialTreeNode<K, V> head;

  public MyBinomialHeap() {
    this.head = null;
  }
  public MyBinomialHeap(BinomialTreeNode<K, V> head) {
    this.head = head;
  }

  @Override
  public BinomialTreeNode<K, V> minimum() {
    if (this.head == null) return null;
    BinomialTreeNode<K, V> res = this.head;
    K min = res.getKey();
    BinomialTreeNode<K, V> current = this.head.getSibling();
    while (current != null) {
      if (current.getKey().compareTo(min) < 0) {
        min = current.getKey();
        res = current;
      }
      current = current.getSibling();
    }
    return res;
  }

  @Override
  public void insert(BinomialTreeNode<K, V> node) {
    MyBinomialHeap<K, V> h = new MyBinomialHeap<>(node);
    // cleanup node
    node.setParent(null);
    node.setChild(null);
    node.setSibling(null);
    node.setDegree(0);
    this.head = IBinomialHeap.heapUnion(this, h).getHead();
  }

  @Override
  public BinomialTreeNode<K, V> extractMin() {
    if (this.head == null) return null;
    BinomialTreeNode<K, V> res = null; // res the left sibling of the min node
    BinomialTreeNode<K, V> pre = null;
    BinomialTreeNode<K, V> minNode = this.head;
    BinomialTreeNode<K, V> current = this.head;
    K min = head.getKey();
    while (current != null) {
      if (current.getKey().compareTo(min) < 0) {
        min = current.getKey();
        res = pre;
        minNode = current;
      }
      pre = current;
      current = current.getSibling();
    }
    // remove res from root list
    if (res == null) {
      this.head = this.head.getSibling();
    } else {
      pre.setSibling(minNode.getSibling());
    }
    BinomialTreeNode<K, V> last = minNode.getChild();
    BinomialTreeNode<K, V> next
    for (int i = 0; i < minNode.getDegree()-1; ++i) {
      BinomialTreeNode<K, V> now = last.getSibling();
      BinomialTreeNode<K, V> next = now.getSibling();
      now.setSibling(last);
      last = now;
    }


    return res;
  }

  @Override
  public void decreaseKey(BinomialTreeNode<K, V> node, K key) {

  }

  @Override
  public void delete(BinomialTreeNode<K, V> node) {

  }

  @Override
  public BinomialTreeNode<K, V> getHead() {
    return this.head;
  }

  @Override
  public void setHead(BinomialTreeNode<K, V> newHead) {
    this.head = newHead;
  }

}
