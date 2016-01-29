package tszielin.quotes.download.run;

import tszielin.quotes.download.NBPDownloader;

public class NBPUpload extends Upload {
    public static void main(String[] args) {
        new NBPUpload().upload(NBPDownloader.class);
        System.exit(0);
    }
}
