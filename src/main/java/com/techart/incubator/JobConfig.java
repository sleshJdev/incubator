package com.techart.incubator;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class JobConfig {

    private static final Config defaultConfig = ConfigFactory.parseResources("defaults.conf");

    public final String outputType;
    public final String parserType;

    public final String fileOutput = defaultConfig.getString("output.file");

    public JobConfig(String output, String parser) {
        this.outputType = output;
        this.parserType = parser;
    }

}