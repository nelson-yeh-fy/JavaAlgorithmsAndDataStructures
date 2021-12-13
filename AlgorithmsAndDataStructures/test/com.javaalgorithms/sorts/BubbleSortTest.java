import org.testng.annotations.Test;
import java.util.Random;
import com.javaalgorithms.sorts.BubbleSort;
import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.Assert.assertTrue;

class BubbleSortTest {

    private static final Random RANDOM = new Random();
    private static final int SIZE = 10000;

    private static Integer[] unsorted;
    private static Integer[] sorted;
    private static Integer[] reverse;

    static {
        unsorted = new Integer[SIZE];
        int i = 0;
        while (i < unsorted.length) {
            int j = RANDOM.nextInt(unsorted.length * 10);
            unsorted[i++] = j;
        }

        sorted = new Integer[SIZE];
        for (i = 0; i < sorted.length; i++)
            sorted[i] = i;

        reverse = new Integer[SIZE];
        for (i = (reverse.length - 1); i >= 0; i--)
            reverse[i] = (SIZE - 1) - i;
    }

    @Test
    public void testBubbleSorts() {
        // Bubble sort
        Integer[] result = BubbleSort.sort(unsorted.clone());
        assertArrayEquals(result, sorted);
    }
}