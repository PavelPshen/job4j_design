package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEmpty()
                .startsWith("S");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 12);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .isEqualTo("Cube")
                .endsWith("e");
    }

    @Test
    void theBoxContains5VertexAnd8Edge() {
        Box box = new Box(5, 8);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isNotZero()
                .isNegative()
                .isEqualTo(-1)
                .isLessThan(0);
    }

    @Test
    void theBoxContains8VertexAnd12Edge() {
        Box box = new Box(8, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isNotZero()
                .isPositive()
                .isGreaterThan(7);
    }

    @Test
    void isExistTrue() {
        Box box = new Box(8, 12);
        boolean result = box.isExist();
        assertThat(result).isTrue()
                .isEqualTo(true);
    }

    @Test
    void isExistFalse() {
        Box box = new Box(5, 2);
        boolean result = box.isExist();
        assertThat(result).isFalse()
                .isEqualTo(false);
    }

    @Test
    void theVertex8AndEdge5() {
        Box box = new Box(8, 5);
        double square = box.getArea();
        assertThat(square).isEqualTo(150)
                .isPositive()
                .isGreaterThan(130);
    }

    @Test
    void theVertex6AndEdge13() {
        Box box = new Box(6, 13);
        double square = box.getArea();
        assertThat(square).isEqualTo(0)
                .isZero()
                .isLessThan(1);
    }
}