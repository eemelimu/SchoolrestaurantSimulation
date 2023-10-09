package view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisualisointiTest {

    // Testataan toimiiko kuvan hakeminen oikein
    @Test
    void testaaImagePath() {
        double value = 0.1;
        String path = "/images/palkit/palkki" + value + ".png";
        assertEquals(path, "/images/palkit/palkki0.1.png", "Path ei ole oikein");
    }
}