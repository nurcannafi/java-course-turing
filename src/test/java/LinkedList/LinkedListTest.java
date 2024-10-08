package LinkedList;

import LinkedList.MyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListTest {

    MyLinkedList<String> list = new MyLinkedList<>();

    @BeforeEach
    void setUp() {
        list = new MyLinkedList<>();
        list.addHead("Ron");
        list.addHead("Harmonie");
        list.addHead("Harry");
    }

    @Test
    void addTest() {
        MyLinkedList<String> list1 = new MyLinkedList<>();
        list1.addHead("Ron");
        list1.addHead("Harmonie");
        list1.addHead("Harry");

        assertEquals(list, list1);
    }
}
