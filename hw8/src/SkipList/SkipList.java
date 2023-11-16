package SkipList;

import java.util.Random;

/**
 * ClassName: SkipList.SkipList
 * Package: PACKAGE_NAME
 * Description: The implementation of skiplist.
 *
 * @Author: Junchao Zhu
 */
public class SkipList implements ISkipList {
  private INode topLeft;
  private INode current;

  /**
   * Initialize the skip list with given number.
   */
  public SkipList() {
    topLeft = new MyNode(Integer.MIN_VALUE); // we assume that MIN_VALUE won't be the element of the skip list
  }

  /**
   * @return a random boolean value.
   */
  private boolean flipCoin() {
    boolean res =  new Random().nextBoolean();
    System.out.println("The coin flip result is " + res);
    return res;
  }

  @Override
  public boolean lookup(int num) {
    if (this.topLeft.getValue() > num) {
      // the number to find is less the smallest node of the skip list
      return false;
    }
    current = topLeft;
    for (; ; ) {
      // always move to the right node unless the value of the right node is greater than num
      while (current.getRight() != null && current.getRight().getValue() <= num) {
        System.out.println("Move to the right node since " + num + " is >= the right node value: " + current.getRight().getValue());
        current = current.getRight();
      }
      // move to lower level when unable to move to the right node
      if (current.getDown() != null) {
        if (current.getRight() != null) {
          System.out.println("Move to the lower level node since the right node has bigger value:" + current.getRight().getValue() + " than " + num);
        } else {
          System.out.println("Move to the lower level node since the right node is null");
        }
        current = current.getDown();
      } else {
        // if we cannot move to either the right node or the lower node, we will stop and do a final check
        if (current.getValue() == num) {
          System.out.println("Find the num in the skip list: " + num);
          return true;
        }
        break;
      }
    }
    System.out.println("Not found number " + num);
    return false;
  }

  @Override
  public void insert(int num) {
    if (!lookup(num)) {
      System.out.println("Insert " + num + " at the lowest level");
      current = current.insertToRight(num);
      while (flipCoin()) {
        System.out.println("Will add " + num + " to the next upper level");
        // when we need to add to next level up
        current = current.insertToUp(num);
        if (current.getLeft().getLeft() == null && current.getLeft().getUp() == null) {
          topLeft = current.getLeft();//update top left
        }
      }
    }
  }

  @Override
  public void delete(int num) {
    if (lookup(num)) {
      // if we find the num, then delete all occurrence
      System.out.println("Now delete all occurrence of " + num + " in the skip list");
      while (current != null) {
        INode up = current.getUp();
        current.delete(current);
        current = up;
      }
    } else {
      System.out.println("No need to delete " + num);
    }
  }

  /**
   * Print the skip list.
   */
  private void print() {
    INode tmp = topLeft;
    int level = 1;
    while (tmp != null) {
      INode next = tmp;

      System.out.print("level " + level + ": ");
      while (next != null) {
        System.out.print(next.getValue() + " ");
        next = next.getRight();
      }
      System.out.println("");
      tmp = tmp.getDown();
      ++level;
    }
    System.out.println("Print complete!");
  }

  public static void main(String[] args) {
    SkipList sl = new SkipList();
    sl.print();
    sl.insert(40);
    sl.print();
    sl.insert(10);
    sl.print();
    sl.insert(20);
    sl.print();
    sl.insert(5);
    sl.print();
    sl.insert(80);
    sl.print();
    sl.delete(20);
    sl.print();
    sl.insert(100);
    sl.print();
    sl.insert(20);
    sl.print();
    sl.insert(30);
    sl.print();
    sl.delete(5);
    sl.print();
    sl.delete(0);
    sl.print();
    sl.delete(40);
    sl.print();
    sl.insert(50);
    sl.print();
    sl.delete(50);
    sl.print();
    System.out.println(sl.lookup(80));
    System.out.println(sl.lookup(7));
  }
}
