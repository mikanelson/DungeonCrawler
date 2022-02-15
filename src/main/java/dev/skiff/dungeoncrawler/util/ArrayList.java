package dev.skiff.dungeoncrawler.util;

public class ArrayList {
    private static final int INITIAL_ARRAY_SIZE = 128;
    private Object[] objectArray;
    private int arrayItems = 0;

    public ArrayList() {
        objectArray = new Object[INITIAL_ARRAY_SIZE];
    }

    public ArrayList(int size) {
        objectArray = new Object[size];
    }

    public boolean contains(Object o) {
        for (Object obj : objectArray) {
            if (obj != null && obj.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(ArrayList a) {
        if (a.getArrayItems() != arrayItems) {
            return false;
        } else {
            Object[] aObjects = a.getObjectArray();
            for (int i = 0; i < arrayItems; i++) {
                boolean contains = false;
                for (int j = 0; j < arrayItems; j++) {
                    if (aObjects[i].equals(objectArray[j])) {
                        contains = true;
                        break;
                    }
                }
                if (!contains) {
                    return false;
                }
            }
        }
        return true;
    }

    public void add(Object o) {
        if (arrayItems == objectArray.length) {
            Object[] t = new Object[objectArray.length * 2];
            for (int i = 0; i < objectArray.length; i++) {
                t[i] = objectArray[i];
            }
            objectArray = t;
        }
        objectArray[arrayItems] = o;
        arrayItems++;
    }

    public void fillArray() {
        for (int i = 0; i < objectArray.length; i++) {
            add(i);
        }
    }

    public String toString() {
        StringBuilder arrayString = new StringBuilder();
        for (Object obj: objectArray) {
            arrayString.append(obj.toString());
        }
        return arrayString.toString();
    }

    public Object get(int index) {
        return objectArray[index];
    }

    public Object[] getObjectArray() {
        return objectArray;
    }

    public int getLength() {
        return objectArray.length;
    }

    public int getArrayItems() {
        return arrayItems;
    }

    // will throw IndexOutOfBoundsException, desired
    public void set(int index, Object o) {
        if (objectArray[index] == null) {
            arrayItems++;
        }
        if (o == null) {
            arrayItems--;
        }
        objectArray[index] = o;
    }
}
