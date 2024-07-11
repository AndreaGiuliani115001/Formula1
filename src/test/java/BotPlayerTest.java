import it.unicam.cs.pa2024.formula1.track.*;
import it.unicam.cs.pa2024.formula1.player.BotPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BotPlayerTest {

    private Track track;
    private BotPlayer botPlayer;
    private Cell startCell;
    private Cell nextCell;

    @BeforeEach
    public void setup() {

        // Mock the Track class
        track = mock(Track.class);

        // Create startCell and set it as start cell
        startCell = new Cell(0, 0, true, false, true);

        // Mock the getCellAt method
        when(track.getCellAt(0, 0)).thenReturn(startCell);

        // Create BotPlayer at startCell
        botPlayer = new BotPlayer("Bot1", startCell);

        // Create a neighboring cell
        nextCell = new Cell(1, 0, false, false, true);
        when(track.getCellAt(1, 0)).thenReturn(nextCell);

        // Mock the isCellOccupiedByPlayer method
        when(track.isCellOccupiedByPlayer(anyInt(), anyInt())).thenReturn(false);
    }

    @Test
    public void testInitialMove() {
        botPlayer.makeMove(track);
        assertNotNull(botPlayer.getCurrentPosition());
        assertNotEquals(startCell, botPlayer.getCurrentPosition());
    }

    @Test
    public void testSubsequentMove() {


        // Perform initial move
        botPlayer.makeMove(track);

        // Setup for subsequent move
        Cell currentPosition = botPlayer.getCurrentPosition();
        Cell nextMoveCell = new Cell(currentPosition.getX() + 1, currentPosition.getY() + 1, false, false, true);
        when(track.getCellAt(currentPosition.getX() + 1, currentPosition.getY() + 1)).thenReturn(nextMoveCell);

        botPlayer.makeMove(track);
        assertNotEquals(currentPosition, botPlayer.getCurrentPosition());
    }

    @Test
    public void testMoveIntoOccupiedCell() {
        // Mock occupied cell
        when(track.isCellOccupiedByPlayer(nextCell.getX(), nextCell.getY())).thenReturn(true);

        botPlayer.makeMove(track);
        assertNotEquals(nextCell, botPlayer.getCurrentPosition());
    }
}

