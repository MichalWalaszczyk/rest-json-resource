package pl.walaszczyk.restjsonresource;

import org.junit.jupiter.api.Test;
import pl.walaszczyk.restjsonresource.model.cargo.Baggage;
import pl.walaszczyk.restjsonresource.model.cargo.WeightUnit;
import pl.walaszczyk.restjsonresource.utils.BaggageUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaggageUtilTest {

    private static final BigDecimal poundToKilogram = BigDecimal.valueOf(0.45359237);

    @Test
    public void testGetTotalWeightFromKg() {
        List<Baggage> baggageList = new ArrayList<>();
        Baggage baggage = new Baggage();
        baggage.setWeight(100);
        baggage.setPieces(2);
        baggage.setWeightUnit(WeightUnit.kg);
        baggageList.add(baggage);
        assertEquals(BigDecimal.valueOf(200), BaggageUtil.getTotalWeight(baggageList));
    }

    @Test
    public void testGetTotalWeightFromPounds() {
        List<Baggage> baggageList = new ArrayList<>();
        Baggage baggage = new Baggage();
        baggage.setWeight(100);
        baggage.setPieces(2);
        baggage.setWeightUnit(WeightUnit.lb);
        baggageList.add(baggage);
        BigDecimal expectedValue = BigDecimal.valueOf(baggage.getWeight()).multiply(BigDecimal.valueOf(baggage.getPieces()))
                .multiply(poundToKilogram).setScale(2, RoundingMode.CEILING);
        assertEquals(expectedValue, BaggageUtil.getTotalWeight(baggageList));
    }
}
