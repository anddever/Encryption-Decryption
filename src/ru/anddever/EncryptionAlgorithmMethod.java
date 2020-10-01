package ru.anddever;

public interface EncryptionAlgorithmMethod {

    String encrypt(String data, int key);

    String decrypt(String data, int key);
}