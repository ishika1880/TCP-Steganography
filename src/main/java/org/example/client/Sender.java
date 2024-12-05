package org.example.client;

import org.example.Utilities.SteganographyUtils;

public class Sender {
    public static byte[] encodeData(String secretMessage, int sequenceNumber) {
        return SteganographyUtils.hideDataInSequence(sequenceNumber, secretMessage);
    }
}
