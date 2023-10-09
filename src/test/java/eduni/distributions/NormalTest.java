package eduni.distributions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalTest {

    // Testataan toimiiko antaako Normal-jakauma arvoja, jotka ovat realistisa simulaatiossa
    @Test
    void sample() {
        Normal normal = new Normal(2, 1);
        double sample = normal.sample();
        assertTrue(sample >= 1 && sample <= 3);
    }
}