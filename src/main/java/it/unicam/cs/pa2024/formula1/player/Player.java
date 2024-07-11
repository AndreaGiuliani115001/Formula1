package it.unicam.cs.pa2024.formula1.player;

import it.unicam.cs.pa2024.formula1.track.Cell;
import it.unicam.cs.pa2024.formula1.track.Track;

/**
 * Interfaccia che rappresenta un giocatore nel gioco.
 * Definisce i metodi che devono essere implementati da ogni tipo di giocatore,
 * che sia un giocatore interattivo (umano) o programmato (bot).
 */
public interface Player {
    /**
     * Restituisce il nome del giocatore.
     *
     * @return Il nome del giocatore.
     */
    String getName();

    /**
     * Restituisce la coordinata X del giocatore.
     *
     * @return La coordinata X del giocatore.
     */
    int getX();

    /**
     * Restituisce la coordinata Y del giocatore.
     *
     * @return La coordinata Y del giocatore.
     */
    int getY();

    /**
     * Restituisce la posizione corrente del giocatore.
     */
    Cell getCurrentPosition();

    /**
     * Aggiorna la posizione corrente del giocatore.
     *
     * @param newPosition la nuova posizione del giocatore (cella).
     */
    void setCurrentPosition(Cell newPosition);

    /**
     * Effettua una mossa per il giocatore.
     * Questo metodo è chiamato per far compiere una mossa al giocatore.
     *
     * @param track il tracciato su cui il giocatore si muove.
     */
    void makeMove(Track track);
}
