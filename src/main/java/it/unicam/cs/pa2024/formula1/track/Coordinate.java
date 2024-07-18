package it.unicam.cs.pa2024.formula1.track;

/**
 * Interfaccia che rappresenta una singola cella all'interno della griglia.
 * La cella viene identificata mediante coordinate (x, y) e può essere marcata come:
 * - Cella di inizio tracciato
 * - Cella di fine tracciato
 * - Cella che fa parte del tracciato
 */
public interface Coordinate {

    /**
     * Restituisce la coordinata x della cella.
     *
     * @return La coordinata x della cella.
     */
    int getX();

    /**
     * Restituisce la coordinata y della cella.
     *
     * @return La coordinata y della cella.
     */
    int getY();

    /**
     * Verifica se la cella è la linea di partenza.
     *
     * @return true se la cella è la linea di partenza, false altrimenti.
     */
    boolean isStartCell();

    /**
     * Imposta se la cella è la linea di partenza.
     *
     * @param startCell true se la cella è la linea di partenza, false altrimenti.
     */
    void setStartCell(boolean startCell);

    /**
     * Verifica se la cella è la linea di arrivo.
     *
     * @return true se la cella è la linea di arrivo, false altrimenti.
     */
    boolean isFinishCell();

    /**
     * Imposta se la cella è la linea di arrivo.
     *
     * @param finishCell true se la cella è la linea di arrivo, false altrimenti.
     */
    void setFinishCell(boolean finishCell);

    /**
     * Verifica se la cella fa parte del tracciato.
     *
     * @return true se la cella fa parte del tracciato, false altrimenti.
     */
    boolean isTrack();

    /**
     * Imposta se la cella fa parte del tracciato.
     *
     * @param track true se la cella fa parte del tracciato, false altrimenti.
     */
    void setTrack(boolean track);
}
