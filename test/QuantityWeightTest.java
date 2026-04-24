import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityWeightTest {

    private static final double EPSILON = 1e-2;

    @Test
    void testEquality_KgToGram() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1000.0, WeightUnit.GRAM)));
    }

    @Test
    void testConversion_KgToGram() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_PoundToKg() {
        QuantityWeight result = new QuantityWeight(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_KgPlusGram() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_WithTargetUnit() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    void testZeroAddition() {
        QuantityWeight result = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(0.0, WeightUnit.GRAM));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testNegativeAddition() {
        QuantityWeight result = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(-2000.0, WeightUnit.GRAM));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(1.0, null));
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM));
    }
}