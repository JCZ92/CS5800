package RedBlackTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

/**
 * ClassName: RedBlackTree
 * Package: RedBlackTree
 * Description: The implementation of red-black tree.
 *
 * @Author: Junchao Zhu
 */
class RedBlackTree<K extends Comparable<K>, V> implements IBST<K, V> {
  private RBNode<K, V> root;
  private RBNode<K, V> sentinel;
  private int size;
  @Override
  public ITreeNode<K, V> search(K key) {
    return root.search(key, sentinel);
  }
  public RedBlackTree() {
    this.size = 0;
    this.sentinel = new RBNode<K, V>(null, null);
    this.sentinel.setColor(1);
    this.root = sentinel;
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
        if (tmp != this.sentinel) {
          queue.add(tmp.getLeft());
          queue.add(tmp.getRight());
          System.out.print(tmp.getVal() + " ");
        } else {
          System.out.print("() ");
        }
      }
      System.out.println("");
    }
  }

  @Override
  public void delete(ITreeNode<K, V> node) {
    throw new UnsupportedOperationException();
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
  /**
   * Rotate the node to left.
   * @param x the node to rotate
   */
  private void LeftRotate (ITreeNode<K, V> x) {
    RBNode<K, V> y = (RBNode<K, V>) x.getRight();
    x.setRight(y.getLeft());
    if (y.getLeft() != this.sentinel) {
      y.getLeft().setParent(x);
    }
    y.setParent(x.getParent());
    if (x.getParent() == this.sentinel) {
      this.root = y;
    } else if (x == x.getParent().getLeft()) {
      x.getParent().setLeft(y);
    } else {
      x.getParent().setRight(y);
    }
    y.setLeft(x);
    x.setParent(y);
  }

  /**
   * Rotate the node to right.
   * @param y the node to rotate
   */
  private void RightRotate(RBNode<K, V> y) {
    RBNode<K, V> x = (RBNode<K, V>) y.getLeft();
    y.setLeft(x.getRight());
    if (x.getRight() != this.sentinel) {
      x.getRight().setParent(y);
    }
    x.setParent(y.getParent());
    if (y.getParent() == this.sentinel) {
      this.root = x;
    } else if (y == y.getParent().getLeft()) {
      y.getParent().setLeft(x);
    } else {
      y.getParent().setRight(x);
    }
    x.setRight(y);
    y.setParent(x);
  }
  private void insertFixUp(RBNode<K, V> z) {
    while (((RBNode<K, V>)z.getParent()).getColor() == 0) {
      if (z.getParent() == z.getParent().getParent().getLeft()) {
        RBNode<K, V> y = (RBNode<K, V>) z.getParent().getParent().getRight();
        if (y.getColor() == 0) {
          ((RBNode<K, V>) z.getParent()).setColor(1);
          y.setColor(1);
          ((RBNode<K, V>)z.getParent().getParent()).setColor(0);
          z = (RBNode<K, V>) z.getParent().getParent();
        } else {
          if (z == z.getParent().getRight()) {
            z = (RBNode<K, V>) z.getParent();
            this.LeftRotate(z);
          }
          ((RBNode<K, V>) z.getParent()).setColor(1);
          ((RBNode<K, V>)z.getParent().getParent()).setColor(0);
          this.RightRotate((RBNode<K, V>) z.getParent().getParent());
        }
      } else {
        RBNode<K, V> y = (RBNode<K, V>) z.getParent().getParent().getLeft();
        if (y.getColor() == 0) {
          ((RBNode<K, V>) z.getParent()).setColor(1);
          y.setColor(1);
          ((RBNode<K, V>)z.getParent().getParent()).setColor(0);
          z = (RBNode<K, V>) z.getParent().getParent();
        } else {
          if (z == z.getParent().getLeft()) {
            z = (RBNode<K, V>) z.getParent();
            this.RightRotate(z);
          }
          ((RBNode<K, V>) z.getParent()).setColor(1);
          ((RBNode<K, V>)z.getParent().getParent()).setColor(0);
          this.LeftRotate((RBNode<K, V>) z.getParent().getParent());
        }
      }
    }
    this.root.setColor(1);
  }

  @Override
  public void insert(ITreeNode<K, V> node) {
    if (!(node instanceof RBNode)) return;
    RBNode<K, V> x = this.root;
    RBNode<K, V> y = this.sentinel;
    while (x != this.sentinel) {
      y = x;
      if (node.getKey().compareTo(x.getKey()) < 0) {
        x = (RBNode<K, V>) x.getLeft();
      } else {
        x = (RBNode<K, V>) x.getRight();
      }
    }
    node.setParent(y);
    if (y == this.sentinel) {
      this.root = (RBNode<K, V>) node;
    } else if (node.getKey().compareTo(y.getKey()) < 0) {
      y.setLeft(node);
    } else {
      y.setRight(node);
    }
    node.setLeft(this.sentinel);
    node.setRight(this.sentinel);
    ((RBNode<K, V>) node).setColor(0); // set to red
    insertFixUp((RBNode<K, V>)node);
    ++size;
  }
  private static List<Integer> readIntegersFromFile(String filePath) {
    List<Integer> result = new LinkedList<>();
    try (Scanner scanner = new Scanner(new File(filePath))) {
      // Assuming integers are separated by spaces
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] tokens = line.split("\\s+");
        for (int i = 0; i < tokens.length; i++) {
          result.add(Integer.parseInt(tokens[i]));
        }
      }

    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + filePath);
      e.printStackTrace();
    } catch (NumberFormatException e) {
      System.err.println("Error converting string to integer");
      e.printStackTrace();
    }
    return result;
  }
  private static void processCommand(String command, RedBlackTree bst) {
    String[] tokens = command.split("\\s+");
    String action = tokens[0];
    int value = 0;
    if (tokens.length > 1) {
      try {
        value = Integer.parseInt(tokens[1]);
      } catch (NumberFormatException e) {
        System.out.println("Invalid value. Please provide a valid integer.");
        return;
      }
    }

    switch (action.toLowerCase()) {
      case "insert":
        bst.insert(new RBNode<>(value, String.valueOf(value)));
        System.out.println("Inserted " + value + " into the BST.");
        break;
      case "search":
        ITreeNode<Integer, String> result = bst.search(value);
        if (result != null) {
          System.out.println("Found " + value + " in the BST.");
        } else {
          System.out.println(value + " not found in the BST.");
        }
        break;
      case "exit":
        System.out.println("Exiting the program. Goodbye!");
        break;
      case "sort":
        bst.sort();
        break;
      case "minimum":
        System.out.println(bst.minimum());
        break;
      case "maximum":
        System.out.println(bst.maximum());
        break;
      case "predecessor":
        ITreeNode<Integer, String> tmp = bst.search(value);
        if (bst.predecessor(tmp) == null) {
          System.out.println("No predecessor of the min element.");
        } else {
          System.out.println(bst.predecessor(tmp));
        }
        break;
      case "successor":
        ITreeNode<Integer, String> tmp2 = bst.search(value);
        if (bst.successor(tmp2) == null) {
          System.out.println("No successor for the max element");
        } else {
          System.out.println(bst.successor(tmp2));
        }
        break;
      case "print":
        bst.print();
        break;
      default:
        System.out.println("Unknown command. Please enter 'insert', 'search', or 'exit'.");
    }
  }
  public static void main(String[] args) {
//    RedBlackTree<Integer, String> bst = new RedBlackTree<Integer, String>();
//    Random ran = new Random();
//    for (int i = 0; i < 35; ++i) {
//      int j = ran.nextInt(50);
//      ITreeNode<Integer, String> tmp = new RBNode<Integer, String>(j, String.valueOf(j));
//      bst.insert(tmp);
//    }
//    bst.print();
//    bst.sort();
//    System.out.println("");
//    System.out.println(bst.minimum());
//    System.out.println(bst.maximum());
//
//
//    RedBlackTree<Integer, String> bst2 = new RedBlackTree<Integer, String>();
//    ITreeNode<Integer, String> node1 = new RBNode<>(5, "5");
//    bst2.insert(node1);
//    ITreeNode<Integer, String> node2 = new RBNode<>(3, "3");
//    bst2.insert(node2);
//    ITreeNode<Integer, String> node3 = new RBNode<>(6, "6");
//    bst2.insert(node3);
//    ITreeNode<Integer, String> node4 = new RBNode<>(7, "7");
//    bst2.insert(node4);
//    ITreeNode<Integer, String> node5 = new RBNode<>(1, "1");
//    bst2.insert(node5);
//    bst2.print();
//    System.out.println("");
//    ITreeNode<Integer, String> tmp = node5.predecessor(bst2.sentinel);
//    System.out.println("Predecessor of " + node5.getVal() + " is "  + node5.predecessor(bst2.sentinel));
//    System.out.println("Successor of " + node2.getVal() + " is "  + node2.successor(bst2.sentinel));
//    System.out.println("Successor of " + node5.getVal() + " is "  + node5.successor(bst2.sentinel));
    String filePath = "int.txt";
    List<Integer> ints = readIntegersFromFile(filePath);
    RedBlackTree<Integer, String> bst = new RedBlackTree<Integer, String>();
    for (int i : ints) {
      ITreeNode<Integer, String> tmp = new RBNode<Integer, String>(i, String.valueOf(i));
      bst.insert(tmp);
    }
    bst.print();

    Scanner scanner = new Scanner(System.in);
    String command;
    System.out.println("Welcome to RedBlack tree. Type like 'insert 9, search 9, exit'.");
    do {
      System.out.print("Enter command: ");
      command = scanner.nextLine();
      processCommand(command, bst);
    } while (!command.equalsIgnoreCase("exit"));
    scanner.close();
  }
}