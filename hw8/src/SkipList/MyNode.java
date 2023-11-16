package SkipList;

/**
 * ClassName: MyList
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: Junchao Zhu
 */
public class MyNode implements INode {
  private final int value;
  private MyNode up;
  private MyNode down;
  private MyNode left;
  private MyNode right;
  public MyNode(int num) {
    this.value = num;
    this.up = null;
    this.down = null;
    this.left = null;
    this.right = null;
  }

  @Override
  public INode insertToRight(int num) {
    MyNode prevRight = this.right;
    MyNode newNode = new MyNode(num);
    this.right = newNode;
    newNode.left = this;
    newNode.right = prevRight;
    if (prevRight != null)
      prevRight.left = newNode;
    return newNode;
  }

  @Override
  public INode insertToUp(int num) {
    MyNode current = this;
    while (current.left != null && current.left.up == null) {
      current = current.left;
    }
//    Three cases
    // current.left == null && current.up == null
    if (current.left == null && current.up == null) {
      current.up = new MyNode(current.value);
      current.up.down = current;
      current = current.up;
    } else if (current.left == null && current.up != null) {
      // current.left == null && current.up != null
      current = current.up;
    } else {
      // current.left != null && current.left.up != null
      current = current.left.up;
    }
    MyNode res = (MyNode) current.insertToRight(num);
    this.up = res;
    res.down = this;
    return res;
  }

  @Override
  public void delete(INode node) {
    if(!(node instanceof MyNode)) {
      // type error
      return;
    }
    MyNode tmp = (MyNode) node;
    if (tmp.left != null) {
      tmp.left.right = tmp.right;
      if (tmp.right != null) {
        tmp.right.left = tmp.left;
      }
      tmp.left = null;

    }
    tmp.right = null;
    tmp.up = null;
    tmp.down = null;
  }

  @Override
  public int getValue() {
    return this.value;
  }

  public MyNode getUp() {
    return up;
  }

  public MyNode getDown() {
    return down;
  }

  public MyNode getLeft() {
    return left;
  }

  public MyNode getRight() {
    return right;
  }
}
