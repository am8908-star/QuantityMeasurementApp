import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.Quantity.convert(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0,
                QuantityMeasurementApp.Quantity.convert(24.0,
                        QuantityMeasurementApp.LengthUnit.INCH,
                        QuantityMeasurementApp.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0,
                QuantityMeasurementApp.Quantity.convert(1.0,
                        QuantityMeasurementApp.LengthUnit.YARD,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testConversion_InchesToYards() {
        assertEquals(2.0,
                QuantityMeasurementApp.Quantity.convert(72.0,
                        QuantityMeasurementApp.LengthUnit.INCH,
                        QuantityMeasurementApp.LengthUnit.YARD),
                EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        assertEquals(1.0,
                QuantityMeasurementApp.Quantity.convert(2.54,
                        QuantityMeasurementApp.LengthUnit.CENTIMETER,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testConversion_FeetToYard() {
        assertEquals(2.0,
                QuantityMeasurementApp.Quantity.convert(6.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.YARD),
                EPSILON);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {
        double original = 5.5;

        double converted = QuantityMeasurementApp.Quantity.convert(
                original,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCH
        );

        double back = QuantityMeasurementApp.Quantity.convert(
                converted,
                QuantityMeasurementApp.LengthUnit.INCH,
                QuantityMeasurementApp.LengthUnit.FEET
        );

        assertEquals(original, back, EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        assertEquals(0.0,
                QuantityMeasurementApp.Quantity.convert(0.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        assertEquals(-12.0,
                QuantityMeasurementApp.Quantity.convert(-1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.Quantity.convert(1.0, null,
                    QuantityMeasurementApp.LengthUnit.FEET);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.Quantity.convert(1.0,
                    QuantityMeasurementApp.LengthUnit.FEET, null);
        });
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.Quantity.convert(Double.NaN,
                    QuantityMeasurementApp.LengthUnit.FEET,
                    QuantityMeasurementApp.LengthUnit.INCH);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.Quantity.convert(Double.POSITIVE_INFINITY,
                    QuantityMeasurementApp.LengthUnit.FEET,
                    QuantityMeasurementApp.LengthUnit.INCH);
        });
    }

    @Test
    void testConversion_PrecisionTolerance() {
        double result = QuantityMeasurementApp.Quantity.convert(1.0,
                QuantityMeasurementApp.LengthUnit.CENTIMETER,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(0.393701, result, EPSILON);
    }
}