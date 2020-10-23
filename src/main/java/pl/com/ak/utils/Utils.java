package pl.com.ak.utils;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class Utils {

  private Utils() {
    throw new IllegalArgumentException();
  }

  public static Deque<Integer> splitIntoParts(final Integer count, final Integer parts) {
    final Deque<Integer> list = new ArrayDeque<>();
    int sum = count;
    for (int i = 0; i < parts; i++) {
      int value = (sum + parts - i - 1) / (parts - i);
      list.push(value);
      sum -= value;
    }
    return list;
  }
}
