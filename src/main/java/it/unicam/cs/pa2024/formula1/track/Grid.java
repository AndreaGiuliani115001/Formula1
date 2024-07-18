package it.unicam.cs.pa2024.formula1.track;

import it.unicam.cs.pa2024.formula1.player.Player;

import java.io.IOException;
import java.util.List;

/**
 * Interfaccia per rappresentare un tracciato di gara.
 */
public interface Grid {

    /**
     * Aggiunge una cella alla lista della linea di partenza.
     *
     * @param startCell La cella della linea di partenza da aggiungere.
     */
    void addStartLine(Cell startCell);

    /**
     * Aggiunge una cella alla lista della linea di arrivo.
     *
     * @param finishCell La cella della linea di arrivo da aggiungere.
     */
    void addFinishLine(Cell finishCell);

    /**
     * Restituisce la griglia di celle.
     *
     * @return La griglia di celle.
     */
    List<List<Cell>> getGrid();

    /**
     * Restituisce la lista delle celle che rappresentano la linea di partenza del tracciato.
     *
     * @return La lista delle celle della linea di partenza.
     */
    List<Cell> getStartLine();

    /**
     * Restituisce la lista delle celle che rappresentano la linea di arrivo del tracciato.
     *
     * @return La lista delle celle della linea di arrivo.
     */
    List<Cell> getFinishLine();

    /**
     * Restituisce la cella alla posizione specificata nella griglia.
     *
     * @param x La coordinata x della cella.
     * @param y La coordinata y della cella.
     * @return La cella alla posizione specificata, o null se non esiste.
     */
    Cell getCellAt(int x, int y);

    /**
     * Restituisce la lista dei giocatori.
     *
     * @return La lista dei giocatori.
     */
    List<Player> getPlayers();

    /**
     * Verifica se una determinata cella nella griglia è occupata da un giocatore.
     *
     * @param x La coordinata X della cella da verificare.
     * @param y La coordinata Y della cella da verificare.
     * @return true se la cella alle coordinate specificate è occupata da un giocatore, false altrimenti.
     */
    boolean isCellOccupiedByPlayer(int x, int y);

    /**
     * Restituisce il giocatore che occupa la cella specificata.
     *
     * @param x La coordinata x della cella.
     * @param y La coordinata y della cella.
     * @return Il giocatore che occupa la cella, o null se la cella non è occupata.
     */
    Player getPlayerAtCell(int x, int y);

    /**
     * Visualizza il tracciato.
     */
    void displayTrack();

    /**
     * Calcola la distanza tra due celle.
     *
     * @param cell1 La prima cella da confrontare.
     * @param cell2 La seconda cella da confrontare.
     * @return La distanza tra le due celle.
     */
    double calculateDistance(Cell cell1, Cell cell2);

}

