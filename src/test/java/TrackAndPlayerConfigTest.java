import static org.junit.jupiter.api.Assertions.*;

import it.unicam.cs.pa2024.formula1.player.*;
import it.unicam.cs.pa2024.formula1.track.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.mockito.Mockito.*;

/**
 * Classe di test per la configurazione del tracciato e dei giocatori.
 * Verifica che la classe TrackAndPlayerConfig carichi correttamente le informazioni
 * dal file di configurazione e aggiorni il tracciato e i giocatori di conseguenza.
 */
public class TrackAndPlayerConfigTest {

    private Track track;
    private TrackAndPlayerConfig config;

    /**
     * Configura la griglia e il percorso del file di configurazione prima di ogni test.
     */
    @BeforeEach
    public void setUp() throws IOException {

        track = new Track(10, 10);
        config = new TrackAndPlayerConfig("C:\\Users\\giuli\\OneDrive\\Desktop\\configTest.txt");

    }

    /**
     * Testa il caricamento delle celle di partenza dal file di configurazione.
     * Verifica che le chiamate appropriate siano state effettuate su Track.
     *
     * @throws IOException Se si verifica un errore durante la lettura del file.
     */
    @Test
    public void testLoadTrack() throws IOException {

        Cell startCell = track.getCellAt(0, 0);
        Cell trackCell = track.getCellAt(2, 2);
        Cell finishCell = track.getCellAt(4, 4);

        assertFalse(startCell.isTrack() && startCell.isStartCell(), "La coordinata (0,0) non dovrebbe essere parte del tracciato e della linea di inizio");
        assertFalse(trackCell.isTrack(), "La coordinata (2,2) non dovrebbe essere parte del tracciato");
        assertFalse(finishCell.isTrack() && finishCell.isFinishCell(), "La coordinata (4,4) non dovrebbe essere parte del tracciato e della linea di arrivo");

        config.loadTrack(track);

        assertTrue(startCell.isTrack() && startCell.isStartCell(), "La coordinata (0,0) dovrebbe essere parte del tracciato e della linea di inizio");
        assertTrue(trackCell.isTrack(), "La coordinata (2,2) dovrebbe essere parte del tracciato");
        assertTrue(finishCell.isTrack() && finishCell.isFinishCell(), "La coordinata (4,4) dovrebbe essere parte del tracciato e della linea di arrivo");

    }

    /**
     * Testa il caricamento delle celle di arrivo dal file di configurazione.
     * Verifica che le chiamate appropriate siano state effettuate su Track.
     *
     * @throws IOException Se si verifica un errore durante la lettura del file.
     */
    @Test
    public void testLoadTrackFinishCells() throws IOException {
        String configContent = """
                FINISH_CELL:
                2, 0
                3, 0
                """;

        BufferedReader reader = new BufferedReader(new StringReader(configContent));

        config.loadTrack(track);

        verify(track, times(2)).getCellAt(anyInt(), anyInt());
        verify(track, times(2)).addFinishLine(any(Cell.class));
    }

    /**
     * Testa il caricamento delle celle del tracciato dal file di configurazione.
     * Verifica che le chiamate appropriate siano state effettuate su Track.
     *
     * @throws IOException Se si verifica un errore durante la lettura del file.
     */
    @Test
    public void testLoadTrackCells() throws IOException {
        String configContent = """
                TRACK_CELL:
                0, 1
                1, 1
                """;

        BufferedReader reader = new BufferedReader(new StringReader(configContent));

        config.loadTrack(track);

        verify(track, times(2)).getCellAt(anyInt(), anyInt());
    }

    /**
     * Testa il caricamento dei giocatori dal file di configurazione.
     * Verifica che le chiamate appropriate siano state effettuate su Track.
     *
     * @throws IOException Se si verifica un errore durante la lettura del file.
     */
    @Test
    public void testLoadPlayers() throws IOException {
        String configContent = """
                PLAYER:
                BOT, BotPlayer1, 0, 0
                HUMAN, HumanPlayer1, 1, 1
                """;

        BufferedReader reader = new BufferedReader(new StringReader(configContent));

        config.loadTrack(track);

        verify(track, times(2)).getCellAt(anyInt(), anyInt());
        verify(track, times(1)).addPlayer(any(BotPlayer.class));
        verify(track, times(1)).addPlayer(any(HumanPlayer.class));
    }
}


