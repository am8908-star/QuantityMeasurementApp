import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testEquality_YardToYard_SameValue() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.YARD);
        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        var yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var feet = new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertTrue(yard.equals(feet));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        var feet = new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        var yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        assertTrue(feet.equals(yard));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        var yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var inch = new QuantityMeasurementApp.Quantity(36.0, QuantityMeasurementApp.LengthUnit.INCH);
        assertTrue(yard.equals(inch));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        var inch = new QuantityMeasurementApp.Quantity(36.0, QuantityMeasurementApp.LengthUnit.INCH);
        var yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        assertTrue(inch.equals(yard));
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        var yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var feet = new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertFalse(yard.equals(feet));
    }

    @Test
    void testEquality_CentimeterToInch_EquivalentValue() {
        var cm = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETER);
        var inch = new QuantityMeasurementApp.Quantity(0.393701, QuantityMeasurementApp.LengthUnit.INCH);
        assertTrue(cm.equals(inch));
    }

    @Test
    void testEquality_CentimeterToFeet_NonEquivalentValue() {
        var cm = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETER);
        var feet = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertFalse(cm.equals(feet));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        var yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var feet = new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        var inch = new QuantityMeasurementApp.Quantity(36.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityMeasurementApp.Quantity(1.0, null);
        });
    }

    @Test
    void testEquality_SameReference() {
        var q = new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.YARD);
        assertTrue(q.equals(q));
    }

    @Test
    void testEquality_NullComparison() {
        var q = new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.YARD);
        assertFalse(q.equals(null));
    }
}