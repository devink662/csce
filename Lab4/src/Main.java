/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 */
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        LinkedQueue<Song> fullPlaylist = new LinkedQueue<>();
        LinkedQueue<Song> userPlaylist = new LinkedQueue<>();
        LinkedQueue<Song> excludedSongs = new LinkedQueue<>();
        Scanner scanner = new Scanner(System.in);

        // Read playlist.txt file into the queue
        System.out.print("Enter the name of the playlist file: ");
        String inputFileName = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\t");
                fullPlaylist.enqueue(new Song(fields[0], fields[1], fields[2], Integer.parseInt(fields[3])));
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        // Prompt the user for the length of the playlist
        System.out.print("How long of a playlist do you need (in minutes)? ");
        int playlistLength = scanner.nextInt() * 60; // Convert minutes to seconds
        scanner.nextLine(); // Consume the newline

        // Ask the user if they want to exclude any song, artist, or genre
        System.out.print("Is there any song, artist, or genre of music you don't want to hear? (yes/no): ");
        String excludeResponse = scanner.nextLine();
        String excludeType = "";
        String excludeValue = "";
        if (excludeResponse.equalsIgnoreCase("yes")) {
            System.out.print("Enter 1 to exclude a song, 2 to exclude an artist, or 3 to exclude a genre: ");
            excludeType = scanner.nextLine();
            System.out.print("Enter the " + (excludeType.equals("1") ? "song title" : excludeType.equals("2") ? "artist" : "genre") + " to exclude: ");
            excludeValue = scanner.nextLine();
        }

        // Dequeue songs to create the user playlist and the list of excluded songs
        int currentPlaylistLength = 0;
        while (!fullPlaylist.isEmpty() && currentPlaylistLength < playlistLength) {
            Song currentSong = fullPlaylist.dequeue();
            boolean excludeSong = false;
            switch (excludeType) {
                case "1": // Exclude by song title
                    if (currentSong.getTitle().equalsIgnoreCase(excludeValue)) {
                        excludeSong = true;
                    }
                    break;
                case "2": // Exclude by artist
                    if (currentSong.getArtist().equalsIgnoreCase(excludeValue)) {
                        excludeSong = true;
                    }
                    break;
                case "3": // Exclude by genre
                    if (currentSong.getGenre().equalsIgnoreCase(excludeValue)) {
                        excludeSong = true;
                    }
                    break;
            }

            if (excludeSong) {
                excludedSongs.enqueue(currentSong);
            } else {
                userPlaylist.enqueue(currentSong);
                currentPlaylistLength += currentSong.getLength();
            }
        }

        // Output the lengths and save the playlists to files
        System.out.println("The playlist created for you in the file userplaylist.txt is " + (currentPlaylistLength / 60.0) + " minutes long");

        writeQueueToFile("userplaylist.txt", userPlaylist);
        writeQueueToFile("unexaminedsongs.txt", fullPlaylist);
        writeQueueToFile("excludedsongs.txt", excludedSongs);
    }

    // Method to write the contents of a queue to a file
    private static void writeQueueToFile(String fileName, LinkedQueue<Song> queue) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(queue.toString());
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}