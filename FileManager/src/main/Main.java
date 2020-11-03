package main;


import merge.FileMerge;
import split.FileSplit;

import java.io.*;
import java.util.*;

/**
 * @author 16013
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        File f = new File("D://IdeaProjects//Test.txt");
        if (!f.exists()) {
            f.createNewFile();
        }

        RandomAccessFile raf = new RandomAccessFile(f,"rw");

        Thread t1 = new Thread(new FileSplit(raf, "t1"));
        Thread t2 = new Thread(new FileSplit(raf, "t2"));
        Thread t3 = new Thread(new FileSplit(raf, "t3"));
        t1.start();
        t2.start();
        t3.start();
        try{
            t1.join();
            t2.join();
            t3.join();
            ArrayList<String> list = FileSplit.getList();
            System.out.println(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        File file2 = new File("D://IdeaProjects//Merge.txt");
        if (!file2.exists()) {
            file2.createNewFile();
        }
        RandomAccessFile raf2 = new RandomAccessFile(file2,"rw");
        Thread t4 = new Thread(new FileMerge(raf2, "t1"));
        Thread t5 = new Thread(new FileMerge(raf2, "t2"));
        Thread t6 = new Thread(new FileMerge(raf2, "t3"));
        t4.start();
        t5.start();
        t6.start();
        try {
            t4.join();
            t5.join();
            t6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
