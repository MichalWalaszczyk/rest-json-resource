package pl.walaszczyk.restjsonresource.model.cargo.repository;

import pl.walaszczyk.restjsonresource.model.cargo.Cargo;

import java.util.List;
import java.util.Optional;

public interface CargoRepository {

    Optional<Cargo> findByFlightId(Integer flightId);

    List<Cargo> findAllByFlightIdIn(List<Integer> flightIds);
}
