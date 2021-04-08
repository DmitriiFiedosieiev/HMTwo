package com.hillel.stringWorker;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.OptionalInt;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


@Fork(value = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
public class MaxLength {

    private String filePath;

    public MaxLength() {
        this.filePath = "File.txt";
    }

    @Benchmark
    public void findMaxLengthWithForEach(Blackhole blackhole) {
        int flag = 0;
        try(FileInputStream input = new FileInputStream(filePath)) {
            byte[] buffer = input.readAllBytes();
            String[] lines = new String(buffer).split("\n");
            for (String line : lines) {
                if (line.length() > flag) {
                    flag = line.length();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        blackhole.consume(flag);
    }

    @Benchmark
    public void findMaxLengthWithStream(Blackhole blackhole) {
        OptionalInt result = OptionalInt.empty();
        Path path = Paths.get(filePath);
        try(Stream<String> lines = Files.lines(path)) {
            result = lines.mapToInt(String::length).max();
        } catch (IOException e) {
            e.printStackTrace();
        }
        blackhole.consume(result);
    }
}
