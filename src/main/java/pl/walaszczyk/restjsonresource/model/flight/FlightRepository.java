package pl.walaszczyk.restjsonresource.model.flight;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FlightRepository {

    Optional<Flight> findFlightByFlightNumberAndDepartureDate(Integer flightNumber, Date departureDate);

    List<Flight> findAllByDepartureAirportIATACodeAndDepartureDate(AirportIATACode departureAirportIATACode, Date departureDate);
    List<Flight> findAllByArrivalAirportIATACodeAndDepartureDate(AirportIATACode arrivalAirportIATACode, Date departureDate);
}
