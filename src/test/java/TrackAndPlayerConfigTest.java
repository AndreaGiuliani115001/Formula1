import static org.junit.jupiter.api.Assertions.*;

import it.unicam.cs.pa2024.formula1.player.Player;
import it.unicam.cs.pa2024.formula1.track.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
        config = new TrackAndPlayerConfig("configTest.txt");

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
        assertTrue(track.getPlayers().isEmpty());

        config.loadTrack(track);

        assertTrue(startCell.isTrack() && startCell.isStartCell(), "La coordinata (0,0) dovrebbe essere parte del tracciato e della linea di inizio");
        assertTrue(trackCell.isTrack(), "La coordinata (2,2) dovrebbe essere parte del tracciato");
        assertTrue(finishCell.isTrack() && finishCell.isFinishCell(), "La coordinata (4,4) dovrebbe essere parte del tracciato e della linea di arrivo");

        assertEquals(1, track.getPlayers().size());
        Player player = track.getPlayers().getFirst();
        assertEquals("Andrea", player.getName(), "Il player aggiunto dovrebbe chiamarsi Andrea");
        assertEquals(0, player.getX(), "Il player aggiunto dovrebbe trovarsi nella coordinata X = 0");
        assertEquals(0, player.getY(), "Il player aggiunto dovrebbe trovarsi nella coordinata Y = 0");

    }

}


