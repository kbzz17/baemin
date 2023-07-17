package jw.project.baemin.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    static final String fileURL = "src/main/resources/file/";

    public static List<String[]> parseTxtFile(String fileName) {
        BufferedReader br;
        String line;
        List<String[]> fileList = new ArrayList<>();
        try {
            File file = new File(fileURL+fileName);
            br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

            while ((line = br.readLine()) != null) {
                fileList.add(line.split("\t"));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileList;
    }

}
