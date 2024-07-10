package it.unicam.cs.pa2024.formula1.engine;

import it.unicam.cs.pa2024.formula1.player.Player;
import it.unicam.cs.pa2024.formula1.track.*;

import java.util.List;

/**
 * Classe che gestisce le regole e il coordinamento della gara.
 */
public class GameEngine {

    private final Track track;
    private boolean raceInProgress;
    private int turnCounter;

    /**
     * Costruttore del GameEngine.
     *
     * @param track Il tracciato su cui si svolge la gara.
     */
    public GameEngine(Track track) {
        this.track = track;
        this.raceInProgress = false;
        this.turnCounter = 0;
    }

    /**
     * Avvia la gara.
     */
    public void startRace() {
        System.out.println("La gara sta per iniziare!");
        raceInProgress = true;

        //logica per il coordinamento della gara
        while (raceInProgress) {

            updateRaceState();
            track.displayTrack();
            turnCounter++;

            try {
                Thread.sleep(2000); // Aggiunge un ritardo di 2 secondi tra i turni

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        endRace();
    }

    /**
     * Aggiorna lo stato della gara.
     */
    private void updateRaceState() {
        List<Player> players = track.getPlayers();

        for (Player player : players) {

            player.makeMove();
            checkPlayerPosition(player);

        }
    }

    /**
     * Controlla la posizione del giocatore e aggiorna lo stato della gara se necessario.
     *
     * @param player Il giocatore di cui controllare la posizione.
     */
    private void checkPlayerPosition(Player player) {
        Cell cell = track.getCellAt(player.getX(), player.getY());
        if (cell == null || !cell.isTrack()) {
            System.out.println(player.getName() + " è uscito dal tracciato e viene eliminato!");
            track.getPlayers().remove(player);
        } else if (cell.isFinishCell()) {
            System.out.println(player.getName() + " ha tagliato il traguardo e vince la gara!");
            raceInProgress = false;
        }
    }

    /**
     * Termina la gara.
     */
    public void endRace() {
        System.out.println("La gara è terminata!");
        raceInProgress = false;
    }
}
