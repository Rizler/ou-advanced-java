package maman12_1;

public class Term {
    private double coefficient;
    private int exponent;

    public Term(double coefficient, int exponent) {
        // Create a term from a coefficient and an exponent.
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public Term plus(Term other) {
        // Add two terms.
        if (this.exponent != other.exponent) {
            throw new IllegalArgumentException("Cannot add terms with different exponents!");
        }
        double newCoefficient = this.coefficient + other.coefficient;
        if (newCoefficient == 0) {
            return null;
        }
        return new Term(newCoefficient, this.exponent);
    }

    public Term derivative() {
        // Calculate the derivative of a term.
        if (this.exponent == 0) {
            return null;
        }
        return new Term(this.coefficient * this.exponent, this.exponent - 1);
    }

    @Override
    public boolean equals(Object obj) {
        // Check if two terms are equal.
        if (!(obj instanceof Term)) {
            return false;
        }
        Term other = (Term) obj;
        return this.coefficient == other.coefficient && this.exponent == other.exponent;
    }

    public String toString() {
        // Return a string representation of the term with its sign.
        if (this.coefficient < 0) {
            return "-" + toStringNoSign();
        }
        return toStringNoSign();
    }

    public String toStringNoSign() {
        // Return a string representation of the term without its sign.
        double absCoefficient = Math.abs(this.coefficient);
        if (this.exponent == 0) {
            return String.format("%.1f", absCoefficient);
        } else if (this.exponent == 1) {
            return String.format("%.1fx", absCoefficient);
        } else {
            return String.format("%.1fx^%d", absCoefficient, this.exponent);
        }
    }
}
