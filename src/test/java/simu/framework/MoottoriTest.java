package simu.framework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoottoriTest {
    private long viive;

    void setViive(long aika) {
        viive = aika;
        assertEquals(viive, aika);
    }

    // Tässä testataan, että viive muuttuja ottaa syötetyn arvon.
    @Test
    void getViive() {
        long uusiaika = 800;
        setViive(uusiaika);
        assertEquals(uusiaika, viive);
    }
}