package com.sber.rupassword;

import java.util.Random;

public class PasswordHelper {
    private final String[] russians;
    private final String[] latins;

    private final String mEngCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String mEng = "abcdefghijklmnopqrstuvwxyz";
    private final String mNumbers = "0123456789";
    private final String mSymbols = "!@#$%^&*_=+-/";

    public PasswordHelper(String[] russians, String[] latins) {
        this.russians = russians;
        this.latins = latins;

        if (russians.length != latins.length) {
            throw new IllegalArgumentException();
        }
    }

    public String convert(CharSequence input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            boolean found = false;

            for (int j = 0; j < russians.length; j++) {
                if (russians[j].equals(String.valueOf(c).toLowerCase())) {
                    result.append(Character.isUpperCase(c) ? latins[j].toUpperCase() : latins[j]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                result.append(c);
            }
        }

        return result.toString();
    }

    public String generatePassword(int length, boolean caps, boolean symbols, boolean numbers) {
        StringBuilder resultPassword = new StringBuilder();
        StringBuilder dictionary = new StringBuilder(mEng);
        Random random = new Random();

        if (caps)
            dictionary.append(mEngCaps);
        if (symbols)
            dictionary.append(mSymbols);
        if (numbers)
            dictionary.append(mNumbers);

        for (int i = 0; i < length; i++) {
            resultPassword.append(dictionary.charAt(random.nextInt(dictionary.length())));
        }
        return resultPassword.toString();
    }

}
