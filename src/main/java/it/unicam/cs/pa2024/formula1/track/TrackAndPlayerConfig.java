package it.unicam.cs.pa2024.formula1.track;

import it.unicam.cs.pa2024.formula1.player.BotPlayer;
import it.unicam.cs.pa2024.formula1.player.HumanPlayer;
import it.unicam.cs.pa2024.formula1.player.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Questa classe gestisce la configurazione del tracciato e dei giocatori.
 * Tramite lettura di un file vengono definiti:
 * - Giocatori partecipanti alla gara (Umani e bot)
 * - Forma del tracciato, con relativa linea di inizio e fine.
 */
public class TrackAndPlayerConfig {

    private final String configFilePath;


    /**
     * Costruttore della classe che imposta il percorso del file di configurazione.
     *
     * @param configFilePath Percorso del file di configurazione da cui leggere i dati.
     */
    public TrackAndPlayerConfig(String configFilePath) {
        this.configFilePath = configFilePath;
    }


    /**
     * Legge le righe dal file di configurazione specificato.
     *
     * @param track Griglia da aggiornare con le informazioni del tracciato nel file.
     * @throws IOException Se si verifica un errore durante la lettura del file.
     */
    public void loadTrack(Track track) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(configFilePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("START_CELL:")) {
                    readCells(reader, track, "start");

                } else if (line.startsWith("FINISH_CELL:")) {
                    readCells(reader, track, "finish");

                } else if (line.startsWith("TRACK_CELL:")) {
                    readCells(reader, track, "track");

                } else if (line.startsWith("PLAYER:")) {
                    readPlayers(reader, track);
                } else {
                    throw new IllegalArgumentException("Tipo di cella non valido: " + line);
                }

            }
        }
    }

    /**
     * Aggiorna lo stato delle celle specifiche del file di configurazione
     * nella griglia di gioco e definisce il tracciato.
     *
     * @param reader   BufferedReader utilizzato per leggere il file di configurazione.
     * @param track    Oggetto Track da aggiornare con le informazioni lette dal file.
     * @param cellType Tipo di cella da leggere e aggiornare (start, finish, track).
     * @throws IOException Se si verifica un errore durante la lettura del file.
     */
    private void readCells(BufferedReader reader, Track track, String cellType) throws IOException {
        String line;

        while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {

            String[] parts = line.trim().split(",");
            int x = Integer.parseInt(parts[0].trim());
            int y = Integer.parseInt(parts[1].trim());
            Cell cell = track.getCellAt(x, y);

            switch (cellType) {

                case "start":
                    cell.setStartCell(true);
                    cell.setTrack(true);
                    track.addStartLine(cell);
                    break;

                case "finish":
                    cell.setFinishCell(true);
                    cell.setTrack(true);
                    track.addFinishLine(cell);
                    break;

                case "track":
                    cell.setTrack(true);
                    break;

            }
        }
    }

    /**
     * Legge le informazioni dei giocatori dal file di configurazione specificato e li crea.
     *
     * @param reader BufferedReader utilizzato per leggere il file di configurazione.
     * @param track  Oggetto Track a cui aggiungere i giocatori letti dal file.
     * @throws IOException Se si verifica un errore durante la lettura del file.
     */
    private void readPlayers(BufferedReader reader, Track track) throws IOException {
        String line;

        while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {

            String[] parts = line.trim().split(",");
            String playerType = parts[0].trim();
            String playerName = parts[1].trim();
            int x = Integer.parseInt(parts[2].trim());
            int y = Integer.parseInt(parts[3].trim());

            Cell currentPosition = track.getCellAt(x, y);
            Player player;

            if ("BOT".equals(playerType)) {
                player = new BotPlayer(playerName, currentPosition);
                track.addPlayer(player);

            } else if ("HUMAN".equals(playerType)) {
                player = new HumanPlayer(playerName, currentPosition);
                track.addPlayer(player);

            } else {
                throw new IllegalArgumentException("Tipo di player non valido: " + playerType);
            }

        }
    }


}
