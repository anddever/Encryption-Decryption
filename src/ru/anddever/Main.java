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
        String mode = "enc", data = "", outFileName = "", inFileName = "", alg = "shift";
        int k = 0;
        AlgorithmCreator creator = new AlgorithmCreator();
        EncryptionAlgorithmMethod method;
        while (k < args.length - 1) {
            String arg = args[k];
            switch (arg) {
                case "-mode" -> mode = args[k + 1];
                case "-key" -> key = Integer.parseInt(args[k + 1]);
                case "-data" -> data = args[k + 1];
                case "-out" -> outFileName = args[k + 1];
                case "-in" -> inFileName = args[k + 1];
                case "-alg" -> alg = args[k + 1];
            }
            k += 2;
        }
        switch (alg) {
            case "shift" -> method = creator.createEncryptionAlgorithm(AlgorithmTypes.SHIFTING);
            case "unicode" -> method = creator.createEncryptionAlgorithm(AlgorithmTypes.UNICODE);
            default -> {
                System.out.println("You passed unsupported algorithm, we'll use shifting instead");
                method = creator.createEncryptionAlgorithm(AlgorithmTypes.SHIFTING);
            }
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
                    writer.print(method.encrypt(data, key));
                } catch (FileNotFoundException e) {
                    System.out.println("Error " + e.getMessage());
                }
            if ("dec".equals(mode))
                try (PrintWriter writer = new PrintWriter(outFile)) {
                    writer.print(method.decrypt(data, -1 * key));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
        } else {
            if ("enc".equals(mode))
                System.out.println(method.encrypt(data, key));
            if ("dec".equals(mode))
                System.out.println(method.decrypt(data, -1 * key));
        }
    }
}