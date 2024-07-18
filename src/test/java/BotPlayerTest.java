import it.unicam.cs.pa2024.formula1.engine.GameEngine;
import it.unicam.cs.pa2024.formula1.player.Player;
import it.unicam.cs.pa2024.formula1.track.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitari per verificare il corretto funzionamento della classe BotPlayer.
 */
public class BotPlayerTest {

    private Track track;
    private Player andreaBot;
    private Player lucaBot;

    /**
     * Inizializza il tracciato e il BotPlayer prima di ogni test.
     */
    @BeforeEach
    void setUp() throws IOException {

        // Inizializzazione del tracciato
        track = new Track(10, 10, "config.txt");
        andreaBot = track.getPlayers().get(0);
        lucaBot = track.getPlayers().get(1);

    }

    /**
     * Verifica che il nome del BotPlayer sia corretto.
     */
    @Test
    void testGetName() {
        assertEquals("Andrea", andreaBot.getName());
        assertEquals("Luca", lucaBot.getName());
    }

    /**
     * Verifica che la posizione iniziale del BotPlayer sia corretta.
     */
    @Test
    void testInitialPosition() {
        assertTrue(andreaBot.getCurrentPosition().isStartCell());
        assertTrue(lucaBot.getCurrentPosition().isStartCell());
    }

    /**
     * Verifica i movimenti dei Player:
     * - La nuova posizione deve far parte della griglia.
     * - La nuova posizione deve essere sempre diversa da quella vecchia.
     * - La distanza tra la linea di fine e la nuova posizione deve essere
     *   sempre minore rispetto che da quella vecchia.
     */
    @Test
    void testMakeMove() {

        GameEngine engine = new GameEngine(track);

        Cell positionA = andreaBot.getCurrentPosition();
        Cell positionL = lucaBot.getCurrentPosition();


        while (engine.isRaceInProgress()) {

            andreaBot.makeMove(track);
            lucaBot.makeMove(track);

            Cell newPositionA = andreaBot.getCurrentPosition();
            Cell newPositionL = lucaBot.getCurrentPosition();

            assertNotNull(newPositionA, "la nuova posizione di Andrea non dovrebbe mai essere null");
            assertNotNull(newPositionL, "la nuova posizione di Luca non dovrebbe mai essere null");

            assertNotEquals(positionA, newPositionA, "La nuova posizione di Andrea dovrebbe essere sempre diversa da quella vecchia");
            assertNotEquals(positionL, newPositionL, "La nuova posizione di Luca dovrebbe essere sempre diversa da quella vecchia");


            for (Cell finishCell : track.getFinishLine()) {
                assertTrue(track.calculateDistance(newPositionA, finishCell) < track.calculateDistance(positionA, finishCell), "La distanza dalla fine della nuova posizione di Andrea dovrebbe sempre essere minore rispetto che da quella vecchia");
                assertTrue(track.calculateDistance(newPositionL, finishCell) < track.calculateDistance(positionL, finishCell), "La distanza dalla fine della nuova posizione di Luca dovrebbe sempre essere minore rispetto che da quella vecchia");

            }

            positionA = newPositionA;
            positionL = newPositionL;

        }

    }

}

