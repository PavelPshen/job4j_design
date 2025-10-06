package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    int sizeInput;
    int sizeOutput;

    public T poll() {
        if (sizeInput == 0) {
            throw new java.util.NoSuchElementException("Queue is empty");
        }
        T result;
        sizeOutput = sizeInput;
        while (sizeOutput > 0) {
            output.push(input.pop());
            sizeOutput--;
        }
        result = output.pop();
        sizeInput--;
        while (sizeOutput < sizeInput) {
            input.push(output.pop());
            sizeOutput++;
        }
        return result;
    }

    public void push(T value) {
        input.push(value);
        sizeInput++;
    }
}