package dev.skiff.dungeoncrawler.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayListTest {
    ArrayList a1;
    ArrayList a2;
    ArrayList a3;

    @BeforeEach
    public void setUp() {
        a1 = new ArrayList();
        a2 = new ArrayList();
        a3 = new ArrayList(129);
    }

    @Test
    public void arrayIsFilled() {
        a1.fillArray();
        Object[] a1a = a1.getObjectArray();
        boolean isFilled = true;
        for (int i = 0; i < a1.getLength(); i++) {
            if (a1a[i] == null) {
                isFilled = false;
            }
        }
        assertTrue(isFilled);
    }

    @Test
    public void containsSingleItem() {
        a1.add(100);
        a1.contains(100);
    }

    @Test
    public void containsMultipleItems() {
        a1.add(100);
        a1.add(20);
        assertTrue(a1.contains(100) && a1.contains(20));
    }

    @Test
    public void setItem() {
        a1.add(10);
        a1.set(0, 200);
        a1.contains(200);
    }

    @Test
    public void twoArrayListsAreEqualSameSizes() {
        a1.fillArray();
        a2.fillArray();
        assertTrue(a1.equals(a2));
    }

    @Test
    public void twoArraysAreNotEqualDifferentSizes() {
        a1.fillArray();
        a3.fillArray();
        assertFalse(a1.equals(a2));
    }

    @Test
    public void twoArraysAreEqualDifferentSizes() {
        a1.fillArray();
        a3.fillArray();
        a3.set(128, null);
        assertTrue(a1.equals(a3));
    }
}
