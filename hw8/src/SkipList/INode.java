package SkipList;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ClassName: SkipList.INode
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: Junchao Zhu
 */
public interface INode {
  /**
   * Insert a new node to the right of the current node.
   * @param num the value of the new node
   * @return the inserted node
   */
  INode insertToRight(int num);

  /**
   * Insert a new node to the upper level of the current node.
   * @param num the value of the new node
   * @return the inserted node
   */
  INode insertToUp(int num);

  /**
   * Delete a specified node.
   * @param node the node to delete
   */
  void delete(INode node);

  /**
   * @return the number of the node.
   */
  int getValue();
  INode getLeft();
  INode getRight();
  INode getUp();
  INode getDown();

}
