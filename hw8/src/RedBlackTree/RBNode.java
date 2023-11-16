package RedBlackTree;

/**
 * ClassName: RBNode
 * Package: RedBlackTree
 * Description: The red black tree node is a subclass of TreeNode but with color.
 *
 * @Author: Junchao Zhu
 */
public class RBNode<K extends Comparable<K>, V> extends TreeNode<K, V> {
  private int color; // 0 is black, 1 is white

  public RBNode(K key, V val) {
    super(key, val);
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }
}
