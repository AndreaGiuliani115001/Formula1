package it.unicam.cs.pa2024.formula1.player;

import it.unicam.cs.pa2024.formula1.track.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe che rappresenta un giocatore programmato (bot).
 * Implementa l'interfaccia Player e rappresenta un giocatore che si muove
 * senza interazione attiva dell'utente.
 */
public class BotPlayer implements Player {
    private String name;
    private Cell currentPosition;
    private Cell lastPosition;

    /**
     * Costruttore per creare un nuovo BotPlayer con il nome e la posizione specificati.
     *
     * @param name            Il nome del bot.
     * @param currentPosition la posizione corrente del bot (cella).
     */
    public BotPlayer(String name, Cell currentPosition) {
        this.name = name;
        this.currentPosition = currentPosition;
        this.lastPosition = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return currentPosition.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return currentPosition.getY();
    }

    /**
     * {@inheritDoc}
     */
    public Cell getCurrentPosition() {
        return this.currentPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentPosition(Cell newPosition) {
        this.currentPosition = newPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeMove(Track track) {

        //tutte le possibili direzioni del giocatore bot
        int[] dx = {1, 0, 1, -1, 0, -1, 1, -1};
        int[] dy = {0, 1, 1, 0, -1, -1, -1, 1};

        Random randomMovement = new Random();

        Cell principalPoint = calculatePrincipalPoint(track);
        List<Cell> eightNeighborsValid = new ArrayList<>();
        List<Cell> eightNeighborsNotValid = new ArrayList<>();

        if (!principalPoint.equals(this.currentPosition)) eightNeighborsValid.add(principalPoint);

        for (int i = 0; i < dx.length; i++) {

            Cell neighbors = track.getCellAt(principalPoint.getX() + dx[i], principalPoint.getY() + dy[i]);

            if (isValidMove(track, neighbors) && !neighbors.equals(this.lastPosition)) {

                eightNeighborsValid.add(neighbors);

            } else eightNeighborsNotValid.add(neighbors);
        }

        this.lastPosition = currentPosition;

        if (!eightNeighborsValid.isEmpty()) {

            List<Cell> closestCells = new ArrayList<>();
            double minDistance = Double.MAX_VALUE;

            for (Cell validCell : eightNeighborsValid) {
                for (Cell finishCell : track.getFinishLine()) {
                    double distance = track.calculateDistance(validCell, finishCell);
                    if (distance < minDistance) {
                        closestCells.add(validCell);
                        minDistance = distance;
                    }
                }
            }

            int indexRandomMovement = randomMovement.nextInt(closestCells.size());
            this.currentPosition = closestCells.get(indexRandomMovement);

        } else {
            int indexRandomMovement = randomMovement.nextInt(eightNeighborsNotValid.size());
            this.currentPosition = eightNeighborsNotValid.get(indexRandomMovement);
        }
    }


    /**
     * Verifica se una mossa è valida in base alla posizione specificata.
     * Una cella è considerata come posizione valida se: fa parte del tracciato,
     * non è già occupata da un player e se fa parte della griglia (!= null).
     *
     * @param track        Il tracciato su cui si muove il giocatore.
     * @param cellToVerify la cella da verificare.
     * @return {@code true} se la cella è valida per il movimento; {@code false} altrimenti.
     */
    private boolean isValidMove(Track track, Cell cellToVerify) {
        return (cellToVerify != null && !track.isCellOccupiedByPlayer(cellToVerify.getX(), cellToVerify.getY()) && cellToVerify.isTrack());
    }

    /**
     * Calcola il punto principale basato sulle posizioni attuale e precedente del giocatore.
     *
     * @param track Il tracciato su cui si muove il giocatore.
     * @return La cella che rappresenta il punto principale.
     */
    private Cell calculatePrincipalPoint(Track track) {

        if (this.currentPosition.isStartCell()) return this.currentPosition;

        int xDif = this.currentPosition.getX() - this.lastPosition.getX();
        int yDif = this.currentPosition.getY() - this.lastPosition.getY();
        int xPrincipalPoint = this.currentPosition.getX() + xDif;
        int yPrincipalPoint = this.currentPosition.getY() + yDif;

        Cell principalPoint = track.getCellAt(xPrincipalPoint, yPrincipalPoint);

        if (isValidMove(track, principalPoint)) return principalPoint;
        else return this.currentPosition;
    }

}

