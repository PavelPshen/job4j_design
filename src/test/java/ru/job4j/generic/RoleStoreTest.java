package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenUserRoleIsDeveloper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Developer"));
        Role result = store.findById("1");
        assertThat(result.getUserRole()).isEqualTo("Developer");
    }

    @Test
    void whenAddDuplicateAndFindUserRoleIsTester() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Tester"));
        store.add(new Role("1", "Developer"));
        Role result = store.findById("1");
        assertThat(result.getUserRole()).isEqualTo("Tester");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Devops"));
        boolean result = store.replace("1", new Role("1", "Cleaner"));
        assertThat(result).isTrue();
    }
}