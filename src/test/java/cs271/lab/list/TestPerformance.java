package cs271.lab.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPerformance {

  private final int SIZE = 1000;  // Increased for better observation
  private final int REPS = 1000000;

  private List<Integer> arrayList;
  private List<Integer> linkedList;

  @Before
  public void setUp() throws Exception {
    arrayList = new ArrayList<>(SIZE);
    linkedList = new LinkedList<>();
    for (int i = 0; i < SIZE; i++) {
      arrayList.add(i);
      linkedList.add(i);
    }
  }

  @After
  public void tearDown() throws Exception {
    arrayList = null;
    linkedList = null;
  }

  @Test
  public void testLinkedListAddRemove() {
    measurePerformance("LinkedList Add/Remove", () -> {
      for (int r = 0; r < REPS; r++) {
        linkedList.add(0, 77);
        linkedList.remove(0);
      }
    });
  }

  @Test
  public void testArrayListAddRemove() {
    measurePerformance("ArrayList Add/Remove", () -> {
      for (int r = 0; r < REPS; r++) {
        arrayList.add(0, 77);
        arrayList.remove(0);
      }
    });
  }

  @Test
  public void testLinkedListAccess() {
    measurePerformance("LinkedList Access", () -> {
      long sum = 0;
      for (int r = 0; r < REPS; r++) {
        sum += linkedList.get(r % SIZE);
      }
    });
  }

  @Test
  public void testArrayListAccess() {
    measurePerformance("ArrayList Access", () -> {
      long sum = 0;
      for (int r = 0; r < REPS; r++) {
        sum += arrayList.get(r % SIZE);
      }
    });
  }

  private void measurePerformance(String testName, Runnable test) {
    long startTime = System.nanoTime();
    test.run();
    long endTime = System.nanoTime();
    System.out.println(testName + " took " + (endTime - startTime) / 1_000_000.0 + " ms");
  }
}
