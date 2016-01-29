package tszielin.quotes.download.run;

import tszielin.quotes.download.FEDDownloader;

public class FEDUpload extends Upload {
    public static void main(String[] args) {
        new FEDUpload().upload(FEDDownloader.class);
        System.exit(0);
    }
}
