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

        System.out.print("Inserisci il percorso del file di configurazione del tracciato e dei player: ");
        String path = scanner.nextLine();

        try {
            track = new Track(width, height, path);
        } catch (Exception e) {
            System.err.println("Errore nella creazione del tracciato: " + e.getMessage());
        }

        System.out.println();

        track.displayTrack();

        boolean running = true;
        while (running) {
            System.out.println("\nCosa vuoi fare?");
            System.out.println("1. Inizia la gara");
            System.out.println("2. Chiusura del gioco");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    GameEngine gameEngine = new GameEngine(track);
                    gameEngine.startRace();
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
