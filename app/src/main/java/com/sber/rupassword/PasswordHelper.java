package com.sber.rupassword;

import java.util.Random;
import java.util.regex.Pattern;

public class PasswordHelper {
    private final String[] russians;
    private final String[] latins;

    private final String mEngCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String mEng = "abcdefghijklmnopqrstuvwxyz";
    private final String mNumbers = "0123456789";
    private final String mSymbols = "!@#$%^&*_=+/";
    private String password;

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

        password = resultPassword.toString();
        return password;
    }

    public int calculateStrength(String password) {
        StringBuilder builder = new StringBuilder();
        if (Pattern.matches(".*\\d+.*", password))
            builder.append(mNumbers);
        if (Pattern.matches(".*[a-z]+.*", password))
            builder.append(mEng);
        if (Pattern.matches(".*[A-Z]+.*", password))
            builder.append(mEngCaps);
        if (Pattern.matches(".*[!@#$%^&*_=+/]+.*", password))
            builder.append(mSymbols);

        double entropyPerCharacter = log2(Math.pow(builder.length(), password.length()));
        return (int) (entropyPerCharacter);
    }

    private double log2(double n) {
        return (Math.log(n) / Math.log(2));
    }
}
