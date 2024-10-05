package module01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListTest {

    LinkedList<String> list = new LinkedList<>();

    @BeforeEach
    void setUp() {
        list = new LinkedList<>();
        list.add("Harry");
        list.add("Harmonie");
        list.add("Ron");
    }

    @Test
    void addTest() {
        LinkedList<String> list1 = new LinkedList<>();
        list.add("Harry");
        list.add("Harmonie");
        list.add("Ron");

        assertEquals(list, list1);
    }
}
