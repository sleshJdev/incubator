package com.techart.incubator.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class WithoutUnicodeParser implements Parser {

    public String getContent(File file) throws IOException {
        FileInputStream i = new FileInputStream(file);
        StringBuilder output = new StringBuilder();
        int data;
        while ((data = i.read()) > 0) {
            if (data < 0x80) {
                output.append((char) data);
            }
        }
        return output.toString();
    }

}