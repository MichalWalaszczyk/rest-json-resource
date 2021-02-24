package pl.walaszczyk.restjsonresource.utils;

import pl.walaszczyk.restjsonresource.model.cargo.Baggage;
import pl.walaszczyk.restjsonresource.model.cargo.WeightUnit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class BaggageUtil {

    private static final BigDecimal poundToKilogram = BigDecimal.valueOf(0.45359237);

    public static BigDecimal getTotalWeight(List<Baggage> baggageList) {
        BigDecimal totalWeightInKg = BigDecimal.ZERO;
        for (Baggage baggage : baggageList) {
            totalWeightInKg = totalWeightInKg.add(getSingleBaggageTotalWeightInKg(baggage));
        }
        return totalWeightInKg;
    }

    private static BigDecimal getSingleBaggageTotalWeightInKg(Baggage baggage) {
        if (WeightUnit.kg.equals(baggage.getWeightUnit())) {
            return BigDecimal.valueOf(baggage.getWeight()).multiply(BigDecimal.valueOf(baggage.getPieces()));
        } else if (WeightUnit.lb.equals(baggage.getWeightUnit())) {
            return BigDecimal.valueOf(baggage.getWeight()).multiply(BigDecimal.valueOf(baggage.getPieces()))
                    .multiply(poundToKilogram).setScale(2, RoundingMode.CEILING);
        } else {
            throw new IllegalArgumentException("BaggageUtil - baggage has got incompatible weight unit");
        }
    }

    public static int getTotalPiecesOfBaggage(List<Baggage> baggageList) {
        int totalPiecesOfBaggage = 0;
        for (Baggage baggage : baggageList) {
            totalPiecesOfBaggage += baggage.getPieces();
        }
        return totalPiecesOfBaggage;
    }
}
