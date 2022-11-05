import java.util.Objects;

public class MyNode<S, T> {
    S key;
    T value;
    MyNode<S, T> next;
    MyNode<S, T> previous;

    MyNode(S key, T value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyNode<?, ?> myNode = (MyNode<?, ?>) o;
        return Objects.equals(key, myNode.key) && Objects.equals(value, myNode.value) && Objects.equals(next, myNode.next) && Objects.equals(previous, myNode.previous);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value, next, previous);
    }
}
