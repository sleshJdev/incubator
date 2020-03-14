package com.techart.incubator;

import com.techart.incubator.parser.DefaultParser;
import com.techart.incubator.parser.Parser;
import com.techart.incubator.parser.WithoutUnicodeParser;
import com.techart.incubator.writer.ContentWriter;
import com.techart.incubator.writer.FileContentWriter;

import java.io.File;

public class JobContext {

    public static String DEFAULT_PARSER = "default";
    public static String WITHOUT_UNICODE_PARSER = "withoutUniCode";

    public static String OUTPUT_TYPE = "file";

    public final Parser parser;
    public final ContentWriter writer;
    public final JobConfig config;

    public JobContext(JobConfig config) {
        this.config = config;
        this.parser = getParser(config.parserType);
        this.writer = getContentWriter(config.outputType);
    }

    private Parser getParser(String parserType) {
        if (parserType.equals(DEFAULT_PARSER)) return new DefaultParser();
        else if (parserType.equals(WITHOUT_UNICODE_PARSER)) return new WithoutUnicodeParser();
        else throw new RuntimeException("Wrong parser configuration");
    }

    private ContentWriter getContentWriter(String outputType) {
        if (outputType.equals(OUTPUT_TYPE)) {
            File file = new File(this.config.fileOutput);
            return new FileContentWriter(file);
        } else throw new RuntimeException("Wrong writer configuration");
    }

}
