package ru.avalon.java;

import ru.avalon.java.console.ConsoleUI;
import ru.avalon.java.actions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Лабораторная работа №3
 * <p>
 * Курс: "Программирование на платформе Java. Разработка
 * многоуровневых приложений"
 * <p>
 * Тема: "Потоки исполнения (Threads) и многозадачность" 
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class Lab3 extends ConsoleUI<Commands> {
    /**
     * Точка входа в приложение.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        new Lab3().run();
    }
    /**
     * Конструктор класса.
     * <p>
     * Инициализирует экземпляр базового типа с использоавнием
     * перечисления {@link Commands}.
     */
    Lab3() {
        super(Commands.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCommand(Commands command) throws IOException {
        String source;
        String target;
        String[] nameFile;
        BufferedReader br;

        switch (command) {
            case copy:
                /*
                 * TODO №6 Обработайте команду copy
                 */
                br = new BufferedReader(new
                                    InputStreamReader(System.in));

                System.out.print("Откуда копируем: ");
                source = br.readLine();
                System.out.println(source);
                System.out.print("Куда копируем: ");
                target = br.readLine();
                System.out.println(target);
                FileCopyAction fileCopy = new FileCopyAction(source, target);

                if(fileCopy.getThread().isAlive()) {
                    try {
                        fileCopy.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                break;
            case move:
                /*
                 * TODO №7 Обработайте команду move
                 */
                br = new BufferedReader(new
                        InputStreamReader(System.in));

                System.out.print("Откуда перемещаем: ");
                source = br.readLine();
                nameFile = source.split("\\\\");
                System.out.println(source);
                System.out.print("Куда перемещаем: ");
                target = br.readLine();
                System.out.println(target);
                FileMoveAction fileMove = new FileMoveAction(source, target, nameFile[nameFile.length-1]);

                if(fileMove.getThread().isAlive()) {
                    try {
                        fileMove.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                break;
            case delete:

                br = new BufferedReader(new
                        InputStreamReader(System.in));

                System.out.print("Откуда удаляем файл: ");
                target = br.readLine();
                FileDeleteAction fileDelete = new FileDeleteAction(target);

                if(fileDelete.getThread().isAlive()) {
                    try {
                        fileDelete.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

            case create:
                br = new BufferedReader(new
                        InputStreamReader(System.in));

                System.out.print("Где создаем файл: ");
                target = br.readLine();
                System.out.println(target);
                FileCreateAction fileCreate = new FileCreateAction(target);

                fileCreate.start(); 
                    try {
                        fileCreate.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                

                break;

            case exit:
                close();
                break;
                /*
                 * TODO №9 Обработайте необработанные команды
                 */
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
    
}
