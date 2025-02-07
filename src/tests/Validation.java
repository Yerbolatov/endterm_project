package tests;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Validation {

    public static boolean isValidUsername(String username) {
        return username != null && username.matches("^[a-zA-Z0-9_]{1,32}$");
    }

    public static boolean isValidPassword(String password) {
        return password != null;
    }

    public static boolean isValidArtistName(String name) {
        return name != null;
    }

    public static boolean isValidAlbumTitle(String title) {
        return title != null;
    }

    public static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidGenre(String genre) {
        return genre != null;
    }
}