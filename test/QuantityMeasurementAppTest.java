import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-2;

    // ---------------- LengthUnit Tests ----------------

    @Test
    void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getFactor());
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0, LengthUnit.INCHES.getFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARDS.getFactor());
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getFactor(), EPSILON);
    }

    // ---------------- Convert To Base ----------------

    @Test
    void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0, LengthUnit.FEET.toBase(5.0));
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCHES.toBase(12.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0, LengthUnit.YARDS.toBase(1.0));
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0, LengthUnit.CENTIMETERS.toBase(30.48), EPSILON);
    }

    // ---------------- Convert From Base ----------------

    @Test
    void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0, LengthUnit.FEET.fromBase(2.0));
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCHES.fromBase(1.0));
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0, LengthUnit.YARDS.fromBase(3.0));
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48, LengthUnit.CENTIMETERS.fromBase(1.0), EPSILON);
    }

    // ---------------- Quantity Tests ----------------

    @Test
    void testQuantityLengthRefactored_Equality() {
        assertTrue(new Quantity(1.0, LengthUnit.FEET)
                .equals(new Quantity(12.0, LengthUnit.INCHES)));
    }

    @Test
    void testQuantityLengthRefactored_ConvertTo() {
        Quantity result = new Quantity(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testQuantityLengthRefactored_Add() {
        Quantity result = new Quantity(1.0, LengthUnit.FEET)
                .add(new Quantity(12.0, LengthUnit.INCHES), LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testQuantityLengthRefactored_AddWithTargetUnit() {
        Quantity result = new Quantity(1.0, LengthUnit.FEET)
                .add(new Quantity(12.0, LengthUnit.INCHES), LengthUnit.YARDS);

        assertEquals(0.67, result.getValue(), EPSILON);
    }

    // ---------------- Validation ----------------

    @Test
    void testQuantityLengthRefactored_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity(1.0, null));
    }

    @Test
    void testQuantityLengthRefactored_InvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity(Double.NaN, LengthUnit.FEET));
    }

    // ---------------- Round Trip ----------------

    @Test
    void testRoundTripConversion_RefactoredDesign() {
        Quantity original = new Quantity(5.0, LengthUnit.FEET);

        Quantity converted = original.convertTo(LengthUnit.INCHES)
                .convertTo(LengthUnit.FEET);

        assertEquals(original.getValue(), converted.getValue(), EPSILON);
    }

    // ---------------- Immutability ----------------

    @Test
    void testUnitImmutability() {
        assertNotNull(LengthUnit.FEET);
        assertNotNull(LengthUnit.INCHES);
    }

}