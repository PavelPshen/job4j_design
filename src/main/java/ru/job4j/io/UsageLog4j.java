package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        boolean first = true;
        long second = Long.MAX_VALUE;
        float third = 3.14F;
        double fourth = 777.77;
        char fifth = 'Y';
        byte sixth = 127;
        short seventh = -32768;

        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.debug("int : {}, long : {}, byte : {}, short : {}", age, second, sixth, seventh);
        LOG.debug("float : {}, double : {}", third, fourth, name);
        LOG.debug("boolean : {}, no primitive : {}", first);
        LOG.debug("char : {}", fifth);

    }
}