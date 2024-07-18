package it.unicam.cs.pa2024.formula1.track;

/**
 * Questa classe rappresenta una singola cella all'interno della griglia.
 * La cella viene identificata mediante coordinata (x, y) e può essere marcata come:
 * Cella di inizio tracciato (isStartCell),
 * Cella di fine tracciato (isFinishCell),
 * Cella che fa parte del tracciato (isTrack).
 */
public class Cell implements Coordinate{
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
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStartCell() {
        return isStartCell;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStartCell(boolean startCell) {
        isStartCell = startCell;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinishCell() {
        return isFinishCell;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFinishCell(boolean finishCell) {
        isFinishCell = finishCell;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTrack() {
        return isTrack;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTrack(boolean track) {
        isTrack = track;
    }
}
