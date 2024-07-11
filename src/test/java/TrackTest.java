import static org.junit.jupiter.api.Assertions.*;

import it.unicam.cs.pa2024.formula1.player.BotPlayer;
import it.unicam.cs.pa2024.formula1.player.Player;
import it.unicam.cs.pa2024.formula1.track.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 * Classe di test per la classe Track.
 */
public class TrackTest {

    private Track track;

    /**
     * Imposta il fixture di test.
     * Questo metodo viene chiamato prima di ogni metodo di test.
     */
    @BeforeEach
    public void setUp() throws IOException {
        track = new Track(10, 10, "C:\\Users\\giuli\\OneDrive\\Desktop\\configTest.txt");
    }

    /**
     * Verifica che una cella venga correttamente aggiunta alla linea di partenza della traccia.
     */
    @Test
    public void testAddStartLine() {
        Cell startCell = new Cell(1, 1, true, false, true);
        track.addStartLine(startCell);
        assertEquals(2, track.getStartLine().size(), "Dovrebbero esserci 2 celle nella linea di partenza");
        assertEquals(startCell, track.getStartLine().get(1), "La cella aggiunta alla linea di partenza non corrisponde");
    }

    /**
     * Verifica che una cella venga correttamente aggiunta alla linea di arrivo della traccia.
     */
    @Test
    public void testAddFinishLine() {
        Cell finishCell = new Cell(5, 5, false, true, true);
        track.addFinishLine(finishCell);
        assertEquals(2, track.getFinishLine().size(), "Dovrebbe esserci 1 cella nella linea di arrivo");
        assertEquals(finishCell, track.getFinishLine().get(1), "La cella aggiunta alla linea di arrivo non corrisponde");
    }


    /**
     * Test per il metodo getGrid.
     * Verifica che la griglia abbia la dimensione corretta.
     */
    @Test
    public void testGetGrid() {
        List<List<Cell>> grid = track.getGrid();
        assertEquals(10, grid.size(), "La griglia dovrebbe avere 10 righe");
        assertEquals(10, grid.get(0).size(), "La griglia dovrebbe avere 10 colonne per ogni riga");
    }

    /**
     * Test per il metodo getStartLine.
     * Verifica che la linea di partenza contenga la cella corretta.
     */
    @Test
    public void testGetStartLine() {
        List<Cell> startLine = track.getStartLine();
        assertEquals(1, startLine.size(), "La linea di partenza dovrebbe contenere 1 cella");
        assertEquals(0, startLine.get(0).getX(), "la cella di partenza dovrebbe avere come X : 0");
        assertEquals(0, startLine.get(0).getY(), "la cella di partenza dovrebbe avere come Y : 0");
    }

    /**
     * Test per il metodo getFinishLine.
     * Verifica che la linea di arrivo contenga la cella corretta.
     */
    @Test
    public void testGetFinishLine() {
        List<Cell> finishLine = track.getFinishLine();
        assertEquals(1, finishLine.size(), "La linea di arrivo dovrebbe contenere 1 cella");
        assertEquals(4, finishLine.get(0).getX(), "la cella di arrivo dovrebbe avere come X : 4");
        assertEquals(4, finishLine.get(0).getY(), "la cella di arrivo dovrebbe avere come Y : 4");

    }

    /**
     * Test per il metodo getCellAt.
     * Verifica che il metodo restituisca la cella corretta alle coordinate specificate.
     */
    @Test
    public void testGetCellAt() {
        Cell cell = track.getCellAt(0, 0);
        assertNotNull(cell, "La cella alle coordinate (0, 0) non dovrebbe essere null");
        assertEquals(0, cell.getX(), "La coordinata X della cella dovrebbe essere 0");
        assertEquals(0, cell.getY(), "La coordinata Y della cella dovrebbe essere 0");
    }

    /**
     * Test per il metodo addPlayer.
     * Verifica che il giocatore venga aggiunto correttamente alla lista dei giocatori.
     */
    @Test
    public void testAddPlayer() {
        BotPlayer player2 = new BotPlayer("Luca", new Cell(1, 0, true, false, true));
        track.addPlayer(player2);
        assertEquals(2, track.getPlayers().size(), "Dovrebbero esserci 2 giocatori nella lista");
        assertEquals("Luca", track.getPlayers().get(1).getName(), "Il secondo player aggiunto dovrebbe essere Luca");
    }

    @Test
    /**
     * Test per il metodo getPlayers.
     * Verifica che vengano restituiti tutti i giocatori.
     */
    public void testGetPlayers() {
        assertEquals(1, track.getPlayers().size(), "Dovrebbe esserci un solo giocatore definito tramite file");
        assertEquals("Andrea", track.getPlayers().get(0).getName(), "Il nome del giocatore dovrebbe essere Andrea");
    }

    /**
     * Test per il metodo isCellOccupiedByPlayer.
     * Verifica che il metodo restituisca true se la cella è occupata da un giocatore.
     */
    @Test
    public void testIsCellOccupiedByPlayer() {
        assertTrue(track.isCellOccupiedByPlayer(0, 0), "La cella (0, 0) dovrebbe essere occupata dal giocatore");
        assertFalse(track.isCellOccupiedByPlayer(1, 1), "La cella (1, 1) non dovrebbe essere occupata da nessun giocatore");
    }

    /**
     * Test per il metodo getPlayerAtCell.
     * Verifica che il metodo restituisca il giocatore che occupa la cella specificata.
     */
    @Test
    public void testGetPlayerAtCell() {
        Player retrievedPlayer = track.getPlayerAtCell(0, 0);
        assertNotNull(retrievedPlayer, "Dovrebbe esserci un giocatore alla cella (0, 0)");
        assertEquals("Andrea", retrievedPlayer.getName(), "Il giocatore alla cella (0, 0) dovrebbe essere il giocatore Andrea, aggiunto dal file");
    }

    /**
     * Test per il metodo displayTrack.
     * Verifica che il metodo displayTrack non lanci eccezioni.
     * Questo test non verifica l'output della console.
     */
    @Test
    public void testDisplayTrack() {
        assertDoesNotThrow(() -> track.displayTrack(), "Il metodo displayTrack non dovrebbe lanciare eccezioni");
    }
}
