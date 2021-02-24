package pl.walaszczyk.restjsonresource.model.flight;

import lombok.Data;

import java.util.Date;

@Data
public class Flight {
    private Integer flightId;
    private Integer flightNumber;
    private AirportIATACode departureAirportIATACode;
    private AirportIATACode arrivalAirportIATACode;
    private Date departureDate;
}
