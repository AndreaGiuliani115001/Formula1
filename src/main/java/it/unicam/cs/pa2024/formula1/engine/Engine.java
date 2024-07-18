package it.unicam.cs.pa2024.formula1.engine;

/**
 * Interfaccia per gestire le regole e il coordinamento della gara.
 */
public interface Engine {

    /**
     * Avvia la gara.
     */
    void startRace();

    /**
     * Verifica se la gara è in corso.
     *
     * @return true se la gara è in corso, false altrimenti.
     */
    boolean isRaceInProgress();

}
