package ex3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    private String fileName;

    public ReadFile(String fileName) {
        this.fileName = fileName;
    }

    public String readFromFile() throws IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        String fileContent = "";
        while (line != null) {
            fileContent = fileContent.concat(line);
            line = bufferedReader.readLine();
        }
        fileReader.close();
        return fileContent;
    }
}
