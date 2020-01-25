package ru.avalon.java.actions;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileCreateAction implements Action {
    private String filename;
    
    public FileCreateAction(String filename) {
        this.filename = filename;
    }
    
    @Override
    public void start() {
        new Thread(this).run();
        System.out.println("Запущен поток создания файла");
    }
    
    @Override
    public void run() {
        try {
            File file = new File(filename);
            if (file.createNewFile()) {
                System.out.printf("Файл %s создан\n", filename);
            } else {
                System.out.printf("Файл с имененм %s уже существует", filename);
            }
        } catch (IOException ex) {
            Logger.getLogger(FileCreateAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void close() throws Exception {
        filename = null;
    }
    
}
