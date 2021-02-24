package pl.walaszczyk.restjsonresource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.walaszczyk.restjsonresource.model.cargo.Cargo;
import pl.walaszczyk.restjsonresource.model.flight.Flight;
import pl.walaszczyk.restjsonresource.model.flight.FlightRepository;
import pl.walaszczyk.restjsonresource.rest.exceptions.ElementNotFoundException;
import pl.walaszczyk.restjsonresource.rest.flight.response.FlightWeightResponseDTO;
import pl.walaszczyk.restjsonresource.utils.BaggageUtil;

import java.util.Date;
import java.util.Optional;

@Service
public class FlightWeightService {

    private final FlightRepository flightRepository;
    private final CargoService cargoService;

    @Autowired
    public FlightWeightService(FlightRepository flightRepository, CargoService cargoService) {
        this.flightRepository = flightRepository;
        this.cargoService = cargoService;
    }

    public FlightWeightResponseDTO getFlightWeight(Integer flightNumber, Date departureDate) {
        Optional<Flight> flightOptional = flightRepository.findFlightByFlightNumberAndDepartureDate(flightNumber, departureDate);
        if (flightOptional.isEmpty())
            throw new ElementNotFoundException();

        FlightWeightResponseDTO responseDTO = new FlightWeightResponseDTO();
        Optional<Cargo> cargoOptional = cargoService.findCargoByFlightId(flightOptional.get().getFlightId());
        if (cargoOptional.isPresent()) {
            responseDTO.setCargoWeight(BaggageUtil.getTotalWeight(cargoOptional.get().getCargo()));
            responseDTO.setBaggageWeight(BaggageUtil.getTotalWeight(cargoOptional.get().getBaggage()));
            responseDTO.setTotalWeight(responseDTO.getCargoWeight().add(responseDTO.getBaggageWeight()));
        }
        return responseDTO;
    }
}
