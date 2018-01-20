import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class ArraySearchTest {

    @Test
    public void testEmptyList() {
        Integer[] arr = new Integer[]{};

        assertEquals(ArraySearch.include(arr, 5), false);
    }

    @Test(expected = NullPointerException.class)
    public void testNullList() {
        Integer[] arr = null;

        ArraySearch.include(arr, 5);
    }

    @Test
    public void testFindNullValueFalse() {
        Integer[] arr = new Integer[]{1,2,3,4,5,6};

        assertEquals(ArraySearch.include(arr, null), false);
    }

    @Test
    public void testFindNullValueTrue() {
        Integer[] arr = new Integer[]{1,2,3,4,5,6, null};

        assertEquals(ArraySearch.include(arr, null), true);
    }

    @Test
    public void testFindValueTrue() {
        Integer[] arr = new Integer[]{1,2,3,4,5,6, null};

        assertEquals(ArraySearch.include(arr, 1), true);
    }

    @Test
    public void testFindValueFalse() {
        Integer[] arr = new Integer[]{1,2,3,4,5,6, null};

        assertEquals(ArraySearch.include(arr, -1), false);
    }

    @Test
    public void testFindObjectFalse() {
        Object[] arr = new Object[]{1, "a", true, null, new BigDecimal(5)};

        assertEquals(ArraySearch.include(arr, 5), false);
    }

    @Test
    public void testFindObjectTrue() {
        Object[] arr = new Object[]{1, "a", true, null, new BigDecimal(5)};

        assertEquals(ArraySearch.include(arr, new BigDecimal(5)), true);
    }
}