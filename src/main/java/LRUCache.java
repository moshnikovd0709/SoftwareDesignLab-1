import org.jetbrains.annotations.NotNull;

import java.util.*;

public class LRUCache<S, T> {

    private final int maxSize;
    private final MyLinkedList<S, T> myLinkedList;
    private final Map<S, MyNode<S, T>> map;

    public LRUCache(int maxSize) {
        if (maxSize >= 1) {
            this.maxSize = maxSize;
            myLinkedList = new MyLinkedList<>();
            map = new HashMap<>();
        } else {
            throw new RuntimeException("maxSize less than one");
        }
    }

    public int getSize() {
        return myLinkedList.getSize();
    }

    public void put(@NotNull S key, @NotNull T value) {
        assert getSize() <= maxSize;

        int size0 = getSize();
        remove(key);

        assert getSize() <= size0;

        putFirst(key, value);

        assert getSize() <= maxSize;
        assert size0 <= getSize();
        assert myLinkedList.head.key == key && myLinkedList.head.value == value;
        assert map.get(key).key == key;
        assert map.get(key).value == value;
    }

    public T get(@NotNull S key) {
        assert getSize() <= maxSize;

        int size0 = getSize();
        T result = remove(key);

        assert getSize() <= size0;

        if (result != null) {
            putFirst(key, result);
        }

        assert getSize() == size0;
        assert getSize() <= maxSize;
        assert myLinkedList.head.key == key || result == null;

        return result;
    }

    private void putFirst(S key, T value) {
        MyNode<S, T> resNode = myLinkedList.addFirst(key, value);
        if (myLinkedList.getSize() == maxSize + 1) {
            MyNode<S, T> removedNode = myLinkedList.removeLast();
            map.remove(removedNode.key);
        }
        map.put(key, resNode);

        assert resNode.equals(myLinkedList.head);
        assert getSize() <= maxSize;
    }

    private T remove(S key) {
        assert getSize() <= maxSize;

        int size0 = getSize();
        if (map.containsKey(key)) {
            MyNode<S, T> oldNode = map.get(key);
            T resValue = oldNode.value;
            myLinkedList.remove(oldNode);
            map.remove(key);

            assert getSize() <= size0;
            assert getSize() <= maxSize;

            return resValue;
        }

        return null;
    }
}
