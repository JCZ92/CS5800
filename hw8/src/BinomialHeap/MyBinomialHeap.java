package BinomialHeap;

import java.util.Random;

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
    K min = minNode.getKey();
    BinomialTreeNode<K, V> current = this.head;
    while (current != null) {
      if (current.getKey().compareTo(min) < 0) {
        min = current.getKey();
        res = pre;
        minNode = current;
      }
      pre = current;
      current = current.getSibling();
    }
    // remove minNode from root list
    if (res == null) {
      this.head = this.head.getSibling();
    } else {
      res.setSibling(minNode.getSibling());
    }
    BinomialTreeNode<K, V> now = minNode.getChild() == null? null: minNode.getChild().getSibling();
    BinomialTreeNode<K, V> rest = now == null ? null : now.getSibling();
    BinomialTreeNode<K, V> ordered = minNode.getChild();
    if (ordered != null) ordered.setSibling(null);
    while (now != null) {
      now.setSibling(ordered);
      ordered = now;
      now = rest;
      if (rest != null) rest = rest.getSibling();
    }
    IBinomialHeap<K, V> h = new MyBinomialHeap<>(ordered);
    this.head = IBinomialHeap.heapUnion(this, h).getHead();
    return minNode;
  }

  @Override
  public void decreaseKey(BinomialTreeNode<K, V> node, K key) {
    if (key.compareTo(node.getKey()) < 0) {
      node.setKey(key);
      BinomialTreeNode<K, V> y = node;
      BinomialTreeNode<K, V> z = y.getParent();
      while (z != null && y.getKey().compareTo(z.getKey()) < 0) {
        // exchange z ang y
        K zKey = z.getKey();
        V zVal = z.getVal();
        z.setKey(y.getKey());
        z.setVal(y.getVal());
        y.setKey(zKey);
        y.setVal(zVal);
        y = z;
        z = y.getParent();
      }
    } else {
      System.out.println("The new key should be smaller");
    }
  }
  public void print(){
    BinomialTreeNode<K, V> tmp = this.head;
    if (tmp != null) {
//      tmp.printNode();
    }
  }
  @Override
  public void delete(BinomialTreeNode<K, V> node, K minKey) {
    decreaseKey(node, minKey);
    extractMin();
  }

  @Override
  public BinomialTreeNode<K, V> getHead() {
    return this.head;
  }

  @Override
  public void setHead(BinomialTreeNode<K, V> newHead) {
    this.head = newHead;
  }

  public static void main(String[] args) {
    MyBinomialHeap<Integer, String> heap = new MyBinomialHeap<>();
    int[] arr = new int[] {7,2,4,17,1,11,6,8,15,10,20,5};
    for (int i: arr) {
      BinomialTreeNode<Integer, String> tmp = new BinomialTreeNode<>(i, String.valueOf(i));
      heap.insert(tmp);
    }
//

    // test decrease key and delete
//    BinomialTreeNode<Integer, String> node1 = new BinomialTreeNode<>(5, "5");
//    heap.insert(node1);
//    BinomialTreeNode<Integer, String> node2 = new BinomialTreeNode<>(2, "2");
//    heap.insert(node2);
//    BinomialTreeNode<Integer, String> node3 = new BinomialTreeNode<>(15, "15");
//    heap.insert(node3);
//    BinomialTreeNode<Integer, String> node4 = new BinomialTreeNode<>(4, "4");
//    heap.insert(node4);
//    BinomialTreeNode<Integer, String> node5 = new BinomialTreeNode<>(10, "10");
//    heap.insert(node5);
//    BinomialTreeNode<Integer, String> node6 = new BinomialTreeNode<>(1, "1");
//    heap.insert(node6);
//    System.out.println("current minimum key is " + heap.minimum().getKey());
//    heap.decreaseKey(node3, 0);
//    System.out.println("After decrease one other key to 0, new minimum kwy is " + heap.minimum().getKey());
//    heap.delete(heap.minimum(), -1000);
//    System.out.println("After deleting the minimum key, new min key is " + heap.minimum().getKey());
    // part 2 insert and extractMin
//    Random ran = new Random();
//    for (int i = 0; i < 35; ++i) {
//    /*int j = ran.nextInt(100);*/
//      int j = i;
//      BinomialTreeNode<Integer, String> tmp = new BinomialTreeNode<>(j,String.valueOf(j));
//      heap.insert(tmp);
//    }
//    IBinomialHeap<Integer, String> heap2 = new MyBinomialHeap<>();
//    for (int i = 0; i < 15; ++i) {
//      heap2.insert(new BinomialTreeNode<>(i+100, String.valueOf(i+100)));
//    }
//    IBinomialHeap.heapUnion(heap, heap2);
//    while (heap.minimum() != null) {
//      System.out.println(heap.extractMin().getVal());
//    }
  }
}
