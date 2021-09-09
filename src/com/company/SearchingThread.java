package com.company;

import java.io.File;

public class SearchingThread implements Runnable{
    private FileSearcher searcher;
    private File[] files;

    public SearchingThread(File[] files, FileSearcher searcher) {
        this.searcher = searcher;
        this.files = files;
    }

    @Override
    public void run() {
        searcher.searching(files);
    }
}
