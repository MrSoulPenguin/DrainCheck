package me.mrsoulpenguin;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class DrainCheck {

    public static void main(String[] args) {
        decompress(args[0], args[1]);
        Set<String> foundFiles = scan(args[1]);
        System.out.println("Amount of possibly impacted files: " + foundFiles.size());

        if (foundFiles.size() > 0) {
            System.out.println("Writing results");
            Path jarPath = Path.of(args[0]);
            File file = new File(jarPath.getParent() + "/results.txt");
            try (FileOutputStream fileOutputStream = new FileOutputStream(file, true)) {
                fileOutputStream.write((jarPath.getFileName() + System.lineSeparator()).getBytes(StandardCharsets.UTF_8));
                for (String fileName : foundFiles) {
                    String fileEntry = fileName + System.lineSeparator();
                    fileOutputStream.write(fileEntry.getBytes(StandardCharsets.UTF_8));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void decompress(String jarFilePath, String outputDir) {
        System.out.println("Decompressing " + Path.of(jarFilePath).getFileName());

        try (JarInputStream jarInputStream = new JarInputStream(new FileInputStream(jarFilePath))) {
            JarEntry entry;
            while ((entry = jarInputStream.getNextJarEntry()) != null) {
                File outputFile = new File(outputDir, entry.getName());
                if (entry.isDirectory()) {
                    outputFile.mkdirs();
                } else {
                    outputFile.getParentFile().mkdirs();
                    try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = jarInputStream.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, bytesRead);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Set<String> scan(String inputDir) {
        File dir = new File(inputDir);
        File[] files = dir.listFiles();
        if (files == null) {
            return new HashSet<>();
        }

        Set<String> foundFiles = new HashSet<>();
        for (File file : files) {
            if (file.isDirectory()) {
                foundFiles.addAll(scan(file.getAbsolutePath()));
            } else if (file.getName().endsWith(".class")) {
                System.out.println("Scanning " + file.getName());

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("ObjectInputStream") || line.contains("ObjectOutputStream")) {
                            foundFiles.add(file.getAbsolutePath());
                            break;
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return foundFiles;
    }
}
