package it.unicam.cs.pa2024.formula1.engine;

import it.unicam.cs.pa2024.formula1.player.*;
import it.unicam.cs.pa2024.formula1.track.*;

import java.util.Iterator;

/**
 * Classe che gestisce le regole e il coordinamento della gara.
 */
public class GameEngine implements Engine {

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
     * {@inheritDoc}
     */
    @Override
    public void startRace() {
        System.out.println("La gara sta per iniziare!");
        raceInProgress = true;

        //logica per il coordinamento della gara
        while (raceInProgress) {

            System.out.println("Siamo al " + turnCounter + " turno");
            System.out.println();

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
     * {@inheritDoc}
     */
    @Override
    public boolean isRaceInProgress() {
        return raceInProgress;
    }

    /**
     * Aggiorna lo stato della gara.
     */
    private void updateRaceState() {

        Iterator<Player> iterator = track.getPlayers().iterator();

        while (iterator.hasNext()) {

            Player player = iterator.next();
            player.makeMove(track);
            checkPlayerPosition(player, iterator);
            if (!this.isRaceInProgress()) break;

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

            if (track.getPlayers().size() == 1) {

                System.out.println("Vittoria a tavolino!");
                raceInProgress = false;

            } else if (track.getPlayers().size() == 0) {

                System.out.println("Tutti i giocatori sono usciti dal tracciato! nessuna vittoria...");
                raceInProgress = false;
            }

        } else if (cell.isFinishCell()) {

            System.out.println(player.getName() + " ha tagliato il traguardo e vince la gara!");
            raceInProgress = false;
        }
    }

    /**
     * Termina la gara.
     */
    private void endRace() {
        System.out.println("Termine della gara");
    }
}
