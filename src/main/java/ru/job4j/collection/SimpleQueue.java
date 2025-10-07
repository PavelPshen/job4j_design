package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int sizeOutput;
    private int sizeInput;

    public T poll() {
        if (sizeInput + sizeOutput == 0) {
            throw new java.util.NoSuchElementException("Queue is empty");
        }
        if (sizeOutput == 0) {
            while (sizeInput > 0) {
                output.push(input.pop());
                sizeOutput++;
                sizeInput--;
            }
        }
        sizeOutput--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        sizeInput++;
    }
}