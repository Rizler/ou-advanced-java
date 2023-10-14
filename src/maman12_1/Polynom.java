package maman12_1;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Polynom {
    private ArrayList<Term> terms;

    public Polynom(ArrayList<Double> coefficients, ArrayList<Integer> exponents) {
        // Create a polynom from a list of coefficients and exponents.
        if (coefficients.size() != exponents.size()) {
            throw new IllegalArgumentException("The number of coefficients must match the number of exponents!");
        }
        this.terms = new ArrayList<Term>();
        for (int i = 0; i < coefficients.size(); i++) {
            if (coefficients.get(i) == 0) {
                continue;
            }
            this.terms.add(new Term(coefficients.get(i), exponents.get(i)));
        }
        this.terms.sort((a, b) -> b.getExponent() - a.getExponent());
    }

    public Polynom(ArrayList<Term> terms) {
        // Create a polynom from a list of terms.
        this.terms = terms;
    }

    public Polynom plus(Polynom other) {
        // Add two polynoms.
        int i = 0;
        int j = 0;
        ArrayList<Term> newTerms = new ArrayList<Term>();
        while (i < this.terms.size() || j < other.terms.size()) {
            Term term1 = null;
            Term term2 = null;
            if (i < this.terms.size()) {
                term1 = this.terms.get(i);
            }
            if (j < other.terms.size()) {
                term2 = other.terms.get(j);
            }

            if (term2 == null) {
                newTerms.add(term1);
                i++;
                continue;
            }
            if (term1 == null) {
                newTerms.add(term2);
                j++;
                continue;
            }

            if (term1.getExponent() > term2.getExponent()) {
                newTerms.add(term1);
                i++;
            } else if (term1.getExponent() < term2.getExponent()) {
                newTerms.add(term2);
                j++;
            } else {
                // Same exponent.
                Term newTerm = term1.plus(term2);
                if (newTerm != null) {
                    newTerms.add(newTerm);
                }
                i++;
                j++;
            }
        }
        return new Polynom(newTerms);
    }

    public Polynom minus(Polynom other) {
        // Subtract two polynoms.
        ArrayList<Term> multipliedTerms = new ArrayList<Term>();
        for (Term term : other.terms) {
            multipliedTerms.add(new Term(-term.getCoefficient(), term.getExponent()));
        }
        return this.plus(new Polynom(multipliedTerms));
    }

    public Polynom derivative() {
        // Calculate the derivative of the polynom.
        ArrayList<Term> derivativeTerms = new ArrayList<Term>();
        for (Term term : this.terms) {
            Term derivative = term.derivative();
            if (derivative != null) {
                derivativeTerms.add(derivative);
            }
        }
        return new Polynom(derivativeTerms);
    }

    @Override
    public boolean equals(Object obj) {
        // Check if two polynoms are equal.
        if (!(obj instanceof Polynom)) {
            return false;
        }
        Polynom other = (Polynom) obj;
        if (this.terms.size() != other.terms.size()) {
            return false;
        }
        for (int i = 0; i < this.terms.size(); i++) {
            if (!this.terms.get(i).equals(other.terms.get(i))) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        // Return a string representation of the polynom.
        if (this.terms.isEmpty()) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(this.terms.get(0));
        for (int i = 1; i < this.terms.size(); i++) {
            if (this.terms.get(i).getCoefficient() >= 0) {
                builder.append(" + ");
            } else {
                builder.append(" - ");
            }
            builder.append(this.terms.get(i).toStringNoSign());
        }
        return builder.toString();
    }
}

