package ru.anddever;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        int key = 0;
        String mode = "enc", data = "", outFileName = "", inFileName = "";
        int k = 0;
        while (k < args.length - 1) {
            String arg = args[k];
            switch (arg) {
                case "-mode":
                    mode = args[k + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[k + 1]);
                    break;
                case "-data":
                    data = args[k + 1];
                    break;
                case "-out":
                    outFileName = args[k + 1];
                    break;
                case "-in":
                    inFileName = args[k + 1];
            }
            k += 2;
        }
        if (data.isEmpty() && !inFileName.isEmpty()) {
            try {
                data = new String(Files.readAllBytes(Paths.get(inFileName)));
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }
        }
        if (!outFileName.isEmpty()) {
            File outFile = new File(outFileName);
            if ("enc".equals(mode))
                try (PrintWriter writer = new PrintWriter(outFile)) {
                    writer.print(shiftStringBy(data, key));
                } catch (FileNotFoundException e) {
                    System.out.println("Error " + e.getMessage());
                }
            if ("dec".equals(mode))
                try (PrintWriter writer = new PrintWriter(outFile)) {
                    writer.print(shiftStringBy(data, -1 * key));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
        } else {
            if ("enc".equals(mode))
                System.out.println(shiftStringBy(data, key));
            if ("dec".equals(mode))
                System.out.println(shiftStringBy(data, -1 * key));
        }
    }

    public static String shiftStringBy(String str, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            c = shiftCharBy(c, shift);
            result.append(c);
        }
        return result.toString();
    }

    public static char shiftCharBy(char c, int shift) {
        char result = (char) (c + shift);
        if (result > '~') {
            result -= 26;
        }
        return result;
    }
}