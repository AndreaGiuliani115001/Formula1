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

        int[] dx = {1, 0, 1, -1, 0, -1, 1, -1};
        int[] dy = {0, 1, 1, 0, -1, -1, -1, 1};
        Random randomMovement = new Random();

        if (this.currentPosition.isStartCell()) {


            int indexRandomMovement = randomMovement.nextInt(dx.length);

            //calcolo random della prima direzione da prendere
            int xDirection = dx[indexRandomMovement];
            int yDirection = dy[indexRandomMovement];

            int x = this.getX() + xDirection;
            int y = this.getY() + yDirection;

            if (track.getCellAt(x, y) != null
                    &&
                    !track.isCellOccupiedByPlayer(x, y)
                    &&
                    !track.getCellAt(x, y).isStartCell()
                    &&
                    track.getCellAt(x, y).isTrack()) {

                this.lastPosition = this.currentPosition;
                this.setCurrentPosition(track.getCellAt(x, y));
            } else makeMove(track);

        } else {

            //calcolo del punto principale
            int xDif = this.lastPosition.getX() + this.currentPosition.getX();
            int yDif = this.lastPosition.getY() + this.currentPosition.getY();
            int xPrincipalPoint = xDif + this.currentPosition.getX();
            int yPrincipalPoint = yDif + this.currentPosition.getY();
            Cell principalPoint = track.getCellAt(xPrincipalPoint, yPrincipalPoint);

            //calcolo degli otto vicini del punto principale
            List<Cell> eightNeighbors = new ArrayList<>();
            eightNeighbors.add(principalPoint);

            for (int i = 0; i < dx.length; i++) {
                if (track.getCellAt(principalPoint.getX() + dx[i], principalPoint.getY() + dy[i]) != null) {
                    eightNeighbors.add(track.getCellAt(principalPoint.getX() + dx[i], principalPoint.getY() + dy[i]));
                }
            }

            this.lastPosition = currentPosition;
            int indexRandomMovement = randomMovement.nextInt(eightNeighbors.size());
            this.currentPosition = eightNeighbors.get(indexRandomMovement);

        }

    }
}

