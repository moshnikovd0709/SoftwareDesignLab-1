public class MyLinkedList<S, T> {

    private final MyNode<S, T> ROOT = new MyNode<>(null, null);
    MyNode<S, T> tail;
    MyNode<S, T> head;

    public int getSize() {
        return size;
    }

    private int size;

    MyLinkedList() {
        this.head = ROOT;
        this.tail = ROOT;
        this.size = 0;
    }

    void remove(MyNode<S, T> node) {
        int size0 = size;
        if (node == null) throw new RuntimeException("trying to remove null node");
        if (node.equals(ROOT)) throw new RuntimeException("trying to remove ROOT node");
        size--;
        MyNode<S, T> prev = node.previous;
        MyNode<S, T> next = node.next;
        if (node.equals(head)) head = (node.next == null) ? ROOT : node.next;
        if (node.equals(tail)) tail = node.previous;
        prev.next = next;
        if (next != null) next.previous = prev;

        assert node != tail;
        assert node != head;
        assert size0 - 1 == size;
    }
    MyNode<S, T> addFirst(S key, T value) {
        int size_0 = size;
        MyNode<S, T> node = new MyNode<>(key, value);
        size++;
        if (!head.equals(ROOT)) {
            MyNode<S, T> leftNode = head.previous;
            MyNode<S, T> rightNode = head;
            leftNode.next = node;
            rightNode.previous = node;
            head = node;
            node.previous = leftNode;
            node.next = rightNode;

            assert size_0 + 1 == size;
            assert head != tail;

            return node;
        } else {
            ROOT.next = node;
            node.previous = ROOT;
            head = node;
            tail = node;

            assert size_0 + 1 == size;

            return node;
        }
    }
    MyNode<S, T> removeLast() {
        int size0 = size;
        MyNode<S, T> tail0 = tail;
        if (tail.equals(ROOT)) throw new RuntimeException("trying to remove from empty list");
        if (tail.equals(head)) {
            head = head.previous;
        }
        size--;
        MyNode<S, T> removed = tail;
        tail = tail.previous;
        tail.next = null;

        assert tail0.previous == tail;
        assert size0 - 1 == size;

        return removed;
    }

}
