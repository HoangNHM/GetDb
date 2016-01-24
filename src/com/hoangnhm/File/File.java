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
    public BufferedWriter mWriter;

    public File(String fileName) {

        mCharset = Charset.forName("UTF-8");
        mFilePath = FileSystems.getDefault().getPath(fileName);
        mOpenOptions = new OpenOption[] {StandardOpenOption.CREATE, StandardOpenOption.APPEND};
        try {
            mWriter = Files.newBufferedWriter(mFilePath, mCharset, mOpenOptions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(List<String> strings) {

        for (String s : strings) {
            try {
                mWriter.write(s);
                mWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToFile(String errorMsg) {
        try {
            mWriter.write(errorMsg);
            mWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
