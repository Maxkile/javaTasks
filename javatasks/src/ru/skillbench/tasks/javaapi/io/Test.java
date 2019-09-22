package ru.skillbench.tasks.javaapi.io;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.stream.Stream;

class AddThread implements Runnable{
    private WordFinderImpl wf;

    AddThread(WordFinderImpl wf){
        this.wf = wf;
    }

    @Override
    public void run() {
        System.out.println(wf.getText());
    }

}

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        Thread add_thread =  new Thread(() -> {
            System.out.println(System.nanoTime());
            ;});
        add_thread.start();
        WordFinderImpl wf = new WordFinderImpl();
//        FileInputStream sr = new FileInputStream(new File("Test"));
//        wf.setInputStream(System.in);
//        wf.setResource("Test");0
        wf.setFilePath("/home/maxkile/Docs/Progs/git/java/javatasks/src/ru/skillbench/tasks/javaapi/io/Test");
        wf.findWordsStartWith("pu");
        wf.writeWords(new FileOutputStream(new File("S")));
//        System.out.println(wf.getText());
        add_thread.join();
        System.out.println(System.nanoTime());
    }
}



