import java.util.Objects;

public class Quantity {

    private final double value;
    private final LengthUnit unit;
    private static final double EPSILON = 1e-2;

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

    public Quantity convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");

        double base = unit.toBase(value);
        double converted = targetUnit.fromBase(base);

        return new Quantity(round(converted), targetUnit);
    }

    public Quantity add(Quantity other) {
        return add(other, this.unit);
    }

    public Quantity add(Quantity other, LengthUnit targetUnit) {
        if (other == null) throw new IllegalArgumentException("Other quantity cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");

        double base1 = this.unit.toBase(this.value);
        double base2 = other.unit.toBase(other.value);

        double sumBase = base1 + base2;
        double result = targetUnit.fromBase(sumBase);

        return new Quantity(round(result), targetUnit);
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity)) return false;

        Quantity other = (Quantity) o;

        double base1 = this.unit.toBase(this.value);
        double base2 = other.unit.toBase(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        double base = unit.toBase(value);
        return Objects.hash(round(base));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}