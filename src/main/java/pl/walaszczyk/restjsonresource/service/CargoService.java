package pl.walaszczyk.restjsonresource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.walaszczyk.restjsonresource.model.cargo.Cargo;
import pl.walaszczyk.restjsonresource.model.cargo.repository.CargoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    @Autowired
    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public Optional<Cargo> findCargoByFlightId(Integer flightId) {
        return cargoRepository.findByFlightId(flightId);
    }

    public List<Cargo> findAllByFlightIdIn(List<Integer> flightIds) {
        return cargoRepository.findAllByFlightIdIn(flightIds);
    }
}
