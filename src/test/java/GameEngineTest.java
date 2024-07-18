import it.unicam.cs.pa2024.formula1.engine.GameEngine;
import it.unicam.cs.pa2024.formula1.track.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitari per verificare il corretto funzionamento del GameEngine.
 */
public class GameEngineTest {

    private GameEngine gameEngine;

    /**
     * Inizializza il tracciato e il GameEngine prima di ogni test.
     */
    @BeforeEach
    void setUp() throws IOException {

        // Inizializzazione del tracciato
        Track track = new Track(10, 10, "config.txt");

        // Inizializzazione del GameEngine con il tracciato
        gameEngine = new GameEngine(track);
    }

    /**
     * Verifica che la gara possa essere avviata correttamente e terminata quando finisce.
     */
    @Test
    void testStartAndEndRace() {

        // Verifica che la gara inizi correttamente
        gameEngine.startRace();

        // Assicura che la gara sia terminata
        assertFalse(gameEngine.isRaceInProgress());
    }
}
