package com.hillel;

import com.hillel.stringWorker.MaxLength;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MaxLengthRunner {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(MaxLength.class.getSimpleName()).build();
        new Runner(options).run();
    }
}
