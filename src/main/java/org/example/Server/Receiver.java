package org.example.Server;

import org.example.Utilities.SteganographyUtils;

public class Receiver {
    public static String decodeData(int modifiedSequence, int originalSequence) {
        return SteganographyUtils.extractDataFromSequence(modifiedSequence, originalSequence);
    }
}