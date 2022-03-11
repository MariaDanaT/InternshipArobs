package ex3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    private String fileName;

    public WriteFile(String fileName) {
        this.fileName = fileName;
    }
    public void writeToFile(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.append(data);
        writer.close();
    }
}
