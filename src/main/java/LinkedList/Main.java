package LinkedList;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addHead(5);
        list.addHead(73);
        list.addTail(3);
        list.addTail(13);
        list.addTail(27);
        list.addTail(31);
        list.addTail(49);
        list.addHead(7);
        System.out.println(list);
        list.removeHead();
        list.removeTail();
        list.insert(1, 51);
        list.update(2, 9);
        System.out.println(list);
        list.delete(3);
        list.delete(Integer.valueOf(13));
        Object[] array = list.toArray();
        System.out.println(Arrays.toString(array));

    }
}
