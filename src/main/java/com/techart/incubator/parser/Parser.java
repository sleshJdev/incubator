package com.techart.incubator.parser;

import java.io.File;
import java.io.IOException;

public interface Parser {
    String getContent(File file) throws IOException;
}
