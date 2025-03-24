package org.laboratorio3.enums;

public enum BloodType {
    A_POSITIVE, A_NEGATIVE, B_POSITIVE, B_NEGATIVE, AB_POSITIVE, AB_NEGATIVE, O_POSITIVE, O_NEGATIVE;

    public static String getBloodType(BloodType bloodType) {
        switch (bloodType) {
            case A_POSITIVE:
                return "A+";
            case A_NEGATIVE:
                return "A-";
            case B_POSITIVE:
                return "B+";
            case B_NEGATIVE:
                return "B-";
            case AB_POSITIVE:
                return "AB+";
            case AB_NEGATIVE:
                return "AB-";
            case O_POSITIVE:
                return "O+";
            case O_NEGATIVE:
                return "O-";
            default:
                return "Unknown";
        }
    }

    public static BloodType getBloodType(String bloodType) {
        switch (bloodType) {
            case "A+":
                return A_POSITIVE;
            case "A-":
                return A_NEGATIVE;
            case "B+":
                return B_POSITIVE;
            case "B-":
                return B_NEGATIVE;
            case "AB+":
                return AB_POSITIVE;
            case "AB-":
                return AB_NEGATIVE;
            case "O+":
                return O_POSITIVE;
            case "O-":
                return O_NEGATIVE;
            default:
                return null;
        }
    }
}
