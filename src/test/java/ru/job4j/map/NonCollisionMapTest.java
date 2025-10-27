package ru.job4j.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class NonCollisionMapTest {

    private final SimpleMap<Integer, String> map = new NonCollisionMap<>();

    @BeforeEach
    void setUp() {
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
    }

    @Test
    void checkSimpleIterator() {
        assertThat(map).hasSize(4).contains(1, 2, 3, 4);
    }

    @Test
    void whenCheckGet() {
        assertThat(map.get(1)).isEqualTo("1");
        assertThat(map).hasSize(4);
        assertThat(map.get(5)).isNull();
        assertThat(map).hasSize(4);
    }

    @Test
    void whenCheckPut() {
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map).hasSize(5);
        assertThat(map.put(8, "8")).isFalse();
        assertThat(map).hasSize(5);
        assertThat(map.put(1, "10")).isFalse();
        assertThat(map.get(1)).isEqualTo("1");
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckRemove() {
        assertThat(map.remove(2)).isTrue();
        assertThat(map).hasSize(3);
        assertThat(map.remove(2)).isFalse();
        assertThat(map).hasSize(3);
        assertThat(map.remove(5)).isFalse();
        assertThat(map).hasSize(3);
    }

    @Test
    void whenCheckIterator() {
        map.remove(2);
        map.remove(3);
        map.put(null, "0000");
        Iterator<Integer> iterator = map.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isNull();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(4);
        assertThat(iterator.hasNext()).isFalse();
        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenConcurrentIteratorAdd() {
        Iterator<Integer> iterator = map.iterator();
        map.put(0, "0");
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenConcurrentIteratorRemove() {
        Iterator<Integer> iterator = map.iterator();
        map.remove(1);
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenNotConcurrentIteratorGet() {
        Iterator<Integer> iterator = map.iterator();
        map.get(1);
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenMapExpand() {
        map.put(null, "0000");
        assertThat(map.put(15, "15")).isTrue();
        assertThat(map).hasSize(6);
        assertThat(map.put(8, "8")).isTrue();
        assertThat(map.put(16, "16")).isFalse();
        assertThat(map.get(4)).isEqualTo("4");
        assertThat(map.get(8)).isEqualTo("8");
        assertThat(map.get(15)).isEqualTo("15");
        assertThat(map).hasSize(7).contains(null, 1, 2, 3, 4, 8, 15);
    }

    @Test
    void whenCheckPutKeyNull() {
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckGetKeyNull() {
        map.put(null, "0000");
        assertThat(map.get(null)).isEqualTo("0000");
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckRemoveKeyNull() {
        map.put(null, "0000");
        assertThat(map.remove(null)).isTrue();
        assertThat(map).hasSize(4);
    }

    @Test
    void whenCheckPutZeroAndNull() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map.put(0, "0")).isFalse();
    }

    @Test
    void whenCheckPutNullAndZero() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map.put(null, "0000")).isFalse();
    }

    @Test
    void whenCheckGetZeroAndNull() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map.get(0)).isNull();
    }

    @Test
    void whenCheckGetNullAndZero() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map.get(null)).isNull();
    }

    @Test
    void whenCheckPutSameHashCode() {
        assertThat(map.put(5, "5")).isTrue();
        assertThat(map).hasSize(5).contains(1, 2, 3, 4, 5);
        assertThat(map.put(21, "21")).isFalse();
        assertThat(map).hasSize(5).contains(1, 2, 3, 4, 5);
        assertThat(map.get(5)).isEqualTo("5");
    }

    @Test
    void whenCheckPutMoreCapacityKey() {
        assertThat(map.put(29, "29")).isTrue();
        assertThat(map).hasSize(5).contains(1, 2, 3, 4, 29);
        assertThat(map.get(29)).isEqualTo("29");
    }

    @Test
    void whenCheckGetSameHashCode() {
        assertThat(map.put(5, "5")).isTrue();
        assertThat(map.get(13)).isEqualTo(null);
        assertThat(map.get(5)).isEqualTo("5");
    }

    @Test
    void whenCheckGetMoreCapacityKey() {
        assertThat(map.put(5, "5")).isTrue();
        assertThat(map.put(6, "6")).isTrue();
        assertThat(map.put(7, "7")).isTrue();
        assertThat(map.put(12, "12")).isTrue();
        assertThat(map).hasSize(8);
        assertThat(map.get(12)).isEqualTo("12");
        assertThat(map.put(17, "17")).isFalse();
        assertThat(map.get(17)).isEqualTo(null);
    }

    @Test
    void whenCheckRemoveSameHashCode() {
        assertThat(map.put(5, "5")).isTrue();
        assertThat(map.remove(13)).isFalse();
        assertThat(map).hasSize(5);
        assertThat(map.remove(5)).isTrue();
        assertThat(map).hasSize(4);
    }

    @Test
    void whenCheckRemoveMoreCapacityKey() {
        assertThat(map.put(5, "5")).isTrue();
        assertThat(map.put(6, "6")).isTrue();
        assertThat(map.put(7, "7")).isTrue();
        assertThat(map.put(12, "12")).isTrue();
        assertThat(map).hasSize(8);
        assertThat(map.remove(12)).isTrue();
        assertThat(map).hasSize(7);
        assertThat(map.put(17, "17")).isFalse();
        assertThat(map.remove(17)).isFalse();
        assertThat(map).hasSize(7).contains(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenIteratorLosesCurrentElement() {
        Iterator<Integer> iterator = map.iterator();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
        map.remove(2);
        map.remove(3);
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }
}
