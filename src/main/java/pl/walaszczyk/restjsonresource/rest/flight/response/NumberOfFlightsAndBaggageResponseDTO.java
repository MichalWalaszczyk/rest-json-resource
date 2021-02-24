package pl.walaszczyk.restjsonresource.rest.flight.response;

import lombok.Data;

@Data
public class NumberOfFlightsAndBaggageResponseDTO {
    private Integer flightsDepartingFromTheAirport;
    private Integer flightsArrivingToTheAirport;
    private Integer numberOfBaggagePiecesArrivingToTheAirport;
    private Integer numberOfBaggagePiecesDepartingFromTheAirport;
}
