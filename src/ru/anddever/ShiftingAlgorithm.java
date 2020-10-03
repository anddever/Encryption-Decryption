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
        char result;
        if (Character.isAlphabetic(c)) {
            if (shift >= 0) {
                if (Character.isUpperCase(c)) {
                    result = (char) (c + shift);
                    if (result > 'Z') {
                        result -= 26;
                    }
                } else {
                    result = (char) (c + shift);
                    if (result > 'z') {
                        result -= 26;
                    }
                }
            } else {
                if (Character.isUpperCase(c)) {
                    result = (char) (c + shift);
                    if (result < 'A') {
                        result += 26;
                    }
                } else {
                    result = (char) (c + shift);
                    if (result < 'a') {
                        result += 26;
                    }
                }
            }
            return result;
        }
        return ' ';
    }
}