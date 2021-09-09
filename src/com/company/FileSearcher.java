package com.company;

import java.io.File;
import java.util.ArrayList;

public class FileSearcher implements Runnable{

    private String fileName;
    private File path;
    private ArrayList<File> found;
    private ArrayList<Thread> threads;
    private File[] files;
    private Thread thr;

    public FileSearcher(String fileName, File path) {
        super();
        this.fileName = fileName;
        this.path = path;
        this.files = path.listFiles();
        found = new ArrayList<>();
        threads = new ArrayList<>();
        thr = new Thread(this);
        thr.start();
    }

    public void searching(File[] files) {
        if (files == null) return;
        for (File i : files) {
            if (i.isFile() && i.getName().equalsIgnoreCase(fileName)) {
                System.out.format("--> %s has found file: %s\n", Thread.currentThread().getName(), i.getName());
                found.add(i);
            }
            if (i.isDirectory()) {
                Thread newThread = new Thread(new SearchingThread(i.listFiles(), this));
                newThread.start();
                threads.add(newThread);
            }
        }
    }

    @Override
    public void run() {
        searching(files);
        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (found.size() == 0) {
            System.out.println("Nothing was found!");
        } else {
            System.out.println();
            for (File i : found) {
                System.out.println(i.getAbsoluteFile());
            }
        }
    }
}
