package it.unicam.cs.pa2024.formula1.track;

/**
 * Questa classe rappresenta una singola cella all'interno della griglia.
 * La cella viene identificata mediante coordinata (x, y) e può essere marcata come:
 * Cella di inizio tracciato (isStartCell),
 * Cella di fine tracciato (isFinishCell),
 * Cella che fa parte del tracciato (isTrack).
 */
public class Cell {
    private final int x;
    private final int y;
    private boolean isStartCell;
    private boolean isFinishCell;
    private boolean isTrack;

    /**
     * Costruttore per creare una nuova cella.
     *
     * @param x            La coordinata x della cella (asse orizzontale).
     * @param y            La coordinata y della cella (asse verticale).
     * @param isStartCell  true se la cella rappresenta l'inizio del tracciato
     * @param isFinishCell true se la cella rappresenta la fine del tracciato
     * @param isTrack      true se la cella fa parte del tracciato
     */
    public Cell(int x, int y, boolean isStartCell, boolean isFinishCell, boolean isTrack) {
        this.x = x;
        this.y = y;
        this.isStartCell = isStartCell;
        this.isFinishCell = isFinishCell;
        this.isTrack = isTrack;
    }

    /**
     * Restituisce la coordinata x della cella.
     *
     * @return La coordinata x della cella.
     */
    public int getX() {
        return x;
    }

    /**
     * Restituisce la coordinata y della cella.
     *
     * @return La coordinata y della cella.
     */
    public int getY() {
        return y;
    }

    /**
     * Verifica se la cella è la linea di partenza.
     *
     * @return true se la cella è la linea di partenza, false altrimenti.
     */
    public boolean isStartCell() {
        return isStartCell;
    }

    /**
     * Imposta se la cella è la linea di partenza.
     *
     * @param startCell true se la cella è la linea di partenza, false altrimenti.
     */
    public void setStartCell(boolean startCell) {
        isStartCell = startCell;
    }

    /**
     * Verifica se la cella è la linea di arrivo.
     *
     * @return true se la cella è la linea di arrivo, false altrimenti.
     */
    public boolean isFinishCell() {
        return isFinishCell;
    }

    /**
     * Imposta se la cella è la linea di arrivo.
     *
     * @param finishCell true se la cella è la linea di arrivo, false altrimenti.
     */
    public void setFinishCell(boolean finishCell) {
        isFinishCell = finishCell;
    }

    /**
     * Verifica se la cella fa parte del tracciato.
     *
     * @return true se la cella fa parte del tracciato, false altrimenti.
     */
    public boolean isTrack() {
        return isTrack;
    }

    /**
     * Imposta se la cella fa parte del tracciato.
     *
     * @param track true se la cella fa parte del tracciato, false altrimenti.
     */
    public void setTrack(boolean track) {
        isTrack = track;
    }
}
