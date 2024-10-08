package LinkedList;

import java.util.Objects;
import java.util.Optional;

public class MyLinkedList<T> {

    private Node<T> head;
    private int size;

    public T addHead(final T value) {
        final Node<T> newNode = new Node<T>(value);
        newNode.setNext(head);
        head = newNode;
        size++;
        return value;
    }

    public T addTail(final T value) {
        final Node<T> newNode = new Node<T>(value);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = head;
            for (int i = 0; i < size - 1; i++) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
        }
        size++;
        return value;
    }

    public Optional<T> removeHead() {
        if (head == null) {
            return Optional.empty();
        }
        T data = head.getData();
        head = head.getNext();
        size--;
        return Optional.of(data);
    }

    public Optional<T> removeTail() {
        if (head == null) {
            return Optional.empty();
        }
        if (head.getNext() == null) {
            T data = head.getData();
            head = null;
            size--;
            return Optional.of(data);
        }
        Node<T> temp = head;
        while (temp.getNext().getNext() != null) {
            temp = temp.getNext();
        }
        T data = temp.getNext().getData();
        temp.setNext(null);
        size--;
        return Optional.of(data);
    }

    public T insert(final int index, final T value) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        Node<T> newNode = new Node<>(value);
        if (index == 0) {
            addHead(value);
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
            size++;
        }
        return value;
    }

    public T update(final int index, final T value) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        temp.setData(value);
        return value;
    }

    public T delete(final int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index is wrong");
        }
        T data;
        if (index == 0) {
            data = head.getData();
            head = head.getNext();
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            data = temp.getNext().getData();
            temp.setNext(temp.getNext().getNext());
        }
        size--;
        return data;
    }

    public T delete(final T object) {
        if (head == null || object == null) {
            throw new IllegalArgumentException("Index is wrong");
        }
        if (head.getData().equals(object)) {
            head = head.getNext();
            size--;
        } else {
            Node<T> temp = head;
            while (temp.getNext() != null && !temp.getNext().getData().equals(object)) {
                temp = temp.getNext();
            }
            if (temp.getNext() != null) {
                temp.setNext(temp.getNext().getNext());
                size--;
            }
        }
        return object;
    }

    public MyLinkedList<T> deleteAll() {
        head = null;
        size = 0;
        return this;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        Node<T> temp = head;
        while (temp != null) {
            array[index++] = temp.getData();
            temp = temp.getNext();
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> temp = head;
        while (temp != null) {
            sb.append(temp.getData());
            if (temp.getNext() != null) {
                sb.append(" --> ");
            }
            temp = temp.getNext();
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyLinkedList<?> that = (MyLinkedList<?>) o;

        if (size != that.size) return false;
        Node<T> currentThis = this.head;
        Node<?> currentThat = that.head;

        while (currentThis != null && currentThat != null) {
            if (!Objects.equals(currentThis.getData(), currentThat.getData())) {
                return false;
            }
            currentThis = currentThis.getNext();
            currentThat = currentThat.getNext();
        }

        return currentThis == null && currentThat == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, size);
    }
}
