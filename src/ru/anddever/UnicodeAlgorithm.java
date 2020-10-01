package ru.anddever;

public class UnicodeAlgorithm implements EncryptionAlgorithmMethod {

    @Override
    public void encrypt(String data, int key) {
        shiftStringBy(data, key);
    }

    @Override
    public void decrypt(String data, int key) {
        shiftStringBy(data, key * -1);
    }

    private String shiftStringBy(String str, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            c = shiftCharBy(c, shift);
            result.append(c);
        }
        return result.toString();
    }

    private char shiftCharBy(char c, int shift) {
        char result = (char) (c + shift);
        if (result > '~') {
            result -= 26;
        }
        return result;
    }
}