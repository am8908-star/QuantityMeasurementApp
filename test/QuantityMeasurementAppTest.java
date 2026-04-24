import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-4;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = q1.add(q2, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = q1.add(q2, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(24.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = q1.add(q2, QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(0.6667, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.INCH);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = q1.add(q2, QuantityMeasurementApp.LengthUnit.CENTIMETER);

        assertEquals(5.08, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NullTargetUnit() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertThrows(IllegalArgumentException.class, () -> q1.add(q2, null));
    }

    @Test
    void testAddition_Commutativity() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var r1 = q1.add(q2, QuantityMeasurementApp.LengthUnit.YARD);
        var r2 = q2.add(q1, QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }
}