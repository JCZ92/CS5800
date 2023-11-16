package RedBlackTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * ClassName: BinarySearchTree
 * Package: RedBlackTree
 * Description:
 *
 * @Author: Junchao Zhu
 */
public class SimpleBST<K extends Comparable<K>, V> implements IBST<K, V> {
  private TreeNode<K, V> root;
  private TreeNode<K, V> sentinel;
  private int size;

  public SimpleBST() {
    this.size = 0;
    this.sentinel = new TreeNode<K, V>(null, null);
    this.root = sentinel;
  }

  @Override
  public ITreeNode<K, V> search(K key) {
    return root.search(key, sentinel);
  }

  @Override
  public ITreeNode<K, V> minimum() {
    return root.minimum(sentinel);
  }

  @Override
  public ITreeNode<K, V> maximum() {
    return root.maximum(sentinel);
  }

  @Override
  public void sort() {
    root.sort(sentinel);
  }

  @Override
  public void print() {
    Queue<ITreeNode<K, V>> queue = new LinkedList<>();
    queue.add(this.root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; ++i) {
        ITreeNode<K, V> tmp = queue.poll();
        if (tmp != sentinel) {
          queue.add(tmp.getLeft());
          queue.add(tmp.getRight());
          System.out.print(tmp.getVal() + " ");
        } else {
          System.out.print("() ");
        }
      }
      System.out.println("");
    }
    System.out.println("");
  }

  @Override
  public void insert(ITreeNode<K, V> node) {
    if (!(node instanceof TreeNode)) return;
    if (node.getKey() != null) {
      ITreeNode<K, V> current = root;
      ITreeNode<K, V> y = sentinel;
      while (current != sentinel) {
        y = current;
        if (node.getKey().compareTo(current.getKey()) < 0) {
          current = current.getLeft();
        } else {
          current = current.getRight();
        }
      }
      node.setParent(y);
      if (y == sentinel) {
        this.root = (TreeNode<K, V>) node;
      } else if (node.getKey().compareTo(y.getKey()) < 0) {
        y.setLeft(node);
      } else {
        y.setRight(node);
      }
      node.setLeft(this.sentinel);
      node.setRight(this.sentinel);
      ++size;
    }
  }

  private void transplant(ITreeNode<K, V> u, ITreeNode<K, V> v) {
    if (u != null && v instanceof ITreeNode) {
      if (u.getParent() == sentinel) {
        this.root = (TreeNode<K, V>) v;
      } else if (u == u.getParent().getLeft()) {
        u.getParent().setLeft(v);
      } else {
        u.getParent().setRight(v);
      }
      if (v != null) {
        v.setParent(u.getParent());
      }
    }
  }
  @Override
  public void delete(ITreeNode<K, V> node) {
    if (node != null) {
      --size; // assume node is in the tree
      if (node.getLeft() == sentinel) {
        transplant(node, node.getRight());
      } else if (node.getRight() == sentinel) {
        transplant(node, node.getLeft());
      } else {
        ITreeNode<K, V> successor = node.getRight().minimum(sentinel);
        if (successor != node.getRight()) {
          transplant(successor, successor.getRight());
          successor.setRight(node.getRight());
          successor.getRight().setParent(successor);
        }
        transplant(node, successor);
        successor.setLeft(node.getLeft());
        successor.getLeft().setParent(successor);
      }
    }
  }

  @Override
  public ITreeNode<K, V> successor(ITreeNode<K, V> node) {
    ITreeNode<K, V> res = node.successor(this.sentinel);
    if (res == sentinel) return null;
    return res;
  }

  @Override
  public ITreeNode<K, V> predecessor(ITreeNode<K, V> node) {
    ITreeNode<K, V> res = node.predecessor(this.sentinel);
    if (res == sentinel) return null;
    return res;
  }

  public TreeNode<K, V> getSentinel() {
    return sentinel;
  }

  public static void main(String[] args) {
    SimpleBST<Integer, String> bst = new SimpleBST<Integer, String>();
    ITreeNode<Integer, String> node1 = new TreeNode<>(5, "5");
    bst.insert(node1);
    ITreeNode<Integer, String> node2 = new TreeNode<>(3, "3");
    bst.insert(node2);
    ITreeNode<Integer, String> node3 = new TreeNode<>(6, "6");
    bst.insert(node3);
    ITreeNode<Integer, String> node4 = new TreeNode<>(7, "7");
    bst.insert(node4);
    ITreeNode<Integer, String> node5 = new TreeNode<>(1, "1");
    bst.insert(node5);
    bst.print();
    System.out.println("");
    System.out.println("Search key = 3: " + bst.search(3));
    System.out.println("Min is: " + bst.minimum());
    System.out.println("Max is " + bst.maximum());
    System.out.println("Predecessor of " + node2.getVal() + " is "  + node2.predecessor(bst.sentinel));
    System.out.println("Successor of " + node2.getVal() + " is "  + node2.successor(bst.sentinel));
    System.out.println("Successor of " + node5.getVal() + " is "  + node5.successor(bst.sentinel));
//    System.out.println(node3.minimum(bst.sentinel));
//    System.out.println(node3.maximum(bst.sentinel));
    bst.delete(node1);
    bst.delete(node2);
    bst.delete(node3);
    bst.delete(node4);
    System.out.println("After deletion most keys");
    bst.print();
    System.out.println(" ");

    IBST<Integer, String> bst2 = new SimpleBST<Integer, String>();
    Random ran = new Random();
    for (int i = 0; i < 15; ++i) {
      int j = ran.nextInt(50);
      ITreeNode<Integer, String> tmp = new TreeNode<>(j, String.valueOf(j));
      bst2.insert(tmp);
    }
    bst2.print();
    bst2.sort();
  }
}
