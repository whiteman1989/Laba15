package com.company;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        String find = "Network.log";
        File path = new File("C:/HP");
        FileSearcher search = new FileSearcher(find, path);
    }
}
