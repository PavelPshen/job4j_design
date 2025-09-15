package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkLength() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(""))
                .isInstanceOf(IllegalArgumentException.class)
                .message();
    }

    @Test
    void checkWithoutEqualSign() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("firsSecond"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("symbol");
    }

    @Test
    void checkEqualSignIsFirst() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key:value"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("key", "key:value");
    }

    @Test
    void checkEqualSignIsLast() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("firsSecond="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("value")
                .hasMessageContaining("firsSecond=");
    }

    @Test
    void checkGoodResult() {
        NameLoad nameLoad = new NameLoad();
        nameLoad.parse("first=Second");
        Map<String, String> result = nameLoad.getMap();
        assertThat(result.get("first")).isNotNull()
                    .contains("Second");
    }
}