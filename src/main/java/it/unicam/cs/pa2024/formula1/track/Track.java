package it.unicam.cs.pa2024.formula1.track;

import it.unicam.cs.pa2024.formula1.player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe rappresenta la griglia su cui si definisce il tracciato.
 * La griglia è composta da un insieme di celle (grid),
 * di cui alcune di esse formano il tracciato:
 * - linea di inizio (startLine),
 * - linea di fine (finishLine).
 */
public class Track {
    private List<List<Cell>> grid;
    private List<Cell> startLine;
    private List<Cell> finishLine;
    private List<Player> players;


    /**
     * Costruttore per creare una nuova griglia, con larghezza e altezza specifiche.
     *
     * @param width  La larghezza della griglia (numero di colonne).
     * @param height L'altezza della griglia (numero di righe).
     */
    public Track(int width, int height) {
        this.grid = new ArrayList<>();
        this.startLine = new ArrayList<>();
        this.finishLine = new ArrayList<>();
        this.players = new ArrayList<>();

        for (int i = 0; i < height; i++) {

            List<Cell> row = new ArrayList<>();

            for (int j = 0; j < width; j++) {
                row.add(new Cell(j, i, false, false, false));
            }

            this.grid.add(row);

        }
    }

    /**
     * Costruttore per creare una nuova griglia, con larghezza e altezza specifiche,
     * e definire un nuovo tracciato da configurare tramite file.
     *
     * @param width          La larghezza della griglia (numero di colonne).
     * @param height         L'altezza della griglia (numero di righe).
     * @param configFilePath il percorso del file di configurazione del tracciato.
     * @throws IOException Se si verifica un errore durante la lettura del file di configurazione.
     */
    public Track(int width, int height, String configFilePath) throws IOException {
        this(width, height);

        // Inizio della configurazione del tracciato
        if (configFilePath != null) {
            TrackAndPlayerConfig config = new TrackAndPlayerConfig(configFilePath);
            config.loadTrack(this);
        }
    }

    /**
     * Aggiunge una cella alla lista della linea di partenza.
     *
     * @param startCell La cella della linea di partenza da aggiungere.
     */
    public void addStartLine(Cell startCell) {
        this.startLine.add(startCell);
    }

    /**
     * Aggiunge una cella alla lista della linea di arrivo.
     *
     * @param finishLine La cella della linea di arrivo da aggiungere.
     */
    public void addFinishLine(Cell finishLine) {
        this.finishLine.add(finishLine);
    }

    /**
     * Restituisce la griglia di celle.
     *
     * @return La griglia di celle.
     */
    public List<List<Cell>> getGrid() {
        return grid;
    }

    /**
     * Restituisce la lista delle celle che rappresentano la linea di partenza del tracciato.
     *
     * @return La lista delle celle della linea di partenza.
     */
    public List<Cell> getStartLine() {
        return startLine;
    }

    /**
     * Restituisce la lista delle celle che rappresentano la linea di arrivo del tracciato.
     *
     * @return La lista delle celle della linea di arrivo.
     */
    public List<Cell> getFinishLine() {
        return finishLine;
    }

    /**
     * Restituisce la cella alla posizione specificata nella griglia.
     *
     * @param x La coordinata x della cella.
     * @param y La coordinata y della cella.
     * @return La cella alla posizione specificata, o null se non esiste.
     */
    public Cell getCellAt(int x, int y) {

        if (y >= 0 && y < grid.size()) {
            List<Cell> row = grid.get(y);
            if (x >= 0 && x < row.size()) {
                return row.get(x);
            }
        }

        return null;
    }

    /**
     * Aggiunge un giocatore alla lista dei giocatori.
     *
     * @param player Il giocatore da aggiungere.
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Restituisce la lista dei giocatori.
     *
     * @return La lista dei giocatori.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Verifica se una determinata cella nella griglia è occupata da un giocatore.
     *
     * @param x La coordinata X della cella da verificare.
     * @param y La coordinata Y della cella da verificare.
     * @return true se la cella alle coordinate specificate è occupata da un giocatore, false altrimenti.
     */
    public boolean isCellOccupiedByPlayer(int x, int y) {

        for (Player player : players) {
            if (player.getX() == x && player.getY() == y) {
                return true;
            }
        }

        return false;
    }

    /**
     * Restituisce il giocatore che occupa la cella specificata.
     *
     * @param x La coordinata x della cella.
     * @param y La coordinata y della cella.
     * @return Il giocatore che occupa la cella, o null se la cella non è occupata.
     */
    public Player getPlayerAtCell(int x, int y) {

        for (Player player : players) {
            if (player.getX() == x && player.getY() == y) {
                return player;
            }
        }

        return null;
    }

    /**
     * Visualizza il tracciato.
     */
    public void displayTrack() {

        for (int y = 0; y < grid.size(); y++) {

            for (int x = 0; x < grid.get(0).size(); x++) {

                if (isCellOccupiedByPlayer(x, y)) {
                    Player player = getPlayerAtCell(x, y);
                    System.out.print(player.getName().charAt(0) + "   "); // Mostra l'iniziale del nome del giocatore

                } else {
                    Cell cell = getCellAt(x, y);

                    if (cell.isStartCell()) {
                        System.out.print("S   "); // Cella di start

                    } else if (cell.isFinishCell()) {
                        System.out.print("F   "); // Cella di finish

                    } else if (cell.isTrack()) {
                        System.out.print("T   "); // Cella di track

                    } else {
                        System.out.print(".   "); // Cella base

                    }
                }
            }
            System.out.println();
        }
    }

}
