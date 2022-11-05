import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LRUCacheTest {


    @Test
    void oneObjectTest() {
        LRUCache<Integer, String> cache = new LRUCache<>(10);
        cache.put(0, "value0");
        assertNull(cache.get(1));
        assertEquals("value0", cache.get(0));
    }

    @Test
    void severalObjectsTest() {
        LRUCache<Integer, String> cache = new LRUCache<>(10);
        cache.put(0, "value0");
        cache.put(1, "value1");
        cache.put(2, "value2");
        assertNull(cache.get(100));
        assertEquals("value0", cache.get(0));
        assertEquals("value2", cache.get(2));
        assertEquals("value1", cache.get(1));
        assertEquals("value1", cache.get(1));
    }

    @Test
    void overflowSizeOneObjectTest() {
        LRUCache<String, String> cache = new LRUCache<>(1);
        cache.put("0", "value0");
        cache.put("1", "value1");
        assertNull(cache.get("0"));
        assertEquals("value1", cache.get("1"));
    }

    @Test
    void overflowSizeSeveralObjectsTest() {
        LRUCache<String, String> cache = new LRUCache<>(2);
        cache.put("0", "value0");
        cache.put("1", "value1");
        cache.put("2", "value2");
        cache.put("3", "value3");
        assertNull(cache.get("0"));
        assertNull(cache.get("1"));
        assertEquals("value2", cache.get("2"));
        assertEquals("value3", cache.get("3"));
        cache.put("1", "value1");
        assertEquals("value3", cache.get("3"));
        assertEquals("value1", cache.get("1"));
        assertNull(cache.get("key2"));
        assertNull(cache.get("key0"));
    }

    @Test
    void putOnFirstPositionTest() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(2);
        cache.put(0, 0);
        cache.put(1, 1);
        assertEquals(0, 0);
        cache.put(2, 2);
        assertNull(cache.get(0));
        assertEquals(2, cache.get(2));
        assertEquals(1, cache.get(1));
        cache.put(3, 3);
        assertNull(cache.get(2));
    }

}
