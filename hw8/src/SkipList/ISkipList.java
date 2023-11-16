package SkipList;

/**
 * ClassName: SkipList.ISkipList
 * Package: PACKAGE_NAME
 * Description: The interface of Skip List.
 *
 * @Author: Junchao Zhu
 */
public interface ISkipList {
  boolean lookup(int num);
  void insert(int num);
  void delete(int num);
}
