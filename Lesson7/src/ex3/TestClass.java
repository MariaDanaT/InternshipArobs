package ex3;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose Encrypt/Decrypt:");
        String option = scanner.nextLine();

        if(option.equals("Encrypt")){
            ReadFile readFile = new ReadFile("C:\\Users\\maria.tomos\\Documents\\Git\\InternshipArobsWeek1\\Lesson7\\src\\ex3\\data.dec");
            WriteFile writeFile = new WriteFile("C:\\Users\\maria.tomos\\Documents\\Git\\InternshipArobsWeek1\\Lesson7\\src\\ex3\\data.enc");
            try {
                String data = readFile.readFromFile();
                System.out.println(data);
                ChangeText changeText = new ChangeText();
                String encryptedData = changeText.encrypt(data.toString());
                writeFile.writeToFile(encryptedData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(option.equals("Decrypt")){
            ReadFile readFile = new ReadFile("C:\\Users\\maria.tomos\\Documents\\Git\\InternshipArobsWeek1\\Lesson7\\src\\ex3\\data.enc");
            WriteFile writeFile = new WriteFile("C:\\Users\\maria.tomos\\Documents\\Git\\InternshipArobsWeek1\\Lesson7\\src\\ex3\\data.dec");
            try {
                String data = readFile.readFromFile();
                System.out.println(data);
                ChangeText changeText = new ChangeText();
                String decryptedData = changeText.decrypt(data);
                writeFile.writeToFile(decryptedData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else System.out.println("The option does not exist!");

    }
}
