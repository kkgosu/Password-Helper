package com.sber.rupassword;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

class PasswordHelper {
    private final String[] mRussians;
    private final String[] mLatins;

    private final String mEngCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String mEng = "abcdefghijklmnopqrstuvwxyz";
    private final String mNumbers = "0123456789";
    private final String mSymbols = "!@#$%^&*_=+/(){}[];:'";

    public PasswordHelper(String[] russians, String[] latins) {
        this.mRussians = russians;
        this.mLatins = latins;

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
                result.append(Character.isUpperCase(c) ? mLatins[index].toUpperCase() : mLatins[index]);
            } else {
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

    public int calculateStrength(String password) {
        StringBuilder builder = new StringBuilder();
        if (Pattern.matches(".*\\d+.*", password))
            builder.append(mNumbers);
        if (Pattern.matches(".*[a-z]+.*", password))
            builder.append(mEng);
        if (Pattern.matches(".*[A-Z]+.*", password))
            builder.append(mEngCaps);
        if (Pattern.matches(".*[!@#$%^&*_=+/()'{};:\\[\\]]+.*", password))
            builder.append(mSymbols);

        double entropy = log2(Math.pow(builder.length(), password.length()));
        return (int) Math.round(entropy);
    }

    private double log2(double n) {
        return (Math.log(n) / Math.log(2));
    }
}
