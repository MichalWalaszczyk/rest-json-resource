package pl.walaszczyk.restjsonresource.rest.flight;

import pl.walaszczyk.restjsonresource.model.flight.AirportIATACode;
import pl.walaszczyk.restjsonresource.rest.flight.response.FlightWeightResponseDTO;
import pl.walaszczyk.restjsonresource.rest.flight.response.NumberOfFlightsAndBaggageResponseDTO;

import java.util.Date;

public interface FlightApi {

    FlightWeightResponseDTO getFlightWeight(Integer flightNumber, Date departureDate);

    NumberOfFlightsAndBaggageResponseDTO getNumberOfFlightsAndBaggageService(AirportIATACode airportIATACode, Date departureDate);
}
