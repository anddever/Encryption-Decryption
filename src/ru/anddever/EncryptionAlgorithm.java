package ru.anddever;

public class EncryptionAlgorithm {

    private EncryptionAlgorithmMethod method;

    public void setMethod(EncryptionAlgorithmMethod method) {
        this.method = method;
    }

    public void encrypt(String data, int key) {
        this.method.encrypt(data, key);
    }

    public void decrypt(String data, int key) {
        this.method.decrypt(data, key);
    }
}
