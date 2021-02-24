package pl.walaszczyk.restjsonresource.model.flight;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Repository;
import pl.walaszczyk.restjsonresource.rest.exceptions.GetDataFromJsonException;
import pl.walaszczyk.restjsonresource.utils.JsonToObjectMapper;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Log4j2
public class FlightRepositoryJson implements FlightRepository {

    @Override
    public Optional<Flight> findFlightByFlightNumberAndDepartureDate(Integer flightNumber, Date departureDate) {
        try {
            List<Flight> flights = getObjectsFromJsonFile();
            return flights.stream()
                    .filter(flight -> flightNumber.equals(flight.getFlightNumber()) &&
                            DateUtils.isSameDay(departureDate, flight.getDepartureDate()))
                    .findAny();
        } catch (IOException e) {
            log.error("FlightRepositoryJson - there is no json file in pointed localization", e);
            throw new GetDataFromJsonException(e.getMessage());
        }
    }

    private List<Flight> getObjectsFromJsonFile() throws IOException {
        return JsonToObjectMapper.getObjectsFromJsonFile("src/main/resources/data/flight.json", Flight.class);
    }

    @Override
    public List<Flight> findAllByDepartureAirportIATACodeAndDepartureDate(AirportIATACode departureAirportIATACode, Date departureDate) {
        try {
            List<Flight> flights = getObjectsFromJsonFile();
            return flights.stream()
                    .filter(flight -> departureAirportIATACode.equals(flight.getDepartureAirportIATACode()) &&
                            DateUtils.isSameDay(departureDate, flight.getDepartureDate()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("FlightRepositoryJson - there is no json file in pointed localization", e);
            throw new GetDataFromJsonException(e.getMessage());
        }
    }

    @Override
    public List<Flight> findAllByArrivalAirportIATACodeAndDepartureDate(AirportIATACode arrivalAirportIATACode, Date departureDate) {
        try {
            List<Flight> flights = getObjectsFromJsonFile();
            return flights.stream()
                    .filter(flight -> arrivalAirportIATACode.equals(flight.getArrivalAirportIATACode()) &&
                            DateUtils.isSameDay(departureDate, flight.getDepartureDate()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("FlightRepositoryJson - there is no json file in pointed localization", e);
            throw new GetDataFromJsonException(e.getMessage());
        }
    }
}
