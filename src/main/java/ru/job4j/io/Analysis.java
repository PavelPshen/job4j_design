package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;

public class Analysis {

    public void unavailable(String source, String target) {
        ArrayList<String> result = new ArrayList<>();
        boolean statusOff = false;
        String startTime = null;
        String endTime;
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            for (String line : reader.lines().toList()) {
                String[] serverData = line.split(" ");
                if (!statusOff && Integer.parseInt(serverData[0]) > 300) {
                    statusOff = true;
                    startTime = serverData[1].concat(";");
                }
                if (statusOff && Integer.parseInt(serverData[0]) < 400) {
                    statusOff = false;
                    endTime = serverData[1].concat(";");
                    result.add(startTime.concat(endTime));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            for (String line : result) {
                output.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}