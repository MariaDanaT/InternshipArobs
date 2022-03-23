package ex3;

public class ChangeText {
    public String encrypt(String text) {
        StringBuilder encrypted = new StringBuilder(text);
        for (int i = 0; i < text.length(); i++) {
            encrypted.setCharAt(i, (char) (text.charAt(i) + 1));
        }
        return encrypted.toString();
    }
    public String decrypt(String text) {
        StringBuilder decrypted = new StringBuilder(text);
        for (int i = 0; i < text.length(); i++) {
            decrypted.setCharAt(i, (char) (text.charAt(i) - 1));
        }
        return decrypted.toString();
    }
}
