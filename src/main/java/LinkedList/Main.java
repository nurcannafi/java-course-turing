package LinkedList;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addHead(3);
        list.addHead(2);
        list.addHead(1);
        list.addTail(4);
        list.addTail(6);
        list.update(4, 5);
        list.insert(5, 6);
        list.removeHead();
        list.delete(3);
        System.out.println(list);
        Object[] array = list.toArray();
        System.out.println(Arrays.toString(array));

    }
}
