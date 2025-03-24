package org.laboratorio3.enums;

public enum Gender {
    MALE, FEMALE, OTHER;

    public static String getGender(Gender gender) {
        return switch (gender) {
            case MALE -> "Male";
            case FEMALE -> "Female";
            case OTHER -> "Other";
            default -> throw new IllegalArgumentException("Unknown gender: " + gender);
        };
    }

    public static Gender getGender(String gender) {
        return switch (gender.toLowerCase()) {
            case "male" -> MALE;
            case "female" -> FEMALE;
            case "other" -> OTHER;
            default -> throw new IllegalArgumentException("Unknown gender: " + gender);
        };
    }
}