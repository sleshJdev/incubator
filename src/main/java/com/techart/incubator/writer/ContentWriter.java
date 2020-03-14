package com.techart.incubator.writer;

import java.io.IOException;

public interface ContentWriter {
    void saveContent(String content) throws IOException;
}