package ru.anddever;

public class AlgorithmCreator extends EncryptionAlgorithmFactory {

    @Override
    EncryptionAlgorithmMethod createEncryptionAlgorithm(AlgorithmTypes type) {
        return switch (type) {
            case SHIFTING -> new ShiftingAlgorithm();
            case UNICODE -> new UnicodeAlgorithm();
        };
    }
}