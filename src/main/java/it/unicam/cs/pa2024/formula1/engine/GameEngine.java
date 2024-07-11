package it.unicam.cs.pa2024.formula1.engine;

import it.unicam.cs.pa2024.formula1.player.Player;
import it.unicam.cs.pa2024.formula1.track.*;

import java.util.Iterator;
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
        this.turnCounter = 1;
    }

    /**
     * Avvia la gara.
     */
    public void startRace() {
        System.out.println("La gara sta per iniziare!");
        raceInProgress = true;

        //logica per il coordinamento della gara
        while (raceInProgress) {

            System.out.println("Siamo al " + turnCounter + " turno");

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
        Iterator<Player> iterator = players.iterator();

        while (iterator.hasNext()) {

            Player player = iterator.next();
            player.makeMove(track);
            checkPlayerPosition(player, iterator);

        }
    }

    /**
     * Controlla la posizione del giocatore e aggiorna lo stato della gara se necessario.
     *
     * @param player   Il giocatore di cui controllare la posizione.
     * @param iterator
     */
    private void checkPlayerPosition(Player player, Iterator<Player> iterator) {
        Cell cell = player.getCurrentPosition();
        if (cell == null || !cell.isTrack()) {
            System.out.println(player.getName() + " è uscito dal tracciato e viene eliminato!");
            iterator.remove();
            if(!iterator.hasNext()) raceInProgress = false;
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
