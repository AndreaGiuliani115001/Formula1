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
public class Track implements Grid{
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
     * {@inheritDoc}
     */
    @Override
    public void addStartLine(Cell startCell) {
        this.startLine.add(startCell);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFinishLine(Cell finishLine) {
        this.finishLine.add(finishLine);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<Cell>> getGrid() {
        return grid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Cell> getStartLine() {
        return startLine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Cell> getFinishLine() {
        return finishLine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCellOccupiedByPlayer(int x, int y) {

        for (Player player : players) {
            if (player.getX() == x && player.getY() == y) {
                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayerAtCell(int x, int y) {

        for (Player player : players) {
            if (player.getX() == x && player.getY() == y) {
                return player;
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateDistance(Cell cell1, Cell cell2) {

        int dx = cell1.getX() - cell2.getX();
        int dy = cell1.getY() - cell2.getY();
        return Math.sqrt(dx * dx + dy * dy);

    }

}
