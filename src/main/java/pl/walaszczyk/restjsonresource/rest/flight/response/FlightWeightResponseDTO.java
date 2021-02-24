package pl.walaszczyk.restjsonresource.rest.flight.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FlightWeightResponseDTO {
    private BigDecimal cargoWeight;
    private BigDecimal baggageWeight;
    private BigDecimal totalWeight;
}
