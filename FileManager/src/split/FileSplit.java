package split;

import java.io.*;
import java.util.ArrayList;

public class FileSplit implements Runnable{
    private String name;
    private RandomAccessFile raf;
    private static long seek = 0;
    private static ArrayList<String> list = new ArrayList<>();



    public FileSplit() {}

    public FileSplit(RandomAccessFile raf, String name) {
        this.raf = raf;
        this.name = name;
    }

    @Override
    public void run() {
        split();
    }


    public void split () {
        String line;
        File file = new File("D://IdeaProjects//" + name + ".txt");
        try {
            PrintWriter pw = new PrintWriter(file);
            while (true) {
                synchronized (raf) {
                    try {
                        if ((line = raf.readLine()) != null) {
                            seek += line.length() + 2L;
                            System.out.println(name + " seek = " + seek);
                            raf.seek(seek);
                            System.out.println(name + ":" + line);
                            list.add(name + ":" + seek + "--" + line);
                            pw.println(name + ":" + "--" + line);
                            pw.flush();
                        } else {
                            break;
                        }
                    } catch (IOException e) {

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public static ArrayList<String> getList() {
        return list;
    }
}
