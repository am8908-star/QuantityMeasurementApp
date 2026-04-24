public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    public static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return unit.toFeet(value);
        }

        private static final double EPSILON = 0.0001;

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Quantity other = (Quantity) obj;
            return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBaseUnit());
        }
    }

    public static void main(String[] args) {
        Quantity yardFeet = new Quantity(1.0, LengthUnit.YARD);
        Quantity feet = new Quantity(3.0, LengthUnit.FEET);

        Quantity yardInch = new Quantity(1.0, LengthUnit.YARD);
        Quantity inches = new Quantity(36.0, LengthUnit.INCH);

        Quantity cmInch = new Quantity(1.0, LengthUnit.CENTIMETER);
        Quantity inchVal = new Quantity(0.393701, LengthUnit.INCH);

        System.out.println("Yard vs Feet (" + yardFeet.equals(feet) + ")");
        System.out.println("Yard vs Inches (" + yardInch.equals(inches) + ")");
        System.out.println("CM vs Inch (" + cmInch.equals(inchVal) + ")");
    }
}