package HashTable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import LinkedList.IList;
import LinkedList.MyLinkedList;

/**
 * ClassName: myHashTable
 * Package: PACKAGE_NAME
 * Description: One implementation of the HashTable.IHashTable.
 *
 * @Author: Junchao Zhu
 */
public class MyHashTable implements IHashTable {
  private final Integer max;
  private final MyLinkedList<Pair>[] map;
  private final int PRIME = 31;
  private int size; // how many keys are in the hash table

  /**
   * The class for each key value pair in the hash table.
   */
  private class Pair {
    public String key;
    public Integer value;

    public Pair(String key, int value) {
      this.key = key;
      this.value = value;
    }
  }
  /**
   * @param max is the capacity of the hashTable
   */
  public MyHashTable(int max) {
    this.max = max;
    this.map = new MyLinkedList[this.max];
    size = 0;
  }

  /**
   * Hash function.
   * @param key is a string
   * @return hash code
   */
  private int hash(String key) {
    if (key == null) {
      return 0;
    }
    int hash = 0;
    for (int i = 0; i < key.length(); i++) {
      hash = (hash * PRIME) + key.charAt(i);
    }
    return hash & 0x7FFFFFFF; // avoid negative value
  }

  @Override
  public void insert(String key, int value) {
    int index = hash(key) % this.max;
    if (map[index] == null) {
      map[index] = new MyLinkedList<>();
    }
    for (Pair pair: map[index]) {
      if (pair.key.equals(key)) {
        // when we find a match, update value and return
        pair.value = value;
        return;
      }
    }
    // when there is no match, add the pair as new head of the list
    map[index].addFirst(new Pair(key, value));
    ++size;
  }

  @Override
  public void delete(String key) {
    if (find(key) == 0) {
      return; // return directly when key is not found
    }
    int index = hash(key) % this.max;
    // when we find a match, delete it
    for (int i = 0; i < map[index].size(); ++i) {
      if (map[index].get(i).key.equals(key)) {
        map[index].remove(i);
        --size;
        return;
      }
    }
  }

  @Override
  public void increase(String key) {
    int index = hash(key) % this.max;
    if (find(key) == 0) {
      // if key does not exist yet, do insert(key, 1)
      insert(key, 1);
      return;
    }
    for (Pair pair: map[index]) {
      if (pair.key.equals(key)) {
        // when we find a match, increment count and return
        pair.value++;
        return;
      }
    }
  }

  @Override
  public int find(String key) {
    int index = hash(key) % this.max;
    if (map[index] == null) {
      return 0; // the key was not inserted before
    };
    for (Pair pair: map[index]) {
      if (pair.key.equals(key)) {
        // when we find a match, update value and return
        return pair.value;
      }
    }
    return 0;
  }

  @Override
  public IList<String> listAllKeys() {
    IList<String> res = new MyLinkedList<>();
    for (IList<Pair> list : map) {
      if (list == null) {
        continue;
      }
      for (Pair pair: list) {
        // convert to the Pair type defined in the interface
        res.add(String.format("%s = %d", pair.key, pair.value));
      }
    }
    return res;
  }
  public void summarize(String fileName) {
    double mean = (double) size / max;
    double var = 0;
    List<Integer> len = new ArrayList<>();
    for (IList<Pair> list : map) {
      if (list == null) {
        len.add(0);
        continue;
      }
      len.add(list.size());
      var += Math.pow(list.size() - mean, 2);
    }
    Path filePath = Paths.get(fileName + ".txt");
    Path filePath2 = Paths.get(fileName + "_len_distribution.txt");
    IList<String> pairs = listAllKeys();
    try {
      // Write the contents of the list to the file
      String meanStr = String.format("Expected collision list length is %f\n", mean);
      String varStr = String.format("Variance is %f\n", var / max);
      Files.write(filePath2, len.stream().map(String::valueOf).collect(Collectors.toList()));
      Files.write(filePath, meanStr.getBytes());
      Files.write(filePath, varStr.getBytes(), StandardOpenOption.APPEND);
      Files.write(filePath, "List of words and its count\n".getBytes(), StandardOpenOption.APPEND);
      Files.write(filePath, pairs, StandardOpenOption.APPEND);
      // sort len in descending order
      len.sort((a, b) -> b - a);
      Files.write(filePath, "Top 10% length\n".getBytes(), StandardOpenOption.APPEND);
      for (int i = 0; i < max * 0.1; ++i) {
        String str = String.format("%s\n", len.get(i).toString());
        Files.write(filePath, str.getBytes(), StandardOpenOption.APPEND);
      }

    } catch (IOException e) {
      // Handle IOException
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
//    int[] test = new int[] {30, 300, 3000};
//    for (int i : test) {
//      MyHashTable ht = new MyHashTable(i);
//      String filePathString = "./alice_in_wonderland.txt";
//
//      Path filePath = Paths.get(filePathString);
//
//      try {
//        // Read all lines from the file
//        List<String> lines = Files.readAllLines(filePath, StandardCharsets.ISO_8859_1);
//        // Parse each word from the lines
//        for (String line : lines) {
//          List<String> words =  Arrays.stream(line.split("\\s+"))
//                  .map(word -> word.replaceAll("[^a-zA-Z]", "")) //remove punctuations
//                  .filter(word -> !word.isEmpty()).collect(Collectors.toList());
//          for (String word: words) {
//            ht.increase(word);
//          }
//        }
//        ht.summarize("output" + i);
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
    // part b tests
    MyHashTable ht2 = new MyHashTable(5);
    ht2.insert("abc", 1);
    ht2.insert("def", 1);
    ht2.insert("ghi", 1);
    ht2.insert("jkl", 1);
    ht2.insert("mno", 1);
    ht2.insert("pqr", 1);
    for (String str : ht2.listAllKeys()) {
      System.out.println(str);
    }
    ht2.increase("def");
    ht2.increase("mno");
    ht2.increase("mno");
    ht2.delete("abc");
    System.out.println(ht2.find("sss"));
    ht2.delete("sss"); // does not exist
    for (String str : ht2.listAllKeys()) {
      System.out.println(str);
    }
  }
}
