package LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ClassName: MyLinkedList
 * Package: HashTable
 * Description: My implementation of the linkedList for the hash map data structure.
 *
 * @Author: Junchao Zhu
 */
public class MyLinkedList<T> implements IList<T> {
  private class Node {
    public T val;
    public Node pre;
    public Node next;
    public Node(T val) {
      this.val = val;
      this.pre = null;
      this.next = null;
    }
  }
  private class MyIterator implements Iterator<T> {
    private int index = 0;
    private Node current = head.next;
    @Override
    public boolean hasNext() {
      return index < size;
    }

    @Override
    public T next() throws NoSuchElementException {
      if (!hasNext()) {
        throw new NoSuchElementException();
      } else {
        ++index;
        T res = current.val;
        current = current.next;
        return res;
      }
    }
  }
  private Node head;
  private Node tail;
  private int size;

  public MyLinkedList () {
    this.head = new Node(null);
    this.tail = new Node(null);
    head.next = tail;
    tail.pre = head;
    this.size = 0;
  }

  @Override
  public T get(int i) {
    if (i >= this.size) {
      return null;
    }
    Node current = head.next;
    for (int j = 0; j < i; ++j) {
      current = current.next;
    }
    return current.val;
  }

  @Override
  public void addFirst(T element) {
    Node newNode = new Node(element);
    newNode.next = head.next;
    newNode.pre = head;
    head.next = newNode;
    newNode.next.pre = newNode;
    this.size++;
  }

  @Override
  public T remove(int i) {
    if (i >= this.size) {
      return null;
    }
    this.size--;
    Node current = head.next;
    for (int j = 0; j < i; ++j) {
      current = current.next;
    }
    current.pre.next = current.next;
    current.next.pre = current.pre;
    return current.val;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public void add(T element) {
    Node newNode = new Node(element);
    newNode.pre = tail.pre;
    newNode.next = tail;
    tail.pre = newNode;
    newNode.pre.next = newNode;
    this.size++;
  }

  @Override
  public Iterator<T> iterator() {
    return new MyIterator();
  }
}
