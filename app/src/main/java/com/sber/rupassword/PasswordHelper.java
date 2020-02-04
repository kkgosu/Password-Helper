package com.sber.rupassword;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

class PasswordHelper {
    private final String[] mRussians;
    private final String[] mLatins;

    private static final String ENG_LETTERS_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ENG_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*_=+/(){}[];:'";

    private Random mRandom;

    public PasswordHelper() {
        mRussians = new String[0];
        mLatins = new String[0];
    }

    public PasswordHelper(String[] russians, String[] latins) {
        mRussians = russians;
        mLatins = latins;

        mRandom = new Random();

        if (russians.length != latins.length) {
            throw new IllegalArgumentException();
        }
    }

    public String convert(CharSequence input) {
        StringBuilder result = new StringBuilder();
        List<String> russians = Arrays.asList(mRussians);
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int index = russians.indexOf(String.valueOf(c).toLowerCase());
            if (Character.isLetter(c) && index != -1) {
                result.append(
                        Character.isUpperCase(c) ? mLatins[index].toUpperCase() : mLatins[index]);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public String generatePassword(int length, boolean caps, boolean symbols, boolean numbers) {
        StringBuilder resultPassword = new StringBuilder();
        StringBuilder dictionary = new StringBuilder(ENG_LETTERS);

        if (caps)
            dictionary.append(ENG_LETTERS_CAPS);
        if (symbols)
            dictionary.append(SYMBOLS);
        if (numbers)
            dictionary.append(NUMBERS);

        for (int i = 0; i < length; i++) {
            resultPassword.append(dictionary.charAt(mRandom.nextInt(dictionary.length())));
        }
        return resultPassword.toString();
    }

    public int calculateStrength(String password) {
        StringBuilder builder = new StringBuilder();
        if (Pattern.matches(".*\\d+.*", password))
            builder.append(NUMBERS);
        if (Pattern.matches(".*[a-z]+.*", password))
            builder.append(ENG_LETTERS);
        if (Pattern.matches(".*[A-Z]+.*", password))
            builder.append(ENG_LETTERS_CAPS);
        if (Pattern.matches(".*[!@#$%^&*_=+/()'{};:\\[\\]]+.*", password))
            builder.append(SYMBOLS);

        double entropy = log2(Math.pow(builder.length(), password.length()));
        return (int) Math.round(entropy);
    }

    private double log2(double n) {
        return (Math.log(n) / Math.log(2));
    }
}
