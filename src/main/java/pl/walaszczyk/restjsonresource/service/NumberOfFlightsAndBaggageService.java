package pl.walaszczyk.restjsonresource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.walaszczyk.restjsonresource.model.cargo.Cargo;
import pl.walaszczyk.restjsonresource.model.flight.AirportIATACode;
import pl.walaszczyk.restjsonresource.model.flight.Flight;
import pl.walaszczyk.restjsonresource.model.flight.FlightRepository;
import pl.walaszczyk.restjsonresource.rest.exceptions.ElementNotFoundException;
import pl.walaszczyk.restjsonresource.rest.flight.response.NumberOfFlightsAndBaggageResponseDTO;
import pl.walaszczyk.restjsonresource.utils.BaggageUtil;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NumberOfFlightsAndBaggageService {

    private final FlightRepository flightRepository;
    private final CargoService cargoService;

    @Autowired
    public NumberOfFlightsAndBaggageService(FlightRepository flightRepository, CargoService cargoService) {
        this.flightRepository = flightRepository;
        this.cargoService = cargoService;
    }

    public NumberOfFlightsAndBaggageResponseDTO getNumberOfFlightsAndBaggageService(AirportIATACode airportIATACode, Date departureDate) {
        NumberOfFlightsAndBaggageResponseDTO responseDTO = new NumberOfFlightsAndBaggageResponseDTO();

        List<Flight> flightsDepartingFromAirport = flightRepository.findAllByDepartureAirportIATACodeAndDepartureDate(airportIATACode, departureDate);
        List<Flight> flightsArrivingToAirport = flightRepository.findAllByArrivalAirportIATACodeAndDepartureDate(airportIATACode, departureDate);
        if (CollectionUtils.isEmpty(flightsDepartingFromAirport) && CollectionUtils.isEmpty(flightsArrivingToAirport))
            throw new ElementNotFoundException();

        responseDTO.setFlightsDepartingFromTheAirport(flightsDepartingFromAirport.size());
        responseDTO.setNumberOfBaggagePiecesDepartingFromTheAirport(getNumberOfBaggage(flightsDepartingFromAirport));

        responseDTO.setFlightsArrivingToTheAirport(flightsArrivingToAirport.size());
        responseDTO.setNumberOfBaggagePiecesArrivingToTheAirport(getNumberOfBaggage(flightsArrivingToAirport));

        return responseDTO;
    }

    private Integer getNumberOfBaggage(List<Flight> flights) {
        if (CollectionUtils.isEmpty(flights))
            return null;

        List<Cargo> cargoList = cargoService.findAllByFlightIdIn(flights.stream()
                .mapToInt(Flight::getFlightId)
                .boxed()
                .collect(Collectors.toList())
        );
        return cargoList.stream()
                .mapToInt(cargo -> BaggageUtil.getTotalPiecesOfBaggage(cargo.getBaggage()))
                .sum();
    }
}
