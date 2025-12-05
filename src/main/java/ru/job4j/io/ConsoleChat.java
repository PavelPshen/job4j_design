package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        List<String> answers = readPhrases();
        List<String> log = new ArrayList<>();
        Random random = new Random();
        String massage = "";
        Scanner input = new Scanner(System.in);
        while (!OUT.equals(massage)) {
            massage = input.nextLine();
            log.add("User: " + massage);
            switch (massage) {
                case (STOP) :
                    while (!CONTINUE.equals(massage)) {
                        massage = input.nextLine();
                        log.add("User: " + massage);
                        if (OUT.equals(massage)) {
                            break;
                        }
                    }
                    break;
                case (OUT) :
                    break;
                default:
                    String logString = answers.get(random.nextInt(answers.size()));
                    System.out.println(logString);
                    log.add("Bot: " + logString);
                    break;
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat consoleChat = new ConsoleChat("C:\\projects\\job4j_design\\data\\chatLog.txt",
                "C:\\projects\\job4j_design\\data\\botAnswer.txt");
        consoleChat.run();
    }
}
