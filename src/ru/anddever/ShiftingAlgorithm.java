package ru.anddever;

public class ShiftingAlgorithm implements EncryptionAlgorithmMethod {

    @Override
    public String encrypt(String data, int key) {
        return shiftStringBy(data, key);
    }

    @Override
    public String decrypt(String data, int key) {
        return shiftStringBy(data, key * -1);
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
        if (Character.isAlphabetic(c)) {
            char result = (char) (c + shift);
            if (result > '~') {
                result -= 26;
            }
            return result;
        }
        return ' ';
    }
}