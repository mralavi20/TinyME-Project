package ir.ramtung.tinyme.domain.service;

import ir.ramtung.tinyme.domain.entity.Order;
import ir.ramtung.tinyme.domain.entity.Trade;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class MatchResult {
    private final MatchingOutcome outcome;
    private final Order remainder;
    private final LinkedList<Trade> trades;

    public static MatchResult executed(Order remainder, List<Trade> trades) {
        return new MatchResult(MatchingOutcome.OK, remainder, new LinkedList<>(trades));
    }

    public static MatchResult notEnoughCredit() {
        return new MatchResult(MatchingOutcome.NOT_ENOUGH_CREDIT, null, new LinkedList<>());
    }

    public static MatchResult notEnoughPositions() {
        return new MatchResult(MatchingOutcome.NOT_ENOUGH_POSITIONS, null, new LinkedList<>());
    }

    public static MatchResult minimumQuantityNotSatisfied() {
        return new MatchResult(MatchingOutcome.MINIMUM_QUANTITY_NOT_SATISFIED, null, new LinkedList<>());
    }

    public MatchResult(MatchingOutcome outcome, Order remainder, LinkedList<Trade> trades) {
        this.outcome = outcome;
        this.remainder = remainder;
        this.trades = trades;
    }

    public MatchResult(MatchingOutcome outcome, Order remainder) {
        this(outcome, remainder, new LinkedList<>());
    }

    public MatchingOutcome outcome() {
        return outcome;
    }

    public Order remainder() {
        return remainder;
    }

    public LinkedList<Trade> trades() {
        return trades;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (MatchResult) obj;
        return Objects.equals(this.remainder, that.remainder) &&
                Objects.equals(this.trades, that.trades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remainder, trades);
    }

    @Override
    public String toString() {
        return "MatchResult[" +
                "remainder=" + remainder + ", " +
                "trades=" + trades + ']';
    }


}
