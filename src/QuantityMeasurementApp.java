public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.393701 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double valueInFeet) {
            return valueInFeet / toFeetFactor;
        }
    }

    public static class Quantity {

        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 1e-4;

        public Quantity(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        private double toBaseUnit() {
            return unit.toFeet(value);
        }

        public Quantity convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
            double base = toBaseUnit();
            double converted = targetUnit.fromFeet(base);
            return new Quantity(converted, targetUnit);
        }

        // ✅ UC6: add → result in first operand unit
        public Quantity add(Quantity other) {
            if (other == null) throw new IllegalArgumentException("Other quantity cannot be null");

            double sumFeet = this.toBaseUnit() + other.toBaseUnit();
            double result = this.unit.fromFeet(sumFeet);

            return new Quantity(result, this.unit);
        }

        // ✅ UC7: add with target unit
        public Quantity add(Quantity other, LengthUnit targetUnit) {
            if (other == null) throw new IllegalArgumentException("Other quantity cannot be null");
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");

            double sumFeet = this.toBaseUnit() + other.toBaseUnit();
            double result = targetUnit.fromFeet(sumFeet);

            return new Quantity(result, targetUnit);
        }

        // static version (optional)
        public static Quantity add(Quantity q1, Quantity q2, LengthUnit targetUnit) {
            if (q1 == null || q2 == null) throw new IllegalArgumentException("Null operand");
            return q1.add(q2, targetUnit);
        }

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

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }
}