package org.sqltask.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

    public static String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder builder = new StringBuilder();


        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int read;
            while ((read = reader.read()) != -1) {
                builder.append((char) read);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return builder.toString();
    }
}
