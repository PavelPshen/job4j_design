package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("title")).isEqualTo("mentor");
    }

    @Test
    void whenPairWitCommentAndEmpty() {
        String path = "./data/pair_with_comment_and_empty.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Pavel Pshen");
        assertThat(config.value("gender")).isEqualTo("male");
        assertThat(config.value("age")).isEqualTo("36 25 ty = 5");
        assertThat(config.value("job")).isEqualTo("engineer=programmer");
        assertThat(config.value("# commit")).isNull();
    }

    @Test
    void whenPairNotUsePatternEndSpace() {
        String path = "./data/pair_with_pattern_end_space_fail.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("пробел возле знака =");
    }

    @Test
    void whenPairNotUsePatternStartSpace() {
        String path = "./data/pair_with_pattern_start_space_fail.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("пробел возле знака =");
    }

    @Test
    void whenPairNotUsePatternEqualSign() {
        String path = "./data/pair_with_pattern_without_equal_sign_fail.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("нет знака =");
    }

    @Test
    void whenPairNotUsePatternKeyEmpty() {
        String path = "./data/pair_with_pattern_key_empty_fail.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("нет ключа или значения");
    }

    @Test
    void whenPairNotUsePatternValueEmpty() {
        String path = "./data/pair_with_pattern_value_empty_fail.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("нет ключа или значения");
    }
}