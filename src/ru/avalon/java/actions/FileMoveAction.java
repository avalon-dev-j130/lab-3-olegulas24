package ru.avalon.java.actions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Действие, которое перемещает файлы в пределах дискового
 * пространства.
 */
public class FileMoveAction implements Action {
    String source;
    String target;
    String nameFile;
    Thread thread;

    public FileMoveAction(String source, String target, String nameFile) {
        this.source = source;
        this.target = target;
        this.nameFile = nameFile;
        thread = new Thread(this,"move");
        thread.start();
    }

    @Override
    public void run() {
        /*
         * TODO №4 Реализуйте метод run класса FileMoveAction
         */
        String file = target + "\\" + nameFile;
            Path newFilePath = Paths.get(file);
            try {
                Files.createFile(newFilePath);
                Files.copy(Paths.get(source), Paths.get(file),
                        StandardCopyOption.REPLACE_EXISTING);

                Files.delete(Paths.get(source));
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
        /*
         * TODO №5 Реализуйте метод close класса FileMoveAction
         */
        System.out.println("Перемещение завершено, для завершения программы введите 'exit'");
    }

    public Thread getThread() {
        return thread;
    }

}
