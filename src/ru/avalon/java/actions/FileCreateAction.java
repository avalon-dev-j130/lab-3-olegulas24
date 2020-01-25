package ru.avalon.java.actions;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileCreateAction implements Action {
    String target;
    Thread thread;

    public FileCreateAction(String target) {
        this.target = target;
    }

    public void run() {
        Path newFilePath = Paths.get(target);
        try {
            Files.createFile(newFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void close () throws Exception {
            System.out.println("Создание файла завершено, для завершения программы введите 'exit'");
        }

    public Thread getThread() {
        return thread;
    }

    }

