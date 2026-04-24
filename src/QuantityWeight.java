import java.util.Objects;

public class QuantityWeight {

    private final double value;
    private final WeightUnit unit;
    private static final double EPSILON = 1e-6;

    public QuantityWeight(double value, WeightUnit unit) {
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    public QuantityWeight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);
        return new QuantityWeight(round(converted), targetUnit);
    }

    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
        if (other == null) throw new IllegalArgumentException("Other cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;
        double result = targetUnit.convertFromBaseUnit(sum);

        return new QuantityWeight(round(result), targetUnit);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityWeight other = (QuantityWeight) obj;

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Objects.hash(Math.round(base / EPSILON));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}