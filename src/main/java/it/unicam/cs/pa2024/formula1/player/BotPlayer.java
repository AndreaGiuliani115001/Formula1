package it.unicam.cs.pa2024.formula1.player;

import it.unicam.cs.pa2024.formula1.track.Cell;

/**
 * Classe che rappresenta un giocatore programmato (bot).
 * Implementa l'interfaccia Player e rappresenta un giocatore che si muove
 * senza interazione attiva dell'utente.
 */
public class BotPlayer implements Player {
    private String name;
    private Cell currentPosition;

    /**
     * Costruttore per creare un nuovo BotPlayer con il nome e la posizione specificati.
     *
     * @param name        Il nome del bot.
     * @param currentPosition la posizione corrente del bot (cella).
     */
    public BotPlayer(String name, Cell currentPosition) {
        this.name = name;
        this.currentPosition = currentPosition;
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
    public void makeMove() {

        //da fare

    }
}

