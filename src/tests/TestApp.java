package tests;

import tests.Validation;
import java.util.Scanner;

public class TestApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Test Username Validation
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        if (Validation.isValidUsername(username)) {
            System.out.println("Valid username.");
        } else {
            System.out.println("Invalid username. Must be 1-32 characters (letters, numbers, underscores only).");
        }

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
        if (Validation.isValidPassword(password)) {
            System.out.println("Valid password.");
        } else {
            System.out.println("Invalid password. Must be at least 1 character long.");
        }

        System.out.print("Enter an artist name: ");
        String artistName = scanner.nextLine();
        if (Validation.isValidArtistName(artistName)) {
            System.out.println("Valid artist name.");
        } else {
            System.out.println("Invalid artist name. Must be at least 1 character long.");
        }

        System.out.print("Enter an album title: ");
        String albumTitle = scanner.nextLine();
        if (Validation.isValidAlbumTitle(albumTitle)) {
            System.out.println("Valid album title.");
        } else {
            System.out.println("Invalid album title. Must be at least 1 character long.");
        }

        System.out.print("Enter an album release date (yyyy-mm-dd): ");
        String releaseDate = scanner.nextLine();
        if (Validation.isValidDate(releaseDate)) {
            System.out.println("Valid date.");
        } else {
            System.out.println("Invalid date format. Use yyyy-mm-dd.");
        }

        System.out.print("Enter an album genre: ");
        String genre = scanner.nextLine();
        if (Validation.isValidGenre(genre)) {
            System.out.println("Valid genre.");
        } else {
            System.out.println("Invalid genre. Genre must contain at least one letter.");
        }

        scanner.close();
    }
}
