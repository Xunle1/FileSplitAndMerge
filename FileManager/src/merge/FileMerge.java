package merge;

import java.io.*;

public class FileMerge implements Runnable{
    private String name;
    private File file;
    private RandomAccessFile raf;
    private static long seek = 0;

    public FileMerge() {}

    public FileMerge(RandomAccessFile raf, String name) {
        this.raf = raf;
        this.name = name;
        this.file = new File("D://IdeaProjects//" + name + ".txt");
    }

    @Override
    public void run() {
        merge();
    }
    public void merge() {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while(true) {
                synchronized (raf) {
                    if ((line = br.readLine()) != null) {
                        raf.seek(raf.length());
                        line = line +"\n";
                        raf.writeBytes(line);
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
