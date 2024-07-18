package it.unicam.cs.pa2024.formula1;

import it.unicam.cs.pa2024.formula1.engine.GameEngine;
import it.unicam.cs.pa2024.formula1.track.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Track track = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Benvenuto nel gioco Formula 1!");

        System.out.print("Inserisci la larghezza della griglia di gioco: ");
        int width = scanner.nextInt();

        System.out.print("Inserisci l'altezza della griglia di gioco: ");
        int height = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Hai già il file di configurazione del tracciato? S/N");
        String answer = scanner.nextLine();

        if (answer.startsWith("S")) {
            System.out.print("Inserisci il nome del file di configurazione del tracciato e dei player: ");
            String path = scanner.nextLine();
            try {
                track = new Track(width, height, path);
            } catch (Exception e) {
                System.err.println("Errore nella creazione del tracciato: " + e.getMessage());
            }
        } else track = new Track(width, height);


        System.out.println();

        track.displayTrack();

        boolean running = true;
        while (running) {
            System.out.println("\nCosa vuoi fare?");
            if (track.getPlayers().size() == 0) {
                System.out.println("1. Costruisci un nuovo tracciato e definisci nuovi player");
            } else System.out.println("1. Inizia la gara");
            System.out.println("2. Chiusura del gioco");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    if (track.getPlayers().size() == 0) {

                        System.out.print("Inserisci il nome del file di configurazione del tracciato e dei player: ");
                        String path = scanner.nextLine();

                        TrackAndPlayerConfig config = new TrackAndPlayerConfig(path);

                        try {
                            config.loadTrack(track);
                        } catch (Exception e) {
                            System.err.println("Errore nella lettura del file: " + e.getMessage());
                        }

                        System.out.println();
                        track.displayTrack();
                        break;

                    } else {
                        GameEngine gameEngine = new GameEngine(track);
                        gameEngine.startRace();
                        running = false;
                    }
                    break;

                case 2:
                    System.out.println("Chiusura del gioco...");
                    running = false;
                    break;

                default:
                    System.out.println("Scelta non valida, riprova.");
                    break;
            }
        }

        scanner.close();

    }
}
