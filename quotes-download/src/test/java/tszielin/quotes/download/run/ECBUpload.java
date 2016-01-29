package tszielin.quotes.download.run;

import tszielin.quotes.download.ECBDownloader;

public class ECBUpload extends Upload {
    public static void main(String[] args) {
        new ECBUpload().upload(ECBDownloader.class);
        System.exit(0);
    }
}
