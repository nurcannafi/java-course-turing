package LinkedList;

public class MyLinkedList<T> {
    private Node<T> head;
    private int size;

    MyLinkedList() {
        head = null;
        size = 0;
    }

    public void addHead(T value) {
        Node<T> newNode = new Node<T>(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addTail(T value) {
        Node<T> newNode = new Node<T>(value);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    public T removeHead() {
        if (head == null) {
            return null;
        }
        T data = head.data;
        head = head.next;
        size--;
        return data; //silinen deyeri return edir
    }

    public T removeTail() {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            T data = head.data;
            head = null;
            size--;
            return data;
        }
        Node<T> temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        T data = temp.next.data;
        temp.next = null;
        size--;
        return data;
    }

    public void insert(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node<T> newNode = new Node(value);
        if (index == 0) {
            addHead(value);
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
            size++;
        }
    }

    public void update(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.data = value;
    }

    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }
        size--;
    }

    public void delete(T object) {
        if (head == null || object == null) {
            return;
        }
        if (head.data.equals(object)) {
            head = head.next;
            size--;
            return;
        }
        Node<T> temp = head;
        while (temp.next != null && !temp.next.data.equals(object)) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
            size--;
        }
    }

    public void deleteAll() {
        head = null;
        size = 0;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        Node<T> temp = head;
        while (temp != null) {
            array[index++] = temp.data;
            temp = temp.next;
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> temp = head;
        while (temp != null) {
            sb.append(temp.data);
            if (temp.next != null) {
                sb.append(" --> ");
            }
            temp = temp.next;
        }
        return sb.toString();
    }
}
