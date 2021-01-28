package io.github.eng12020team24.project1.saving;

import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;

public class LoadSystem {
    private FileReader file;
    private String json;

    public LoadSystem(String fileName) throws IOException {
        file = new FileReader(fileName);
        System.out.println(json);
    }
}
