package ru.anddever;

public interface EncryptionAlgorithmMethod {

    void encrypt(String data, int key);

    void decrypt(String data, int key);
}
