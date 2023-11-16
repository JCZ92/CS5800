package BinomialHeap;

/**
 * ClassName: IBinomialHeap
 * Package: BinomialHeap
 * Description: Interface of the Binomial heap.
 *
 * @Author: Junchao Zhu
 */
public interface IBinomialHeap<K extends Comparable<K>, V> {
  /**
   * @return the node with minimum key in the binomial heap.
   */
  BinomialTreeNode<K, V> minimum();

  /**
   * @param h1 binomial heap 1
   * @param h2 binomial heap 2
   * @param <K> type of keys of the binomial heap
   * @param <V> type of values of the binomial heap
   * @return the merged binomial heap, with binomial trees in monotonically increasing order
   */
  static <K extends Comparable<K>, V> IBinomialHeap<K, V> heapMerge(IBinomialHeap<K, V> h1, IBinomialHeap<K, V> h2) {
    if (h1 == null) return h2;
    if (h2 == null) return h1;
    IBinomialHeap<K, V> res;
    BinomialTreeNode<K, V> node1, node2, tmp;
    node1 = h1.getHead();
    node2 = h2.getHead();
    if (node1.getDegree() < node2.getDegree()) {
      res = h1;
      tmp = h1.getHead();
      node1 = node1.getSibling();
    } else {
      res = h2;
      tmp = h2.getHead();
      node2 = node2.getSibling();
    }
    while (node1 != null && node2 != null) {
      if (node1.getDegree() < node2.getDegree()) {
        tmp.setSibling(node1);
        tmp = node1;
        node1 = node1.getSibling();
      } else {
        tmp.setSibling(node2);
        tmp = node2;
        node2 = node2.getSibling();
      }
    }
    if (node1 != null) {
      tmp.setSibling(node1);
    } else {
      tmp.setSibling(node2);
    }
    return res;
  }

  /**
   * @param h1 binomial heap 1
   * @param h2 binomial heap 2
   * @param <K> type of keys of the binomial heap
   * @param <V> type of values of the binomial heap
   * @return the united binomial heap
   */
  static <K extends Comparable<K>, V> IBinomialHeap<K, V> heapUnion(IBinomialHeap<K, V> h1, IBinomialHeap<K, V> h2) {
    if (h1 == null) return h2;
    if (h2 == null) return h1;
    IBinomialHeap<K, V> res = heapMerge(h1, h2);
    BinomialTreeNode<K, V> prev = null;
    BinomialTreeNode<K, V> cur = res.getHead();
    BinomialTreeNode<K, V> next = cur.getSibling();
    while (next != null) {
      if (cur.getDegree() != next.getDegree() || next.getSibling() != null && next.getSibling().getDegree() == cur.getDegree()) {
        prev = cur;
        cur = next;
      } else {
        if (cur.getKey().compareTo(next.getKey()) < 0) {
          // cur as the root
          cur.setSibling(next.getSibling());
          cur.link(next); // next be the left most child of current
          // cur is unchanged
        } else {
          // next be the root, cur be the child of next
          if (prev == null) {
            res.setHead(next);
          } else {
            prev.setSibling(next);
          }
          next.link(cur);
          cur = next;
        }
      }
      next = next.getSibling();
    }
    return res;
  }
  /**
   * Insert a node into the heap.
   */
  void insert(BinomialTreeNode<K, V> node);

  /**
   * @return the node with minimum key from the binomial heap
   */
  BinomialTreeNode<K, V> extractMin();

  /**
   * Decrease the key of a given node.
   * @param node the node to decrease key
   * @param key the new key with smaller value
   */
  void decreaseKey(BinomialTreeNode<K, V> node, K key);

  /**
   * Delete a node in the binomial heap.
   * @param node to delete
   */
  void delete(BinomialTreeNode<K, V> node);

  /**
   * @return head of the binomial heap.
   */
  BinomialTreeNode<K, V> getHead();

  /**
   * Set head of the binomial heap.
   * @param newHead the new head
   */
  void setHead(BinomialTreeNode<K, V> newHead);
}
