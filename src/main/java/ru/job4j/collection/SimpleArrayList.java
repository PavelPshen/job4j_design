package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    public void growSimpleArrayList() {
        container = Arrays.copyOf(container, container.length == 0 ? 10 : container.length * 2);
    }

    @Override
    public void add(T value) {

        if (size == container.length) {
            growSimpleArrayList();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T removeElement = container[index];
        container[index] = newValue;
        return removeElement;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removeElement = container[index];
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                size - index - 1
        );
        container[--size] = null;
        modCount++;
        return removeElement;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {

                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}