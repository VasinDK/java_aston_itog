package five.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {
    @ParameterizedTest
    @CsvSource({
            "5, 5, 'должно быть 5'",
            "15, 15, 'должно быть 15'",
            "1, 1, 'должно быть 1'",
    })
    void testPositiveParsePositiveInt(int a, String b, String c) {
        assertEquals(a, InputValidator.parsePositiveInt(b, ""), c);
    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "-1",
            "-101",
            "абв",
    })
    void testNegativeParsePositiveInt(String a) {
        assertThrows(IllegalArgumentException.class, () -> {
            InputValidator.parsePositiveInt(a, "");
        });
    }
}