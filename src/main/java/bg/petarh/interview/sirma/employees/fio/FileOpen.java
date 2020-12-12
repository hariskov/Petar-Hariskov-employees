package bg.petarh.interview.sirma.employees.fio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOpen {
    private FileOpen(){}

    public static List<String> readFileToListOfLines(String filePath) throws IOException {

        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
