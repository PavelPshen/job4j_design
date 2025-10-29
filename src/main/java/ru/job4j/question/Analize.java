package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int add = 0;
        int change = 0;
        int delete = 0;
        Map<Integer, User> previousMap = new HashMap<>();
        for (User user : previous) {
            previousMap.put(user.getId(), user);
        }
        for (User currentUser : current) {
            User previousUser = previousMap.get(currentUser.getId());

            if (previousUser == null) {
                add++;
            } else {
                if (!previousUser.getName().equals(currentUser.getName())) {
                    change++;
                }
                previousMap.remove(currentUser.getId());
            }
        }
        delete = previousMap.size();
        return new Info(add, change, delete);
    }
}