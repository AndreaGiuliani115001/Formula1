import static org.junit.jupiter.api.Assertions.*;

import it.unicam.cs.pa2024.formula1.track.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe di test per la classe Cell.
 */
public class CellTest {

    private Cell cell;

    /**
     * Imposta il fixture di test.
     * Questo metodo viene chiamato prima di ogni metodo di test.
     */
    @BeforeEach
    public void setUp() {
        cell = new Cell(5, 10, true, false, true);
    }

    /**
     * Test per il metodo getX.
     * Verifica che il metodo restituisca la coordinata X corretta.
     */
    @Test
    public void testGetX() {
        assertEquals(5, cell.getX(), "La coordinata X dovrebbe essere 5");
    }

    /**
     * Test per il metodo getY.
     * Verifica che il metodo restituisca la coordinata Y corretta.
     */
    @Test
    public void testGetY() {
        assertEquals(10, cell.getY(), "La coordinata Y dovrebbe essere 10");
    }

    /**
     * Test per il metodo isStartCell.
     * Verifica che il metodo restituisca il valore corretto per isStartCell.
     */
    @Test
    public void testIsStartCell() {
        assertTrue(cell.isStartCell(), "La cella dovrebbe essere una cella di partenza");
    }

    /**
     * Test per il metodo setStartCell.
     * Verifica che il metodo imposti correttamente il valore di isStartCell.
     */
    @Test
    public void testSetStartCell() {
        cell.setStartCell(false);
        assertFalse(cell.isStartCell(), "La cella non dovrebbe essere una cella di partenza");
    }

    /**
     * Test per il metodo isFinishCell.
     * Verifica che il metodo restituisca il valore corretto per isFinishCell.
     */
    @Test
    public void testIsFinishCell() {
        assertFalse(cell.isFinishCell(), "La cella non dovrebbe essere una cella di arrivo");
    }

    /**
     * Test per il metodo setFinishCell.
     * Verifica che il metodo imposti correttamente il valore di isFinishCell.
     */
    @Test
    public void testSetFinishCell() {
        cell.setFinishCell(true);
        assertTrue(cell.isFinishCell(), "La cella dovrebbe essere una cella di arrivo");
    }

    /**
     * Test per il metodo isTrack.
     * Verifica che il metodo restituisca il valore corretto per isTrack.
     */
    @Test
    public void testIsTrack() {
        assertTrue(cell.isTrack(), "La cella dovrebbe essere parte del tracciato");
    }

    /**
     * Test per il metodo setTrack.
     * Verifica che il metodo imposti correttamente il valore di isTrack.
     */
    @Test
    public void testSetTrack() {
        cell.setTrack(false);
        assertFalse(cell.isTrack(), "La cella non dovrebbe essere parte del tracciato");
    }
}


