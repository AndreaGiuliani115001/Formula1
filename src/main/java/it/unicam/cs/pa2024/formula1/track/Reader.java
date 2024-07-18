package it.unicam.cs.pa2024.formula1.track;

import java.io.IOException;

/**
 * Interfaccia per gestire la configurazione del tracciato e dei giocatori.
 */
public interface Reader {

    /**
     * Carica il tracciato e i giocatori dal file di configurazione specificato.
     *
     * @param track Oggetto Track su cui applicare la configurazione.
     * @throws IOException Se si verifica un errore durante la lettura del file.
     */
    void loadTrack(Track track) throws IOException;

}

