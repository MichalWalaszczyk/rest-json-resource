package pl.walaszczyk.restjsonresource.rest.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.walaszczyk.restjsonresource.model.flight.AirportIATACode;
import pl.walaszczyk.restjsonresource.rest.flight.response.FlightWeightResponseDTO;
import pl.walaszczyk.restjsonresource.rest.flight.response.NumberOfFlightsAndBaggageResponseDTO;
import pl.walaszczyk.restjsonresource.service.FlightWeightService;
import pl.walaszczyk.restjsonresource.service.NumberOfFlightsAndBaggageService;

import java.util.Date;

@RestController
public class FlightRestApi implements FlightApi {

    private final FlightWeightService flightWeightService;
    private final NumberOfFlightsAndBaggageService numberOfFlightsAndBaggageService;

    @Autowired
    public FlightRestApi(FlightWeightService flightWeightService, NumberOfFlightsAndBaggageService numberOfFlightsAndBaggageService) {
        this.flightWeightService = flightWeightService;
        this.numberOfFlightsAndBaggageService = numberOfFlightsAndBaggageService;
    }

    @Override
    @GetMapping("/flights/{flightNumber}/{departureDate}")
    public FlightWeightResponseDTO getFlightWeight(@PathVariable("flightNumber") Integer flightNumber,
                                                   @PathVariable("departureDate")
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departureDate) {
        return flightWeightService.getFlightWeight(flightNumber, departureDate);
    }

    @Override
    @GetMapping("/airports/{airportIATACode}/{departureDate}")
    public NumberOfFlightsAndBaggageResponseDTO getNumberOfFlightsAndBaggageService(
            @PathVariable("airportIATACode") AirportIATACode airportIATACode,
            @PathVariable("departureDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departureDate) {
        return numberOfFlightsAndBaggageService.getNumberOfFlightsAndBaggageService(airportIATACode, departureDate);
    }
}
