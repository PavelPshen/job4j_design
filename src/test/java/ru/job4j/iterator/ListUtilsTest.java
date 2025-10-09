package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;
    private List<Integer> elements;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterLast() {
        ListUtils.addAfter(input, 1, 5);
        assertThat(input).hasSize(3).containsSequence(1, 3, 5);
    }

    @Test
    void whenRemoveIf() {
        Predicate<Integer> isEvenNumber = x -> x % 2==0;
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
        ListUtils.removeIf(input, isEvenNumber);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenRemoveIfMultipleNumbers() {
        Predicate<Integer> isEvenNumber = x -> x % 3==0;
        input.add(4);
        input.add(5);
        input.add(6);
        assertThat(input).hasSize(5).containsSequence(1, 3, 4, 5, 6);
        ListUtils.removeIf(input, isEvenNumber);
        assertThat(input).hasSize(3).containsSequence(1, 4, 5);
    }

    @Test
    void whenReplaceIfFirstNumbers() {
        Predicate<Integer> isEvenNumber = x -> x == 1;
        input.add(4);
        input.add(5);
        input.add(6);
        assertThat(input).hasSize(5).containsSequence(1, 3, 4, 5, 6);
        ListUtils.replaceIf(input, isEvenNumber, 7);
        assertThat(input).hasSize(5).containsSequence(7, 3, 4, 5, 6);
    }

    @Test
    void whenReplaceIfMultipleNumbers() {
        Predicate<Integer> isEvenNumber = x -> x % 3==0;
        input.add(4);
        input.add(5);
        input.add(6);
        assertThat(input).hasSize(5).containsSequence(1, 3, 4, 5, 6);
        ListUtils.replaceIf(input, isEvenNumber, 9);
        assertThat(input).hasSize(5).containsSequence(1, 9, 4, 5, 9);
    }

    @Test
    void whenRemoveAll() {
        elements = new ArrayList<>(Arrays.asList(4, 5));
        input.add(4);
        input.add(5);
        input.add(6);
        assertThat(input).hasSize(5).containsSequence(1, 3, 4, 5, 6);
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(3).containsSequence(1, 3, 6);
    }

    @Test
    void whenRemoveAllInput() {
        input.add(4);
        input.add(5);
        input.add(6);
        assertThat(input).hasSize(5).containsSequence(1, 3, 4, 5, 6);
        ListUtils.removeAll(input, input);
        assertThat(input).isEmpty();
    }
}