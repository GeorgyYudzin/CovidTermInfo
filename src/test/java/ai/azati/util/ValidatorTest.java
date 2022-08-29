package ai.azati.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void validateRightInput() {
        assertTrue(Validator.validate("Italy"));
    }

    @Test
    void validateWrongCountry() {
        assertFalse(Validator.validate("Byzantium"));
    }

    @Test
    void validateWrongSymbols() {
        assertFalse(Validator.validate("9a80sd7fq2;;'p[o"));
    }

}