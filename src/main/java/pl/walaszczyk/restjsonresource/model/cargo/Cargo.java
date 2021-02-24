package pl.walaszczyk.restjsonresource.model.cargo;

import lombok.Data;

import java.util.List;

@Data
public class Cargo {
    private Integer flightId;
    private List<Baggage> baggage;
    private List<Baggage> cargo;
}
