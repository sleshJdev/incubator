package com.techart.incubator;

import com.techart.incubator.parser.Parser;
import com.techart.incubator.writer.ContentWriter;

import java.io.File;
import java.io.IOException;


public class Job {

    private final JobContext jcx;

    private Job(JobConfig config) {
        this.jcx = new JobContext(config);
    }

    public static void main(String[] args) throws IOException {
        String filepath = "path.txt";
        JobConfig config = new JobConfig("file", "default");
        Job job = new Job(config);
        job.execute(filepath);
    }

    public void execute(String filepath) throws IOException {
        File fileToParse = new File(filepath);

        Parser parser = jcx.parser;
        ContentWriter writer = jcx.writer;

        String content = parser.getContent(fileToParse);
        writer.saveContent(content);
    }

}
