package com.hoangnhm.File;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by vantuegia on 1/23/2016.
 */
public class File {

    private final Charset mCharset;
    private Path mFilePath;
    private OpenOption[] mOpenOptions;

    public File(String fileName) {

        mCharset = Charset.forName("UTF-8");
        mFilePath = FileSystems.getDefault().getPath(fileName);
        mOpenOptions = new OpenOption[] {StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};
    }

    public void writeToFile(List<String> strings) throws IOException {

        try (BufferedWriter writer = Files.newBufferedWriter(mFilePath, mCharset, mOpenOptions)) {
            for (String s : strings) {
                writer.write(s);
                writer.newLine();
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}
