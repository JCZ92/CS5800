package HashTable;

import LinkedList.IList;

/**
 * ClassName: HashTable.IHashTable
 * Package: PACKAGE_NAME
 * Description: The interface of HashTable.
 *
 * @Author: Junchao Zhu
 */
public interface IHashTable {
  /**
   * Insert a key value pair into the hash table.
   * @param key a String as a key
   * @param value count of the key
   */
  void insert(String key, int value);

  /**
   * Delete a key in the hash table.
   * @param key a String as a key
   */
  void delete (String key);

  /**
   * Increase the value of the key.
   * @param key a String as a key.
   */
  void increase(String key);

  /**
   * Search the key in the hash table and return its count, if not found, return 0.
   * @param key s Sting as a key
   * @return count of the key
   */
  int find(String key);

  /**
   * @return a list of key-count pair of the hash table.
   */
  IList<String> listAllKeys();
}
