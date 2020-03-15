package com.techart.incubator.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileContentWriter implements ContentWriter {

    private final File file;

    public FileContentWriter(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        FileOutputStream o = new FileOutputStream(file);
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
    }

}
