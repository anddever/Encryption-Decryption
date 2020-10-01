package ru.anddever;

public abstract class EncryptionAlgorithmFactory {

    abstract EncryptionAlgorithmMethod createEncryptionAlgorithm(AlgorithmTypes type);
}