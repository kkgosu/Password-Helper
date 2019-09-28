package com.sber.rupassword;

public class PasswordHelper {
    private final String[] russians;
    private final String[] latins;

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
}
