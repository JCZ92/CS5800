package LinkedList;

/**
 * ClassName: IList
 * Package: HashTable
 * Description:
 *
 * @Author: Junchao Zhu
 */
public interface IList<T> extends Iterable<T> {
  /**
   * Get the ith item in the list.
   * @param i the index of the item to get(zero indexed)
   * @return the ith item.
   */
  T get(int i);

  /**
   * Add the specified element to the head of the linked list.
   * @param element is the new element to add
   */
  void addFirst(T element);

  /**
   * Remove the ith element in the list.
   * @param i the index of the element to remove
   * @return the removed element
   */
  T remove(int i);

  /**
   * Add the element to the end of the list.
   * @param element  the element to add
   */
  void add(T element);

  /**
   * Return size of the list.
   */
  int size();
}
