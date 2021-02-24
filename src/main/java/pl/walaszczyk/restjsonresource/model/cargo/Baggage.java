package pl.walaszczyk.restjsonresource.model.cargo;

import lombok.Data;

@Data
public class Baggage {
    private Integer id;
    private Integer weight;
    private WeightUnit weightUnit;
    private Integer pieces;
}
