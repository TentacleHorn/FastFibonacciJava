package performance.ignore.testing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static performance.PerformanceUtils.ignore;

public class ComparePrimitive {
    public static class MyInteger {
        public int v;
        MyInteger(int v) {
            this.v = v;
        }
    }

    private static int size = 50000000;
    private static int valueRange = 100;
    private static Random rng = new Random();
    private static int[] data;
    private static MyInteger[] dataClass;
    private static ArrayList<Integer> dataArray;


    @BeforeAll
    static void setup() {
        data = new int[size];
        dataClass = new MyInteger[size];
        dataArray = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int v = rng.nextInt(valueRange);
            data[i] = v;
            dataClass[i] = new MyInteger(v);
            dataArray.add(v);
        }
    }

    @Test
    void iterateArray() {
        int sum = 0;
        for (int i=0; i < size; i++) {
            sum += dataArray.get(i);
        }
        ignore(sum);
        assertEquals(1, 1);
    }

    @Test
    void iterateClass() {
        int sum = 0;
        for (int i=0; i < size; i++) {
            sum += dataClass[i].v;
        }
        ignore(sum);
        assertEquals(1, 1);
    }

    @Test
    void iteratePrimitive() {
        int sum = 0;
        for (int i=0; i < size; i++) {
            sum += data[i];
        }
        ignore(sum);
        assertEquals(1, 1);
    }
}
