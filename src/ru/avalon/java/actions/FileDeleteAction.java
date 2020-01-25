package ru.avalon.java.actions;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

public class FileDeleteAction implements Action {
    String target;
    Thread thread;

    public FileDeleteAction(String target) throws IOException {
        this.target = target;
        thread = new Thread(this, "delete");
        thread.start();
    }

    public void run() {
        try {
            Files.delete(Paths.get(target));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() throws Exception {

        System.out.println("Удаление завершено, для завершения программы введите 'exit'");
    }

    public Thread getThread() {
        return thread;
    }

}
